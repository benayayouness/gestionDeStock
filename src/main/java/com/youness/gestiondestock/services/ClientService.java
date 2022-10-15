package com.youness.gestiondestock.services;

import com.youness.gestiondestock.dto.CategorieDto;
import com.youness.gestiondestock.dto.ClientDto;
import com.youness.gestiondestock.model.Adresse;

import java.util.List;

public interface ClientService {
    ClientDto save(ClientDto dto);

    ClientDto findById(Integer id);

    ClientDto findByNomAndPrenom(String nom,String prenom);

    ClientDto findByAdresse(String adresse);

    List<ClientDto> findAll();

    void deleteById(Integer id);
}
