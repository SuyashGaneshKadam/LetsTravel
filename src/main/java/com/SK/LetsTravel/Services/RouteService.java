package com.SK.LetsTravel.Services;

import com.SK.LetsTravel.Models.Route;
import com.SK.LetsTravel.RequestDtos.AddRouteRequestDto;
import com.SK.LetsTravel.Transformers.RouteTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RouteService {
    @Autowired
    private RouteRepository routeRepository;

    public String addRoute(AddRouteRequestDto requestDto){
        Route route = RouteTransformer.convertDtoToEntity(requestDto);
        routeRepository.save(route);
        return "Route has been added successfully";
    }
}
