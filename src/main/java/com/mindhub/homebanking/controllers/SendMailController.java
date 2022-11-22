package com.mindhub.homebanking.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendMailController {

    @Autowired
    private JavaMailSender mail;


    @PostMapping("enviarCorreo")
    public ResponseEntity<?> enviar_correo(@RequestParam String contact){

        SimpleMailMessage email = new SimpleMailMessage();

        email.setTo(contact);
        email.setFrom("martinborzani0@gmail.com");
        email.setSubject("Codigo de verificacion de email");
        email.setText("Bienvenido al dogecoin bank la fruta es: Orange");

        mail.send(email);

        return new ResponseEntity<>(true, HttpStatus.ACCEPTED);
    }

}
