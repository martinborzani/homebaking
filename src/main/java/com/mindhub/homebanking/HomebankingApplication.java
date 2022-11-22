package com.mindhub.homebanking;

import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class HomebankingApplication {

//	@Autowired
//	private PasswordEncoder passwordEnconder;


	public static void main(String[] args) {
		SpringApplication.run(HomebankingApplication.class, args);

	}


	@Bean
	public CommandLineRunner initData(ClientRepository clientRepository, AccountRepository accountRepository, TransactionReposity transactionReposity, LoadRepository loadRepository, ClientLoanRepository clientLoanRepository,CardRepository cardRepository){
		return args -> {




//			Client client1 = new Client("Melba","Morel","melba@mindhub.com", passwordEnconder.encode("1234"));
//			Client client2 = new Client("Martin","Borzani","martinborzani0@gmail.com", passwordEnconder.encode("1234"));
//			List<Integer> cuotasPrestamoHipotecario = List.of(12,24,36,48,60);
//			List<Integer> cuotasPrestamoPersonal = List.of(6,12,24);
//			List<Integer> cuotasPrestamoAutomotriz = List.of(6,12,24,36);
//			clientRepository.save(client1);
//			clientRepository.save(client2);
//			Account cuenta1 = new Account("VIN001",LocalDateTime.now(),5000, "ahorro" ,client1);
//			Account cuenta2 = new Account("VIN002",LocalDateTime.now().plusDays(1),7500, "ahorro",client1);
//			Account cuenta3 = new Account("VIN003",LocalDateTime.now(),11000, "ahorro",client2);
//			Account cuenta4 = new Account("VIN004",LocalDateTime.now().plusDays(1),12500, "ahorro",client2);
//			Transaction transferencia1 = new Transaction(TransactionType.debito,300,"cuota netflix",LocalDateTime.now(),cuenta1);
//			Transaction transferencia2 = new Transaction(TransactionType.credito,500,"cuota ropa",LocalDateTime.now(),cuenta1);
//			Transaction transferencia3 = new Transaction(TransactionType.debito,800,"cuota escuela",LocalDateTime.now(),cuenta1);
//			Loan prestamo1 = new Loan("Hipotecario",500000,cuotasPrestamoHipotecario);
//			Loan prestamo2 = new Loan("Personal",100000,cuotasPrestamoPersonal);
//			Loan prestamo3 = new Loan("Automotriz",300000,cuotasPrestamoAutomotriz);
//			ClientLoan prestamoCliente1 = new ClientLoan(10000,12, client1,prestamo1);
//			ClientLoan prestamoCliente2 = new ClientLoan(15000,6, client2,prestamo2);
//			Card card1 = new Card(CardType.DEBITO,CardColor.GOLD,"1234 3456 2345 4532", client1.getFirstName() +  " "  + client1.getLastName(),123,LocalDate.now(),LocalDate.now().plusYears(5), client1);
//			Card card2 = new Card(CardType.CREDITO,CardColor.TITANIUM,"3432 4354 4324 6543", client1.getFirstName() + " "  + client1.getLastName(), 326,LocalDate.now(),LocalDate.now().plusYears(5), client1);
//			Card card3 = new Card(CardType.CREDITO,CardColor.SILVER,"3432 4234 6534 6543", client2.getFirstName() + " "  + client2.getLastName(), 423,LocalDate.now(),LocalDate.now().plusYears(5), client2);
//
//
//			accountRepository.save(cuenta1);
//			accountRepository.save(cuenta2);
//			accountRepository.save(cuenta3);
//			accountRepository.save(cuenta4);
//			loadRepository.save(prestamo1);
//			loadRepository.save(prestamo2);
//			loadRepository.save(prestamo3);
//			clientLoanRepository.save(prestamoCliente1);
//			clientLoanRepository.save(prestamoCliente2);
//			transactionReposity.save(transferencia1);
//			transactionReposity.save(transferencia2);
//			transactionReposity.save(transferencia3);
//			cardRepository.save(card1);
//			cardRepository.save(card2);
//			cardRepository.save(card3);


		};
	}

}
