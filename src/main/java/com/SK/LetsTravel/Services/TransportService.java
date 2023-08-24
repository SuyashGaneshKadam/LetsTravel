package com.SK.LetsTravel.Services;

import com.SK.LetsTravel.CustomExceptions.RouteNotFoundException;
import com.SK.LetsTravel.Models.Route;
import com.SK.LetsTravel.Models.Transport;
import com.SK.LetsTravel.Repositories.RouteRepository;
import com.SK.LetsTravel.Repositories.TransportRepository;
import com.SK.LetsTravel.RequestDtos.AddTransportRequestDto;
import com.SK.LetsTravel.Transformers.TransportTransformer;
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
    public String addTransport(AddTransportRequestDto requestDto) throws Exception{
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
