package com.mindhub.homebanking.service.implement;

import com.mindhub.homebanking.dto.LoanDTO;
import com.mindhub.homebanking.models.Loan;
import com.mindhub.homebanking.repositories.LoadRepository;
import com.mindhub.homebanking.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanServiceImplement implements LoanService {

    @Autowired
    LoadRepository loadRepository;

    @Override
    public List<LoanDTO> getLoanDTO() {
        return loadRepository.findAll().stream().map(cliente -> new LoanDTO(cliente)).collect(Collectors.toList());
    }

    public Loan findById(long id){
        return loadRepository.findById(id).orElse(null);
    }

    @Override
    public void saveLoan(Loan loan) {
        loadRepository.save(loan);
    }
}
