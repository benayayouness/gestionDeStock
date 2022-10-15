package com.youness.gestiondestock.services;

import com.youness.gestiondestock.dto.ClientDto;
import com.youness.gestiondestock.dto.UtilisateurDto;

import java.util.List;

public interface UtilisateurService {
    UtilisateurDto save(UtilisateurDto dto);

    UtilisateurDto findById(Integer id);

    UtilisateurDto findByNomAndPrenom(String nom,String prenom);

    UtilisateurDto findByDateDeNaissance(String date);

    UtilisateurDto findByAdresse(String adresse);

    List<UtilisateurDto> findAll();

    void deleteById(Integer id);
}
