package com.mindhub.homebanking.dto;



public class LoanApplicationDTO {

    private long id;

    private double amount;

    private int payment;

    private String numberAccount;




    public LoanApplicationDTO(double amount, int payment, String numberAccount) {
        this.amount = amount;
        this.payment = payment;
        this.numberAccount = numberAccount;

    }




    public long getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getPayment() {
        return payment;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }

    public String getNumberAccount() {
        return numberAccount;
    }

    public void setNumberAccount(String numberAccount) {
        this.numberAccount = numberAccount;
    }


}
