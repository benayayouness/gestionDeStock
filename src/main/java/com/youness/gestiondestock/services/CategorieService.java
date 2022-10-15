package com.youness.gestiondestock.services;

import com.youness.gestiondestock.dto.CategorieDto;

import java.util.List;

public interface CategorieService {
    CategorieDto save(CategorieDto dto);

    CategorieDto findById(Integer id);

    CategorieDto findByCode(String code);

    List<CategorieDto> findAll();

    void deleteById(Integer id);
}
