package com.SK.LetsTravel.Services;

import com.SK.LetsTravel.Enums.ModeOfTransport;
import com.SK.LetsTravel.Models.*;
import com.SK.LetsTravel.Repositories.*;
import com.SK.LetsTravel.RequestDTOs.*;
import com.SK.LetsTravel.ResponseDTOs.SearchFlightsResult;
import com.SK.LetsTravel.Transformers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RouteService {
    @Autowired
    private RouteRepository routeRepository;

    public String addRoute(AddRoute requestDto){
        Route route = RouteTransformer.convertDtoToEntity(requestDto);
        routeRepository.save(route);
        return "Route has been added successfully";
    }

    public List<SearchFlightsResult> searchFlights(SearchFlights request){
        List<Route> routes = routeRepository.findRouteByFromCityAndToCityAndModeOfTransport(request.getFromCity(),request.getToCity(), ModeOfTransport.Flight);
        List<SearchFlightsResult> resultList = new ArrayList<>();
        for(Route route : routes){
            List<Transport> transportList = route.getTransportList();
            for (Transport transport : transportList){
                if(request.getJourneyDate().equals(transport.getJourneyDate())){
                    SearchFlightsResult object = TransportTransformer.convertEntityToSearchFlightResult(transport);
                    object.setStopsInBetween(route.getStopsInBetween());
                    resultList.add(object);
                }
            }
        }
        return resultList;
    }
}
