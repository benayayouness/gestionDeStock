package com.youness.gestiondestock.controller;

import com.youness.gestiondestock.controller.api.EntrepriseApi;
import com.youness.gestiondestock.dto.EntrepriseDto;
import com.youness.gestiondestock.services.EntrepriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EntrepriseController implements EntrepriseApi {
    private EntrepriseService entrepriseService;

    @Autowired
    public EntrepriseController(EntrepriseService entrepriseService) {
        this.entrepriseService = entrepriseService;
    }

    @Override
    public EntrepriseDto save(EntrepriseDto dto) {
        return entrepriseService.save(dto);
    }

    @Override
    public EntrepriseDto findById(Integer id) {
        return entrepriseService.findById(id);
    }

    @Override
    public EntrepriseDto findByNom(String nom) {
        return entrepriseService.findByNom(nom);
    }

    @Override
    public EntrepriseDto findByAdresse(String adresse) {
        return entrepriseService.findByAdresse(adresse);
    }

    @Override
    public EntrepriseDto findByCodeFiscal(String code) {
        return entrepriseService.findByCodeFiscal(code);
    }

    @Override
    public List<EntrepriseDto> findAll() {
        return entrepriseService.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        entrepriseService.deleteById(id);
    }
}
