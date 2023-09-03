package com.SK.LetsTravel.Services;

import com.SK.LetsTravel.CustomExceptions.*;
import com.SK.LetsTravel.Enums.*;
import com.SK.LetsTravel.Models.*;
import com.SK.LetsTravel.Repositories.*;
import com.SK.LetsTravel.RequestDTOs.*;
import com.SK.LetsTravel.ResponseDTOs.*;
import com.SK.LetsTravel.Transformers.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class TransportService {
    @Autowired
    private TransportRepository transportRepository;
    @Autowired
    private RouteRepository routeRepository;
    public String addTransport(AddTransport requestDto) throws Exception{
        if(!routeRepository.existsById(requestDto.getRouteId())){
            log.error("Route ID is invalid");
            throw new RouteNotFoundException("Invalid Route ID");
        }
        Route route = routeRepository.findById(requestDto.getRouteId()).get();
        Transport transport = TransportTransformer.convertDtoToEntity(requestDto);

        transport.setRoute(route);
        route.getTransportList().add(transport);

        routeRepository.save(route);

        return "Transport has been added successfully";
    }

    public List<SearchFlightsResult> searchFlights(City fromCity, City toCity, LocalDate journeyDate){
        List<Route> routes = routeRepository.findRouteByFromCityAndToCityAndModeOfTransport(fromCity,toCity,ModeOfTransport.Flight);
        List<SearchFlightsResult> resultList = new ArrayList<>();
        for(Route route : routes){
            List<Transport> transportList = route.getTransportList();
            for (Transport transport : transportList){
                if(journeyDate.equals(transport.getJourneyDate())){
                    SearchFlightsResult object = TransportTransformer.convertEntityToSearchFlightResult(transport);
                    object.setStopsInBetween(route.getStopsInBetween());
                    resultList.add(object);
                }
            }
        }
        return resultList;
    }

    public List<AvailableSeats> getAvailableSeats(LocalDate journeyDate, Integer transportId) throws Exception{
        if(!transportRepository.existsById(transportId)){
            throw new TransportNotFoundException("Invalid Transport ID");
        }

        Transport transport = transportRepository.findById(transportId).get();
        List<Seat> seatList = transport.getSeatList();
        String bookedSeatNos = transport.getBookedSeatNos();
        List<AvailableSeats> availableSeatsList = new ArrayList<>();
        if(bookedSeatNos != null && bookedSeatNos.length() == transport.getTotalSeats()){
            throw new AllSeatsBookedException("All seats are booked");
        }
        for(Seat seat : seatList){
            if(bookedSeatNos == null || bookedSeatNos.indexOf(seat.getSeatNo()) == -1){
                AvailableSeats availableSeat = SeatTransformer.convertEntityToDto(seat);
                availableSeatsList.add(availableSeat);
            }
        }
        return availableSeatsList;
    }

    public Integer totalRevenueEarned(String companyName, Integer month, Integer year) throws Exception{
        Transport transport = transportRepository.findTransportByCompanyName(companyName, month, year);
        if(transport == null){
            log.error("There are no transports");
            throw new Exception("No transport exists in August month of 2023");
        }
        List<Booking> bookingList = transport.getBookingList();
        if(bookingList == null || bookingList.size() == 0){
            log.error("No bookings are done for this company in August 2023");
            throw new Exception("There are no bookings done");
        }
        Integer totalRevenue = 0;
        for(Booking booking : bookingList){
            totalRevenue += booking.getTicket().getTotalCostPaid();
        }
        return totalRevenue;
    }
}