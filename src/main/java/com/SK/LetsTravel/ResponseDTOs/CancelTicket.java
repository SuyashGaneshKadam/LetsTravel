package com.SK.LetsTravel.ResponseDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CancelTicket {
    private Integer userId;
    private String name;
    private Integer amount;
}
