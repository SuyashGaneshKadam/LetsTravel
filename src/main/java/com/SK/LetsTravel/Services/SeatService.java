package com.SK.LetsTravel.Services;

import com.SK.LetsTravel.CustomExceptions.TransportNotFoundException;
import com.SK.LetsTravel.Enums.*;
import com.SK.LetsTravel.Models.*;
import com.SK.LetsTravel.Repositories.*;
import com.SK.LetsTravel.RequestDTOs.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeatService {

    @Autowired
    private SeatRepository seatRepository;
    @Autowired
    private TransportRepository transportRepository;

    public String addFlightSeats(AddFlightSeats addFlightSeats) throws Exception{
        if(!transportRepository.existsById(addFlightSeats.getTransportId())){
            throw new TransportNotFoundException("Invalid Transport ID");
        }
        Transport transport = transportRepository.findById(addFlightSeats.getTransportId()).get();
        if(!transport.getModeOfTransport().toString().equals("Flight")){
            throw new Exception("No flight is associated with the entered Transport ID");
        }
        for(int i=1 ; i<=addFlightSeats.getNoOfEconomySeats() ; i++){
            Seat seat = Seat.builder().seatNo("E" + i).seatType(SeatType.Economy).
                    price(addFlightSeats.getPriceOfEconomySeat()).transport(transport).build();
            transport.getSeatList().add(seat);
        }

        for(int i=1 ; i<=addFlightSeats.getNoOfBusinessSeats() ; i++){
            Seat seat = Seat.builder().seatNo("B" + i).seatType(SeatType.Business).
                    price(addFlightSeats.getPriceOfBusinessSeat()).transport(transport).build();
            transport.getSeatList().add(seat);
        }
        transport.setTotalSeats(addFlightSeats.getNoOfEconomySeats() + addFlightSeats.getNoOfBusinessSeats());
        transportRepository.save(transport);
        return "Seats have been added successfully";
    }
}
