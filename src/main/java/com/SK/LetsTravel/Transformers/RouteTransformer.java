package com.SK.LetsTravel.Transformers;

import com.SK.LetsTravel.Models.Route;
import com.SK.LetsTravel.RequestDtos.AddRouteRequestDto;

public class RouteTransformer {

    public static Route convertDtoToEntity(AddRouteRequestDto requestDto){
        Route route = Route.builder().fromCity(requestDto.getFromCity()).toCity(requestDto.getToCity()).
                stopsInBetween(requestDto.getStopsInBetween()).build();
        return route;
    }
}
