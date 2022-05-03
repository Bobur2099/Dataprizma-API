package com.example.dataprizma.loginController;

import com.example.dataprizma.loginService.EmailSenderService;
import com.example.dataprizma.loginmodel.User;
import com.example.dataprizma.loginrepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/password")
public class EmailController {

        @Autowired
        UserRepository userRepository;
    private final EmailSenderService emailSenderService;

    public EmailController(EmailSenderService emailSenderService) {
        this.emailSenderService = emailSenderService;
    }

    @PostMapping("/send-email")
    public ResponseEntity sendEmail(@RequestParam("to") String to) {
             User user = userRepository.findByEmail(to);
        if(user != null){
            this.emailSenderService.sendEmail(to, user.getPassword1());
            return ResponseEntity.ok("Success");
        }
        return new ResponseEntity("Bad request", HttpStatus.BAD_REQUEST);

    }
}
