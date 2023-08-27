package com.SK.LetsTravel.Transformers;

import com.SK.LetsTravel.Models.Route;
import com.SK.LetsTravel.RequestDTOs.AddRoute;

public class RouteTransformer {

    public static Route convertDtoToEntity(AddRoute requestDto){
        Route route = Route.builder().fromCity(requestDto.getFromCity()).toCity(requestDto.getToCity()).
                stopsInBetween(requestDto.getStopsInBetween()).modeOfTransport(requestDto.getModeOfTransport()).build();
        return route;
    }
}
