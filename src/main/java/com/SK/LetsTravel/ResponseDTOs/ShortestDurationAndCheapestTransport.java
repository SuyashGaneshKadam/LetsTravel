package com.SK.LetsTravel.ResponseDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.convert.DataSizeUnit;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShortestDurationAndCheapestTransport {
    private Integer shortestDurationTransportId;
    private Integer cheapestSeatTransportId;
}
