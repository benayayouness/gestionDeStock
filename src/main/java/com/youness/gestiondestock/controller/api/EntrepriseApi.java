package com.youness.gestiondestock.controller.api;

import com.youness.gestiondestock.dto.EntrepriseDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.youness.gestiondestock.utils.Constants.APP_ROOT;

public interface EntrepriseApi {
    @PostMapping(value =APP_ROOT+"/entreprises/create",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    EntrepriseDto save(@RequestBody EntrepriseDto dto);

    @GetMapping(value =APP_ROOT+"/entreprises/{idEntreprise}",produces = MediaType.APPLICATION_JSON_VALUE)
    EntrepriseDto findById(@PathVariable("idEntreprise") Integer id);

    @GetMapping(value =APP_ROOT+"/entreprises/{nom}",produces = MediaType.APPLICATION_JSON_VALUE)
    EntrepriseDto findByNom(@PathVariable("nom") String nom);

    @GetMapping(value =APP_ROOT+"/entreprises/{adresse}",produces = MediaType.APPLICATION_JSON_VALUE)
    EntrepriseDto findByAdresse(@PathVariable("adresse") String adresse);

    @GetMapping(value =APP_ROOT+"/entreprises/{code}",produces = MediaType.APPLICATION_JSON_VALUE)
    EntrepriseDto findByCodeFiscal(@PathVariable("code") String code);

    @GetMapping(value =APP_ROOT+"/entreprises/all",produces = MediaType.APPLICATION_JSON_VALUE)
    List<EntrepriseDto> findAll();

    @DeleteMapping(value =APP_ROOT+"/entreprises/delete/{idEntreprise}" )
    void deleteById(@PathVariable("idEntreprise") Integer id);
}
