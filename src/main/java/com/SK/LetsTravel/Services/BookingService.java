package com.SK.LetsTravel.Services;

import com.SK.LetsTravel.CustomExceptions.*;
import com.SK.LetsTravel.Models.*;
import com.SK.LetsTravel.Repositories.*;
import com.SK.LetsTravel.RequestDTOs.BookSeatsInFlight;
import com.SK.LetsTravel.ResponseDTOs.*;
import com.SK.LetsTravel.Transformers.BookingTransformer;
import com.SK.LetsTravel.Transformers.SeatTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

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

    public String BookSeatsInFlight(BookSeatsInFlight bookSeatsInFlight) throws Exception{
        if(!userRepository.existsById(bookSeatsInFlight.getUserId())){
            throw new UserNotFoundException("Invalid User ID");
        }
        if(!transportRepository.existsById(bookSeatsInFlight.getTransportId())){
            throw new TransportNotFoundException("Invalid Transport ID");
        }
        Transport transport = transportRepository.findById(bookSeatsInFlight.getTransportId()).get();
        if(!checkIfAllSeatsAvailable(bookSeatsInFlight.getSeatNos(), transport)){
            throw new SeatsNotAvailableException("Seats are not available");
        }

        User user = userRepository.findById(bookSeatsInFlight.getUserId()).get();
        Booking booking = BookingTransformer.convertDtoToEntity(bookSeatsInFlight);

        String bookedSeatNos = transport.getBookedSeatNos();
        if(bookedSeatNos == null) bookedSeatNos = booking.getSeatNos();
        else bookedSeatNos += "," + booking.getSeatNos();
        transport.setBookedSeatNos(bookedSeatNos);

        Ticket ticket = createTicket(transport, bookSeatsInFlight);

        booking.setUser(user);
        booking.setTicket(ticket);
        booking.setTransport(transport);

        ticket.setBooking(booking);
        user.getBookingList().add(booking);
        transport.getBookingList().add(booking);

        bookingRepository.save(booking);

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        String body = "Hi " + user.getName() + ",\n \n" +
                "Thank you for booking a flight with LetsTravel\n" +
                "Your Flight details are as follows :- \n" +
                ticket.getRouteDetails() + " on " + booking.getJourneyDate() + "\n";
        if(ticket.getStopsInBetween() != null && !ticket.getStopsInBetween().equals("")){
            body += "During your journey FROM " + ticket.getRouteDetails() +
                    ". We will be making stops in " + ticket.getStopsInBetween() + "\n";
        }
                body += "We will be taking off at " + ticket.getStartTime() + "\n" +
                "Seat Number/s are : " + ticket.getSeatNos() + "\n" +
                "Total cost paid was : " + ticket.getTotalCostPaid() + "\n" +
                "We hope you have a wonderful experience with us. Have a safe and happy journey.\n \n \n" +
                "Best Regards,\n" +
                "Team LetsTravel";

        simpleMailMessage.setSubject("Flight booking successful");
        simpleMailMessage.setFrom("letstravelbot@gmail.com");
        simpleMailMessage.setTo(user.getEmailId());
        simpleMailMessage.setText(body);
        javaMailSender.send(simpleMailMessage);

        return "Booking done successfully";
    }
    private Ticket createTicket(Transport transport, BookSeatsInFlight bookSeatsInFlight){
        Integer totalCostPaid = findTotalCostPaid(transport, bookSeatsInFlight);
        String routeDetails = getRouteDetails(transport);
        Ticket ticket = Ticket.builder().journeyDate(transport.getJourneyDate()).routeDetails(routeDetails)
                .startTime(transport.getStartTime()).totalCostPaid(totalCostPaid).seatNos(bookSeatsInFlight.getSeatNos())
                .stopsInBetween(transport.getRoute().getStopsInBetween()).build();
        return ticket;
    }
    private Integer findTotalCostPaid(Transport transport, BookSeatsInFlight bookSeatsInFlight){
        List<Seat> seatList = transport.getSeatList();
        String[] bookedSeats = bookSeatsInFlight.getSeatNos().split(",");
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
}