package com.youness.gestiondestock.controller;

import com.youness.gestiondestock.controller.api.UtilisateurApi;
import com.youness.gestiondestock.dto.UtilisateurDto;
import com.youness.gestiondestock.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UtilisateurController implements UtilisateurApi {
    private UtilisateurService utilisateurService;

    @Autowired
    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @Override
    public UtilisateurDto save(UtilisateurDto dto) {
        return utilisateurService.save(dto);
    }

    @Override
    public UtilisateurDto findById(Integer id) {
        return utilisateurService.findById(id);
    }

    @Override
    public UtilisateurDto findByNomAndPrenom(String nom, String prenom) {
        return utilisateurService.findByNomAndPrenom(nom, prenom);
    }

    @Override
    public UtilisateurDto findByDateDeNaissance(String date) {
        return utilisateurService.findByDateDeNaissance(date);
    }

    @Override
    public UtilisateurDto findByAdresse(String adresse) {
        return utilisateurService.findByAdresse(adresse);
    }

    @Override
    public List<UtilisateurDto> findAll() {
        return utilisateurService.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        utilisateurService.deleteById(id);
    }
}
