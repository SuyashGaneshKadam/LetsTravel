package com.SK.LetsTravel.Transformers;

import com.SK.LetsTravel.Models.*;
import com.SK.LetsTravel.RequestDTOs.*;

public class UserTransformer {
    public static User convertDtoToEntity(AddUser addUser){
        User user = User.builder().name(addUser.getName()).age(addUser.getAge()).
                emailId(addUser.getEmailId()).gender(addUser.getGender()).build();
        return user;
    }
}
