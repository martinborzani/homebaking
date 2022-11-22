package com.mindhub.homebanking.service.implement;


import com.mindhub.homebanking.dto.ClientDTO;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositories.ClientRepository;
import com.mindhub.homebanking.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImplement implements ClientService {


    @Autowired
    ClientRepository clientRepository;

    @Override
    public List<ClientDTO> getClientsDTO() {
        return clientRepository.findAll().stream().map(cliente -> new ClientDTO(cliente)).collect(Collectors.toList());
    }

    @Override
    public ClientDTO getClientDTO(long id) {
        return clientRepository.findById(id).map(client -> new ClientDTO(client)).orElse(null);
    }

    @Override
    public Client findByEmail(String email) {
        return clientRepository.findByEmail(email);
    }

    @Override
    public void saveClient(Client client) {
        clientRepository.save(client);
    }

    @Override
    public Client getClientCurrent(String email) {
        return clientRepository.findByEmail(email);
    }

    @Override
    public void deleteClient(Client client) {
        clientRepository.delete(client);
    }


}
