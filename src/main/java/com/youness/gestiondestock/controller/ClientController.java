package com.youness.gestiondestock.controller;

import com.youness.gestiondestock.controller.api.ClientApi;
import com.youness.gestiondestock.dto.ClientDto;
import com.youness.gestiondestock.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClientController implements ClientApi {
    private ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public ClientDto save(ClientDto dto) {
        return clientService.save(dto);
    }

    @Override
    public ClientDto findById(Integer id) {
        return clientService.findById(id);
    }

    @Override
    public ClientDto findByNomAndPrenom(String nom, String prenom) {
        return clientService.findByNomAndPrenom(nom,prenom);
    }

    @Override
    public ClientDto findByAdresse(String adresse) {
        return clientService.findByAdresse(adresse);
    }

    @Override
    public List<ClientDto> findAll() {
        return clientService.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        clientService.deleteById(id);
    }
}
