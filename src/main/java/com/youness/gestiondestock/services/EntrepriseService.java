package com.youness.gestiondestock.services;

import com.youness.gestiondestock.dto.ClientDto;
import com.youness.gestiondestock.dto.EntrepriseDto;

import java.util.List;

public interface EntrepriseService {
    EntrepriseDto save(EntrepriseDto dto);

    EntrepriseDto findById(Integer id);

    EntrepriseDto findByNom(String nom);

    EntrepriseDto findByAdresse(String adresse);

    EntrepriseDto findByCodeFiscal(String code);

    List<EntrepriseDto> findAll();

    void deleteById(Integer id);
}
