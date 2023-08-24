package com.SK.LetsTravel.Transformers;

import com.SK.LetsTravel.Models.Transport;
import com.SK.LetsTravel.RequestDtos.AddTransportRequestDto;

public class TransportTransformer {
    public static Transport convertDtoToEntity(AddTransportRequestDto requestDto){
        Transport transport = Transport.builder().modeOfTransport(requestDto.getModeOfTransport()).
                journeyTime(requestDto.getJourneyTime()).startTime(requestDto.getStartTime()).
                journeyDate(requestDto.getJourneyDate()).build();
        return transport;
    }
}
