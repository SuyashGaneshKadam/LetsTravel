package com.SK.LetsTravel.Services;

import com.SK.LetsTravel.CustomExceptions.*;
import com.SK.LetsTravel.Models.*;
import com.SK.LetsTravel.Repositories.*;
import com.SK.LetsTravel.RequestDTOs.BookTicket;
import com.SK.LetsTravel.ResponseDTOs.CancelTicket;
import com.SK.LetsTravel.Transformers.BookingTransformer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private TransportRepository transportRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JavaMailSender javaMailSender;

    public String bookATicket(BookTicket bookTicket) throws Exception{
        if(!userRepository.existsById(bookTicket.getUserId())){
            throw new UserNotFoundException("Invalid User ID");
        }
        if(!transportRepository.existsById(bookTicket.getTransportId())){
            throw new TransportNotFoundException("Invalid Transport ID");
        }
        Transport transport = transportRepository.findById(bookTicket.getTransportId()).get();
        if(!checkIfAllSeatsAvailable(bookTicket.getSeatNos(), transport)){
            throw new SeatsNotAvailableException("Seats are not available");
        }

        User user = userRepository.findById(bookTicket.getUserId()).get();
        Booking booking = BookingTransformer.convertDtoToEntity(bookTicket);

        String bookedSeatNos = transport.getBookedSeatNos();
        if(bookedSeatNos == null) bookedSeatNos = booking.getSeatNos();
        else bookedSeatNos += "," + booking.getSeatNos();
        transport.setBookedSeatNos(bookedSeatNos);

        Ticket ticket = createTicket(transport, bookTicket);

        booking.setUser(user);
        booking.setTicket(ticket);
        booking.setTransport(transport);

        ticket.setBooking(booking);
        user.getBookingList().add(booking);
        transport.getBookingList().add(booking);

        bookingRepository.save(booking);

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        String body = "Hi " + user.getName() + ",\n \n" +
                "Thank you for booking " + transport.getModeOfTransport() + " seats with LetsTravel\n" +
                "Your " + transport.getModeOfTransport() + " details are as follows :- \n" +
                ticket.getRouteDetails() + " on " + booking.getJourneyDate() + "\n";
        if(ticket.getStopsInBetween() != null && !ticket.getStopsInBetween().equals("")){
            body += "During your journey from " + transport.getRoute().getFromCity() + " to " + transport.getRoute().getToCity() +
                    ". We will be making stops in " + ticket.getStopsInBetween() + "\n";
        }
                body += "We will be taking off at " + ticket.getStartTime() + "\n" +
                "Seat Number/s are : " + ticket.getSeatNos() + "\n" +
                "Total cost paid was : " + ticket.getTotalCostPaid() + "\n" +
                "We hope you have a wonderful experience with us. Have a safe and happy journey.\n \n \n" +
                "Best Regards,\n" +
                "Team LetsTravel";

        simpleMailMessage.setSubject("Booking successful!");
        simpleMailMessage.setFrom("letstravelbot@gmail.com");
        simpleMailMessage.setTo(user.getEmailId());
        simpleMailMessage.setText(body);
        javaMailSender.send(simpleMailMessage);

        return "Booking has been done successfully. Ticket has been sent to your registered email ID";
    }
    private Ticket createTicket(Transport transport, BookTicket bookTicketInFlight){
        Integer totalCostPaid = findTotalCostPaid(transport, bookTicketInFlight);
        String routeDetails = getRouteDetails(transport);
        Ticket ticket = Ticket.builder().journeyDate(transport.getJourneyDate()).routeDetails(routeDetails)
                .startTime(transport.getStartTime()).totalCostPaid(totalCostPaid).seatNos(bookTicketInFlight.getSeatNos())
                .stopsInBetween(transport.getRoute().getStopsInBetween()).build();
        return ticket;
    }
    private Integer findTotalCostPaid(Transport transport, BookTicket bookTicketInFlight){
        List<Seat> seatList = transport.getSeatList();
        String[] bookedSeats = bookTicketInFlight.getSeatNos().split(",");
        Integer economySeatPrice = 0;
        Integer businessSeatPrice = 0;
        for(Seat seat : seatList){
            if(economySeatPrice != 0 && businessSeatPrice != 0){
                break;
            }
            if(seat.getSeatNo().contains("E")){
                economySeatPrice = seat.getPrice();
            }
            else{
                businessSeatPrice = seat.getPrice();
            }
        }
        Integer totalPrice = 0;
        for(String seat : bookedSeats){
            if(seat.contains("E")){
                totalPrice += economySeatPrice;
            }
            else{
                totalPrice += businessSeatPrice;
            }
        }
        return totalPrice;
    }
    private String getRouteDetails(Transport transport){
        Route route = transport.getRoute();
        return route.getFromCity() + " TO " + route.getToCity();
    }
    private boolean checkIfAllSeatsAvailable(String seatNos, Transport transport){
        String[] seatNosToCheck = seatNos.split(",");
        String bookedSeatNos = transport.getBookedSeatNos();
        if(bookedSeatNos == null) return true;
        for(int i=0 ; i < seatNosToCheck.length ; i++){
            if(bookedSeatNos.indexOf(seatNosToCheck[i]) != -1){
                return false;
            }
        }
        return true;
    }

    public CancelTicket cancelTicket(Integer transportId, String seatNo) throws Exception{
        if(!transportRepository.existsById(transportId)){
            log.error("Transport ID entered is invalid");
            throw new TransportNotFoundException("Invalid transport ID");
        }
        Transport transport = transportRepository.findById(transportId).get();
        List<Seat> seatList = transport.getSeatList();
        if(seatList == null || seatList.size() == 0){
            log.error("Seat number is not valid");
            throw new Exception("Invalid Seat number");
        }
        List<Booking> bookingList = transport.getBookingList();
        if(bookingList == null || bookingList.size() == 0){
            log.error("There are no bookings done for this transport");
            throw new Exception("There are no bookings done");
        }
        Integer price = -1;
        for(Seat seat : seatList){
            if(seatNo.equals(seat.getSeatNo())){
                price = seat.getPrice();
                break;
            }
        }
        if(price == -1){
            log.error("Seat Number is not valid");
            throw new Exception("Invalid Seat number");
        }
        if(!transport.getBookedSeatNos().contains(seatNo)){
            log.error("There are no bookings done for this seat number");
            throw new Exception("There are no bookings done for this Seat No");
        }
        else{
            String[] seatNos = transport.getBookedSeatNos().split(",");
            StringBuilder sb = new StringBuilder();
            for(int i=0 ; i<seatNos.length ; i++){
                if(seatNo.equals(seatNos[i])){
                    continue;
                }
                sb.append(seatNos[i]);
                if(i != seatNos.length - 1){
                    sb.append(",");
                }
            }
            transport.setBookedSeatNos(sb.toString());
        }
        Integer userId = -1;
        String userName = "";
        for(Booking booking : bookingList){
            if(booking.getSeatNos().contains(seatNo)){
                userId = booking.getUser().getUserId();
                userName = booking.getUser().getName();
            }
        }
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        String body = "Hi " + userName + ",\n\n" +
                "You have cancelled the following seat : " + seatNo + "\n" +
                "₹ " + price + " has been refunded in your account.\n" +
                "If you have any queries, please send an email to Testing@letstravel.com or give" +
                " us a call at 00000-00000\n" +
                "Have a good day!\n\n\n" +
                "Best regards,\nTeam LetsTravel.";
        simpleMailMessage.setSubject("Booking cancelled!");
        simpleMailMessage.setFrom("letstravelbot@gmail.com");
        simpleMailMessage.setTo(userRepository.findById(userId).get().getEmailId());
        simpleMailMessage.setText(body);
        javaMailSender.send(simpleMailMessage);
        log.info("Modifications are done to the databases");
        transportRepository.save(transport);
        return new CancelTicket(userId,userName,price);
    }
}