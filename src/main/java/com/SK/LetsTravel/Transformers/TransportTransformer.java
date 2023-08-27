package com.SK.LetsTravel.Transformers;

import com.SK.LetsTravel.Models.Transport;
import com.SK.LetsTravel.RequestDTOs.AddTransport;
import com.SK.LetsTravel.RequestDTOs.SearchFlights;
import com.SK.LetsTravel.ResponseDTOs.SearchFlightsResult;

public class TransportTransformer {
    public static Transport convertDtoToEntity(AddTransport requestDto){
        Transport transport = Transport.builder().modeOfTransport(requestDto.getModeOfTransport()).
                journeyTime(requestDto.getJourneyTime()).startTime(requestDto.getStartTime()).
                journeyDate(requestDto.getJourneyDate()).companyName(requestDto.getCompanyName()).build();
        return transport;
    }

    public static SearchFlightsResult convertEntityToSearchFlightResult(Transport transport){
        SearchFlightsResult object = SearchFlightsResult.builder().journeyDate(transport.getJourneyDate()).
                journeyTime(transport.getJourneyTime()).startTime(transport.getStartTime()).
                companyName(transport.getCompanyName()).build();
        return object;
    }
}
