package com.SK.LetsTravel.RequestDTOs;

import com.SK.LetsTravel.Enums.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddUser {
    private String name;

    private String emailId;

    private Integer age;

    private Gender gender;
}
