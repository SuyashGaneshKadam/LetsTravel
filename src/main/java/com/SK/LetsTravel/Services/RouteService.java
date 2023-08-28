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
}
