package com.youness.gestiondestock.controller;

import com.youness.gestiondestock.controller.api.FournisseurApi;
import com.youness.gestiondestock.dto.FournisseurDto;
import com.youness.gestiondestock.services.FournisseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FournisseurController implements FournisseurApi {
    private FournisseurService fournisseurService;

    @Autowired
    public FournisseurController(FournisseurService fournisseurService) {
        this.fournisseurService = fournisseurService;
    }

    @Override
    public FournisseurDto save(FournisseurDto dto) {
        return fournisseurService.save(dto);
    }

    @Override
    public FournisseurDto findById(Integer id) {
        return fournisseurService.findById(id);
    }

    @Override
    public FournisseurDto findByNomAndPrenom(String nom, String prenom) {
        return fournisseurService.findByNomAndPrenom(nom,prenom);
    }

    @Override
    public FournisseurDto findByAdresse(String adresse) {
        return fournisseurService.findByAdresse(adresse);
    }

    @Override
    public List<FournisseurDto> findAll() {
        return fournisseurService.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        fournisseurService.deleteById(id);
    }
}
