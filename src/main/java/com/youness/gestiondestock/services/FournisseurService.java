package com.youness.gestiondestock.services;

import com.youness.gestiondestock.dto.ClientDto;
import com.youness.gestiondestock.dto.FournisseurDto;

import java.util.List;

public interface FournisseurService {
    FournisseurDto save(FournisseurDto dto);

    FournisseurDto findById(Integer id);

    FournisseurDto findByNomAndPrenom(String nom,String prenom);

    FournisseurDto findByAdresse(String adresse);

    List<FournisseurDto> findAll();

    void deleteById(Integer id);
}
