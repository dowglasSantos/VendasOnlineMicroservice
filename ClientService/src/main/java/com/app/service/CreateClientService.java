package com.app.service;

import com.app.domain.Client;
import com.app.dto.ClientDTO;
import com.app.exception.clientexception.AlterClientException;
import com.app.exception.clientexception.DeleteClientException;
import com.app.repository.IClientRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateClientService {
    @Autowired
    IClientRepository clientRepository;

    Client client =  new Client();

    public Client createClient(@Valid ClientDTO clientDTO) {
        client.setName(clientDTO.name());
        client.setCpf(clientDTO.cpf());
        client.setEmail(clientDTO.email());

        System.out.println("Create Client: " + client);

        return clientRepository.save(client);
    }

    public Client alterClient(Long id, @Valid ClientDTO clientDTO) throws AlterClientException{
        Client entity = clientRepository.findById(id)
                .orElseThrow(() -> new AlterClientException("alterClient :: Client not found"));

        entity.setName(clientDTO.name());
        entity.setCpf(clientDTO.cpf());
        entity.setEmail(clientDTO.email());

        clientRepository.save(entity);

        return entity;
    }

    public Boolean deleteClient(@Valid Long id) throws DeleteClientException {
        Client entity = clientRepository.findById(id)
                .orElseThrow(() -> new DeleteClientException("deleteClient :: Client not found"));

        clientRepository.delete(entity);

        return true;
    }
}
