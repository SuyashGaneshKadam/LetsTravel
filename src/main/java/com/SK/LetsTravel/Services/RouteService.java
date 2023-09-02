package com.SK.LetsTravel.Services;

import com.SK.LetsTravel.Enums.*;
import com.SK.LetsTravel.Models.*;
import com.SK.LetsTravel.Repositories.*;
import com.SK.LetsTravel.RequestDTOs.*;
import com.SK.LetsTravel.ResponseDTOs.SearchFlightsResult;
import com.SK.LetsTravel.ResponseDTOs.ShortestDurationAndCheapestTransport;
import com.SK.LetsTravel.Transformers.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.*;

@Slf4j
@Service
public class RouteService {
    @Autowired
    private RouteRepository routeRepository;

    public String addRoute(AddRoute requestDto){
        Route route = RouteTransformer.convertDtoToEntity(requestDto);
        routeRepository.save(route);
        return "Route has been added successfully";
    }

    public ShortestDurationAndCheapestTransport findCheapestTransport(City fromCity, City toCity) throws Exception{
        List<Route> routesList = routeRepository.findRouteByFromCityAndToCity(fromCity,toCity);
        if(routesList == null || routesList.size() == 0){
            log.error("There are no routes from ",fromCity.toString()," to ", toCity.toString());
            throw new Exception("No routes exists");
        }
        Integer cheapestSeatTransportId = -1;
        Integer lowestSeatPrice = Integer.MAX_VALUE;
        Integer shortestDurationTransportId = -1;
        Double shortestDuration = Double.MAX_VALUE;

        for(Route route : routesList){
            List<Transport> transportList = route.getTransportList();
            if(transportList == null || transportList.size() == 0){
                continue;
            }
            for(Transport transport : transportList){
                List<Seat> seatList = transport.getSeatList();
                if(transport.getJourneyTime() < shortestDuration){
                    shortestDuration = transport.getJourneyTime();
                    shortestDurationTransportId = transport.getTransportId();
                }
                if(seatList == null || seatList.size() == 0){
                    continue;
                }
                for(Seat seat : seatList){
                    if(seat.getPrice() < lowestSeatPrice){
                        lowestSeatPrice = seat.getPrice();
                        cheapestSeatTransportId = transport.getTransportId();
                    }
                }
            }
        }
        if(cheapestSeatTransportId == -1){
            throw new Exception("No transport or seat exists");
        }
        return new ShortestDurationAndCheapestTransport(shortestDurationTransportId,cheapestSeatTransportId);
    }
}
