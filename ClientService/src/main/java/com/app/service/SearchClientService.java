package com.app.service;

import com.app.domain.Client;
import com.app.exception.clientexception.GetClientByCpfException;
import com.app.exception.clientexception.GetClientByEmailException;
import com.app.exception.clientexception.GetClientByIdException;
import com.app.exception.clientexception.GetListClientException;
import com.app.repository.IClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SearchClientService {
    @Autowired
    IClientRepository clientRepository;

    private List<Client> listClient;

    public Page<Client> searchPageClient(Pageable pageable) {
        return clientRepository.findAll(pageable);
    }

    public Boolean clientIsRegistered(Long id) {
        Optional<Client> clientOptional = clientRepository.findById(id);

        return clientOptional.isPresent() ? true : false;
    }

    public Client getClientById(Long id) throws GetClientByIdException {
        Client entity = clientRepository.findById(id)
                .orElseThrow(() -> new GetClientByIdException("getClientById :: No client with ID: " + id + "were found."));

        return entity;
    }

    public Client getClientByEmail(String email) throws GetClientByEmailException {
        Client entity = clientRepository.findClientByEmail(email)
                .orElseThrow(() -> new GetClientByEmailException("getClientByEmail :: No client matching that email address: "+ email + "was found."));

        return entity;
    }

    public Client findClientByCpf(String cpf) throws GetClientByCpfException {
        Client entity = clientRepository.findClientByCpf(cpf)
                .orElseThrow(() -> new GetClientByCpfException("getClientByEmail :: No client matching that cpf: "+ cpf + "was found."));

        return entity;
    }

    public List<Client> getListAllClient() throws GetListClientException {
        listClient = clientRepository.findAll();

        if (!listClient.isEmpty()) {
            throw new GetListClientException("getListClient :: Not There are list in database");
        }

        return listClient;
    }
}
