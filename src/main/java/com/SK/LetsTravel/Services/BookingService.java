package com.SK.LetsTravel.Services;

import com.SK.LetsTravel.CustomExceptions.*;
import com.SK.LetsTravel.Models.*;
import com.SK.LetsTravel.Repositories.*;
import com.SK.LetsTravel.ResponseDTOs.*;
import com.SK.LetsTravel.Transformers.SeatTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private TransportRepository transportRepository;

    public List<AvailableSeats> getAvailableSeats(LocalDate journeyDate, Integer transportId) throws Exception{
        if(!transportRepository.existsById(transportId)){
            throw new TransportNotFoundException("Invalid Transport ID");
        }
        List<Booking> bookingList = bookingRepository.findBookingByJourneyDate(journeyDate);

        for(int i=0 ; i<bookingList.size() ; i++){
            if(bookingList.get(i).getTransport().getTransportId() != transportId){
                bookingList.remove(i);
            }
        }

        HashSet<String> bookedSeats = new HashSet<>();
        for(Booking booking : bookingList){
            String[] bookedSeatsInCurrentBooking = booking.getSeatNos().split(",");
            for(String seatNo : bookedSeatsInCurrentBooking){
                bookedSeats.add(seatNo);
            }
        }

        Transport transport = transportRepository.findById(transportId).get();
        List<Seat> seatList = transport.getSeatList();
        List<AvailableSeats> availableSeatsList = new ArrayList<>();
        for(Seat seat : seatList){
            if(!bookedSeats.contains(seat.getSeatNo())){
                AvailableSeats availableSeat = SeatTransformer.convertEntityToDto(seat);
                availableSeatsList.add(availableSeat);
            }
        }
        return availableSeatsList;
    }
}
