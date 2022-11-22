package com.mindhub.homebanking.service;


import com.mindhub.homebanking.dto.ClientDTO;
import com.mindhub.homebanking.models.Client;
import org.bouncycastle.asn1.eac.PublicKeyDataObject;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ClientService {

    public List<ClientDTO> getClientsDTO();

    public ClientDTO getClientDTO(long id);

    public Client findByEmail(String email);

    public void saveClient(Client client);

    public Client getClientCurrent(String email);

    public void deleteClient(Client client);

}
