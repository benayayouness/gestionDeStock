package com.youness.gestiondestock.controller.api;

import com.youness.gestiondestock.dto.FournisseurDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.youness.gestiondestock.utils.Constants.APP_ROOT;

public interface FournisseurApi {
    @PostMapping(value =APP_ROOT+"/fournisseurs/create",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    FournisseurDto save(@RequestBody FournisseurDto dto);

    @GetMapping(value =APP_ROOT+"/fournisseurs/{idFournisseur}",produces = MediaType.APPLICATION_JSON_VALUE)
    FournisseurDto findById(@PathVariable("idFournisseur") Integer id);

    @GetMapping(value =APP_ROOT+"/fournisseurs/{nom}/{prenom}",produces = MediaType.APPLICATION_JSON_VALUE)
    FournisseurDto findByNomAndPrenom(@PathVariable("nom") String nom,@PathVariable("prenom") String prenom);

    @GetMapping(value =APP_ROOT+"/fournisseurs/{adresse}",produces = MediaType.APPLICATION_JSON_VALUE)
    FournisseurDto findByAdresse(@PathVariable("adresse") String adresse);

    @GetMapping(value =APP_ROOT+"/fournisseurs/all",produces = MediaType.APPLICATION_JSON_VALUE)
    List<FournisseurDto> findAll();

    @DeleteMapping(value =APP_ROOT+"/fournisseurs/delete/{idFournisseur}" )
    void deleteById(@PathVariable("idFournisseur") Integer id);
}
