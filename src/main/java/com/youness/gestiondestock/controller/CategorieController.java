package com.youness.gestiondestock.controller;

import com.youness.gestiondestock.controller.api.CategorieApi;
import com.youness.gestiondestock.dto.CategorieDto;
import com.youness.gestiondestock.services.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategorieController implements CategorieApi {
    private CategorieService categorieService;

    @Autowired
    public CategorieController(CategorieService categorieService) {
        this.categorieService = categorieService;
    }

    @Override
    public CategorieDto save(CategorieDto dto) {
        return categorieService.save(dto);
    }

    @Override
    public CategorieDto findById(Integer id) {
        return categorieService.findById(id);
    }

    @Override
    public CategorieDto findByCode(String code) {
        return categorieService.findByCode(code);
    }

    @Override
    public List<CategorieDto> findAll() {
        return categorieService.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        categorieService.deleteById(id);
    }
}
