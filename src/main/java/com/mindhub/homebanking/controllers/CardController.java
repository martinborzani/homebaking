package com.mindhub.homebanking.controllers;


import com.mindhub.homebanking.models.Card;
import com.mindhub.homebanking.models.CardColor;
import com.mindhub.homebanking.models.CardType;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositories.CardRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
import com.mindhub.homebanking.service.CardService;
import com.mindhub.homebanking.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static com.mindhub.homebanking.utils.CardUtils.*;

@RestController
@RequestMapping("/api")
public class CardController {



    @Autowired
    ClientService clientService;

    @Autowired
    CardService cardService;


    public String numberRandom(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min) + "-" + (int) ((Math.random() * (max - min)) + min) + "-" + (int) ((Math.random() * (max - min)) + min) + "-" + (int) ((Math.random() * (max - min)) + min);
    }



    @PostMapping("/clients/current/cards")
    public ResponseEntity<?> createNewCard(Authentication authentication, @RequestParam CardType cardType, @RequestParam CardColor cardColor){

        Client clientCurrent = clientService.getClientCurrent(authentication.getName());
        Set<Card> cards = clientCurrent.getCard().stream().filter(card -> card.getType() == cardType).collect(Collectors.toSet());
        Set<Card> cardsNotDelete = cards.stream().filter(card -> card.isEnable() == true).collect(Collectors.toSet());
//        String number = getCardNumber();


        if(cardsNotDelete.size() == 3){
            return new ResponseEntity<>("You can't have more than 3 cards of the same type " + cardType, HttpStatus.FORBIDDEN);
        }


        if(cardColor == null){
            return new ResponseEntity<>("Card color is blank, please select Titanium, Gold or Silver", HttpStatus.FORBIDDEN);
        }

        if(cardType == null){
            return new ResponseEntity<>("Card type is blank, please select DEBIT or CREDIT", HttpStatus.FORBIDDEN);
        }

        //long numberAccount = (long)(Math.random() * 10000 - 1000) + 1000;
        int numberAccount1 = getCVV();

        Card card = new Card(cardType,cardColor,getCardNumber(),clientCurrent.getFirstName() + " " + clientCurrent.getLastName(),numberAccount1, LocalDate.now(),LocalDate.now().plusYears(5),clientCurrent);
        cardService.saveCard(card);
        clientService.saveClient(clientCurrent);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }




    @PostMapping("/clients/current/cards/delete")
    public ResponseEntity<?> deleteCard(
            @RequestParam Long id,
            Authentication authentication
    ){

        Client clientCurrent = clientService.getClientCurrent(authentication.getName());
        Set <Card> card = clientCurrent.getCard().stream().filter(card1 -> card1.getId() == id).collect(Collectors.toSet());
        if(id == 0){
            return new ResponseEntity<>("Not exist the id ", HttpStatus.FORBIDDEN);
        }

        if(clientCurrent == null){
            return new ResponseEntity<>("Not exist the account ", HttpStatus.FORBIDDEN);
        }

        if(card == null){
            return new ResponseEntity<>("Not exist the card ", HttpStatus.FORBIDDEN);
        }

        Card foundCard = cardService.findAllById(id);
        foundCard.setEnable(false);
        cardService.saveCard(foundCard);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
