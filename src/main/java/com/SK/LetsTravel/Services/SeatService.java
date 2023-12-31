package com.SK.LetsTravel.Services;

import com.SK.LetsTravel.CustomExceptions.TransportNotFoundException;
import com.SK.LetsTravel.Enums.*;
import com.SK.LetsTravel.Models.*;
import com.SK.LetsTravel.Repositories.*;
import com.SK.LetsTravel.RequestDTOs.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

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
        if(transport.getModeOfTransport() != ModeOfTransport.Flight){
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

    public String addBusSeats(AddBusSeats addBusSeats) throws Exception{
        if(!transportRepository.existsById(addBusSeats.getTransportId())){
            throw new TransportNotFoundException("Invalid Transport ID");
        }
        Transport transport = transportRepository.findById(addBusSeats.getTransportId()).get();
        if(transport.getModeOfTransport() != ModeOfTransport.Bus){
            throw new Exception("No Bus is associated with the entered Transport ID");
        }
        for(int i=1 ; i<=addBusSeats.getNoOfWindowSeats() ; i++){
            Seat seat = Seat.builder().seatNo("W" + i).seatType(SeatType.Window).
                    price(addBusSeats.getPriceOfWindowSeat()).transport(transport).build();
            transport.getSeatList().add(seat);
        }

        for(int i=1 ; i<=addBusSeats.getNoOfACSeats() ; i++){
            Seat seat = Seat.builder().seatNo("AC" + i).seatType(SeatType.AC).
                    price(addBusSeats.getPriceOfACSeat()).transport(transport).build();
            transport.getSeatList().add(seat);
        }

        for(int i=1 ; i<=addBusSeats.getNoOfSleeperSeats() ; i++){
            Seat seat = Seat.builder().seatNo("S" + i).seatType(SeatType.Sleeper).
                    price(addBusSeats.getPriceOfSleeperSeat()).transport(transport).build();
            transport.getSeatList().add(seat);
        }
        transport.setTotalSeats(addBusSeats.getNoOfWindowSeats() + addBusSeats.getNoOfACSeats() +
                addBusSeats.getNoOfSleeperSeats());
        transportRepository.save(transport);

        return "Seats have been added successfully";
    }

    public String addTrainSeats(AddTrainSeats addTrainSeats) throws Exception{
        if(!transportRepository.existsById(addTrainSeats.getTransportId())){
            throw new TransportNotFoundException("Invalid Transport ID");
        }
        Transport transport = transportRepository.findById(addTrainSeats.getTransportId()).get();
        if(transport.getModeOfTransport() != ModeOfTransport.Train){
            throw new Exception("No Train is associated with the entered Transport ID");
        }
        for(int i=1 ; i<=addTrainSeats.getNoOfLowerSeats() ; i++){
            Seat seat = Seat.builder().seatNo("L" + i).seatType(SeatType.Lower).
                    price(addTrainSeats.getPriceOfLowerSeat()).transport(transport).build();
            transport.getSeatList().add(seat);
        }

        for(int i=1 ; i<=addTrainSeats.getNoOfMiddleSeats() ; i++){
            Seat seat = Seat.builder().seatNo("M" + i).seatType(SeatType.Middle).
                    price(addTrainSeats.getPriceOfMiddleSeat()).transport(transport).build();
            transport.getSeatList().add(seat);
        }

        for(int i=1 ; i<=addTrainSeats.getNoOfUpperSeats() ; i++){
            Seat seat = Seat.builder().seatNo("U" + i).seatType(SeatType.Upper).
                    price(addTrainSeats.getPriceOfUpperSeat()).transport(transport).build();
            transport.getSeatList().add(seat);
        }
        transport.setTotalSeats(addTrainSeats.getNoOfLowerSeats() + addTrainSeats.getNoOfMiddleSeats() +
                addTrainSeats.getNoOfUpperSeats());
        transportRepository.save(transport);

        return "Seats have been added successfully";
    }
}
