package com.mindhub.homebanking.models;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Card {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name="native",strategy = "native")
    private long id;

    private CardType type;

    private CardColor color;

    private String number;

    private String cardholder;

    private int cvv;

    private LocalDate fromDate;

    private LocalDate thruDate;

    private boolean enable = true;


    @ManyToOne(fetch = FetchType.EAGER)
    private Client clientCard;





    public Card() {
    }

    public Card(CardType type, CardColor color, String number, String cardholder, int cvv, LocalDate fromDate, LocalDate thruDate, Client clientCard) {
        this.type = type;
        this.color = color;
        this.number = number;
        this.cardholder = cardholder;
        this.cvv = cvv;
        this.fromDate = fromDate;
        this.thruDate = thruDate;
        this.clientCard = clientCard;
    }



    public long getId() {
        return id;
    }

    public CardType getType() {
        return type;
    }

    public void setType(CardType type) {
        this.type = type;
    }

    public CardColor getColor() {
        return color;
    }

    public void setColor(CardColor color) {
        this.color = color;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCardholder() {
        return cardholder;
    }

    public void setCardholder(String cardholder) {
        this.cardholder = cardholder;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getThruDate() {
        return thruDate;
    }

    public void setThruDate(LocalDate thruDate) {
        this.thruDate = thruDate;
    }

    public Client getClienteCard() {
        return clientCard;
    }


    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public void setClienteCard(Client clientCard) {
        this.clientCard = clientCard;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", color='" + color + '\'' +
                ", number=" + number +
                ", cardholder='" + cardholder + '\'' +
                ", cvv=" + cvv +
                ", fromDate=" + fromDate +
                ", thruDate=" + thruDate +
                '}';
    }
}
