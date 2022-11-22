package com.mindhub.homebanking.service.implement;


import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Card;
import com.mindhub.homebanking.repositories.CardRepository;
import com.mindhub.homebanking.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImplement implements CardService {

    @Autowired
    CardRepository cardRepository;


    @Override
    public void saveCard(Card card) {
        cardRepository.save(card);
    }

    @Override
    public void deleteCard(Card card) {
        cardRepository.delete(card);
    }

    @Override
    public Card findAllById(Long id) {
        return cardRepository.findAllById(id);
    }


}
