package com.mindhub.homebanking.service;

import com.mindhub.homebanking.dto.LoanDTO;
import com.mindhub.homebanking.models.Loan;

import java.util.List;

public interface LoanService {

    public List<LoanDTO> getLoanDTO();

    public Loan findById(long id);

    public void saveLoan(Loan loan);

}
