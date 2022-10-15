package com.youness.gestiondestock.controller.api;

import com.youness.gestiondestock.dto.CategorieDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.youness.gestiondestock.utils.Constants.APP_ROOT;

public interface CategorieApi {
    @PostMapping(value =APP_ROOT+"/categories/create",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    CategorieDto save(@RequestBody CategorieDto dto);

    @GetMapping(value =APP_ROOT+"/categories/{idCategorie}", produces = MediaType.APPLICATION_JSON_VALUE)
    CategorieDto findById(@PathVariable("idCategorie") Integer id);

    @GetMapping(value =APP_ROOT+"/categories/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
    CategorieDto findByCode(@PathVariable("code") String code);

    @GetMapping(value =APP_ROOT+"/categories/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<CategorieDto> findAll();

    @DeleteMapping(value =APP_ROOT+"/categories/delete/{idCategorie}" )
    void deleteById(@PathVariable("idCategorie") Integer id);
}
