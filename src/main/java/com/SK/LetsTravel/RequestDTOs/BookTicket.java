package com.SK.LetsTravel.RequestDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookTicket {
    private Integer userId;
    private Integer transportId;
    private String seatNos;
    private LocalDate journeyDate;
}
