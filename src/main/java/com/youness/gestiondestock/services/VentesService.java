package com.youness.gestiondestock.services;

import com.youness.gestiondestock.dto.ClientDto;
import com.youness.gestiondestock.dto.VentesDto;

import java.util.List;

public interface VentesService {
    VentesDto save(VentesDto dto);

    VentesDto findById(Integer id);

    VentesDto findByCode(String code);

    VentesDto findByDateVente(String date);

    List<VentesDto> findAll();

    void deleteById(Integer id);
}
