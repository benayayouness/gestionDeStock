package com.youness.gestiondestock.controller.api;

import com.youness.gestiondestock.dto.UtilisateurDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.youness.gestiondestock.utils.Constants.APP_ROOT;

public interface UtilisateurApi {
    @PostMapping(value =APP_ROOT+"/utilisateurs/create",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    UtilisateurDto save(@RequestBody UtilisateurDto dto);

    @GetMapping(value =APP_ROOT+"/utilisateurs/{idUtilisateur}",produces = MediaType.APPLICATION_JSON_VALUE)
    UtilisateurDto findById(@PathVariable("idUtilisateur") Integer id);

    @GetMapping(value =APP_ROOT+"/utilisateurs/{nom}/{prenom}",produces = MediaType.APPLICATION_JSON_VALUE)
    UtilisateurDto findByNomAndPrenom(@PathVariable("nom") String nom,@PathVariable("prenom") String prenom);

    @GetMapping(value =APP_ROOT+"/utilisateurs/{date}",produces = MediaType.APPLICATION_JSON_VALUE)
    UtilisateurDto findByDateDeNaissance(@PathVariable("date") String date);

    @GetMapping(value =APP_ROOT+"/utilisateurs/{adresse}",produces = MediaType.APPLICATION_JSON_VALUE)
    UtilisateurDto findByAdresse(@PathVariable("adresse") String adresse);

    @GetMapping(value =APP_ROOT+"/utilisateurs/all",produces = MediaType.APPLICATION_JSON_VALUE)
    List<UtilisateurDto> findAll();

    @DeleteMapping(value =APP_ROOT+"/utilisateurs/delete/{idUtilisateur}" )
    void deleteById(@PathVariable("idUtilisateur") Integer id);
}
