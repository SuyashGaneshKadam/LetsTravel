package com.SK.LetsTravel.Repositories;

import com.SK.LetsTravel.Enums.City;
import com.SK.LetsTravel.Enums.ModeOfTransport;
import com.SK.LetsTravel.Models.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface RouteRepository extends JpaRepository<Route, Integer> {

    public List<Route> findRouteByFromCityAndToCityAndModeOfTransport(City fromCity, City toCity, ModeOfTransport modeOfTransport);

    public List<Route> findRouteByFromCityAndToCity(City fromCity, City toCity);
}
