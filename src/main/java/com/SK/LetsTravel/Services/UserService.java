package com.SK.LetsTravel.Services;

import com.SK.LetsTravel.Models.User;
import com.SK.LetsTravel.Repositories.UserRepository;
import com.SK.LetsTravel.RequestDTOs.AddUser;
import com.SK.LetsTravel.Transformers.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JavaMailSender emailSender;

    public String addUser(AddUser addUser){
        User user = UserTransformer.convertDtoToEntity(addUser);
        userRepository.save(user);

        SimpleMailMessage mailMessage = new SimpleMailMessage();

        String body = "Hi " + addUser.getName() + ",\n \n" +
                "Welcome to Lets Travel. Book your Flights, Trains or Buses conveniently.\n" +
                "Have a nice day.\n \n \n" +
                "Best Regards,\n" +
                "Team LetsTravel";

        mailMessage.setSubject("Welcome to Lets Travel");
        mailMessage.setFrom("letstravelbot@gmail.com");
        mailMessage.setTo(addUser.getEmailId());
        mailMessage.setText(body);
        emailSender.send(mailMessage);

        return "User has been added successfully";
    }
}
