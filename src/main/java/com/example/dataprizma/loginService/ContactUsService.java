package com.example.dataprizma.loginService;

import com.example.dataprizma.loginService.ContactUs;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class ContactUsService implements ContactUs {

    private final JavaMailSender mailSender;

    public ContactUsService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendEmail(String name, String to, String text) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(to);
        simpleMailMessage.setTo("info@dataprizma.uz");
        simpleMailMessage.setText(text);
        this.mailSender.send(simpleMailMessage);
    }
}
