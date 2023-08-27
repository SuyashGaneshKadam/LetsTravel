package com.SK.LetsTravel.Services;

import com.SK.LetsTravel.CustomExceptions.RouteNotFoundException;
import com.SK.LetsTravel.Models.*;
import com.SK.LetsTravel.Repositories.*;
import com.SK.LetsTravel.RequestDTOs.*;
import com.SK.LetsTravel.Transformers.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}