package com.mindhub.homebanking.dto;

import com.mindhub.homebanking.models.Loan;

import java.util.ArrayList;
import java.util.List;

public class LoanDTO {


    private String nameLoan;

    private List<Integer> payments = new ArrayList<>();

    private double maxAmount;

    private long id;

    public LoanDTO(Loan loan) {
        this.nameLoan = loan.getName();
        this.payments = loan.getPayments();
        this.maxAmount = loan.getMaxAmount();
        this.id = loan.getId();
    }

    public long getId() {
        return id;
    }

    public String getNameLoan() {
        return nameLoan;
    }

    public void setNameLoan(String nameLoan) {
        this.nameLoan = nameLoan;
    }

    public List<Integer> getPayments() {
        return payments;
    }

    public void setPayments(List<Integer> payments) {
        this.payments = payments;
    }

    public double getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(double maxAmount) {
        this.maxAmount = maxAmount;
    }
}
