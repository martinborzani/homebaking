package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.configuration.WebAuthentication;
import com.mindhub.homebanking.dto.ClientDTO;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
import com.mindhub.homebanking.service.AccountService;
import com.mindhub.homebanking.service.ClientService;
import com.mindhub.homebanking.service.implement.ClientServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api")
public class ClientController {


    @Autowired
    ClientService clientService;

    @Autowired
    AccountService accountService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender mail;


    @RequestMapping("/clients")
    public List<ClientDTO> getClients(){
        return clientService.getClientsDTO();
    }

    @RequestMapping("clients/{id}")
    public ClientDTO getClient(@PathVariable long id){
        return clientService.getClientDTO(id);
    }

    @RequestMapping(path="/clients",method = RequestMethod.POST)
    public ResponseEntity<Object> register(
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String email,
            @RequestParam String password
    ){


        if(firstName.isEmpty()){
            return new ResponseEntity<>("The first name is Empty", HttpStatus.FORBIDDEN);
        }

        if(lastName.isEmpty()){
            return new ResponseEntity<>("The last name is Empty", HttpStatus.FORBIDDEN);
        }

        if(email.isEmpty()){
            return new ResponseEntity<>("The email is Empty", HttpStatus.FORBIDDEN);
        }

        if(password.isEmpty()){
            return new ResponseEntity<>("The password is Empty", HttpStatus.FORBIDDEN);
        }



        if(clientService.findByEmail(email) != null){
            return new ResponseEntity<>("Email already in use",HttpStatus.FORBIDDEN);
        }

        Client client = new Client(firstName,lastName,email,passwordEncoder.encode(password));
        clientService.saveClient(client);

        Set<String> accountNumber = accountService.getAccountNumber();
        long numberRandom;
        do{
            numberRandom = (long)(Math.random() * 100000000 - 1) + 1;

        }while(accountNumber.equals("VIN" + numberRandom));

        String accountNum = "VIN" + numberRandom;
        Account account = new Account(accountNum, LocalDateTime.now(),0,"ahorro",client);
        accountService.saveAccount(account);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @DeleteMapping("/clients/delete")
    public ResponseEntity<?> deleteCard(@RequestParam String email){

        Client clientFound = clientService.findByEmail(email);

        if(clientFound == null){
            return new ResponseEntity<>("The client not exist",HttpStatus.FORBIDDEN);
        }

        clientService.deleteClient(clientFound);

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }


    @RequestMapping("/clients/current")
    public ClientDTO getClientAuthentication(Authentication authentication){
        return new ClientDTO (clientService.findByEmail(authentication.getName()));
    }


}
