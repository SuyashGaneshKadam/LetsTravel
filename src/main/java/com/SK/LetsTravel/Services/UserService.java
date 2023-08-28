package com.SK.LetsTravel.Services;

import com.SK.LetsTravel.Models.User;
import com.SK.LetsTravel.Repositories.UserRepository;
import com.SK.LetsTravel.RequestDTOs.AddUser;
import com.SK.LetsTravel.Transformers.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public String addUser(AddUser addUser){
        User user = UserTransformer.convertDtoToEntity(addUser);
        userRepository.save(user);
        return "User has been added successfully";
    }
}
