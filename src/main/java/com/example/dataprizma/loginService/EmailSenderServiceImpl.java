package com.example.dataprizma.loginService;

import com.example.dataprizma.loginmodel.User;
import com.example.dataprizma.loginrepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderServiceImpl implements EmailSenderService {

    private final JavaMailSender mailSender;

    public EmailSenderServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Autowired
    UserRepository userRepository;
    @Override
    public void sendEmail(String to, String text) {
        User user = userRepository.findByEmail(to);

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("saidbobursb@gmail.com");
        simpleMailMessage.setTo(to);
        simpleMailMessage.setText(text);

        this.mailSender.send(simpleMailMessage);

    }
}
