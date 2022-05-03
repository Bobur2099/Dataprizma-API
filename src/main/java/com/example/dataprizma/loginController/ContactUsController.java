package com.example.dataprizma.loginController;


import com.example.dataprizma.loginService.ContactUs;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/contact-us")
public class ContactUsController {

    private final ContactUs contactUs;

    public ContactUsController(ContactUs contactUs) {
        this.contactUs = contactUs;
    }

    @PostMapping("/send-email")
    public ResponseEntity sendEmail(@RequestParam("name")String name,@RequestParam("to") String to,
                                    @RequestParam("text") String text){

        this.contactUs.sendEmail(name, to, text);
        return ResponseEntity.ok("success");

    }
}
