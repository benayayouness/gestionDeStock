package com.youness.gestiondestock.controller.api;

import com.youness.gestiondestock.dto.ClientDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.youness.gestiondestock.utils.Constants.APP_ROOT;

public interface ClientApi {
    @PostMapping(value =APP_ROOT+"/clients/create",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    ClientDto save(@RequestBody ClientDto dto);

    @GetMapping(value =APP_ROOT+"/clients/{idClient}",produces = MediaType.APPLICATION_JSON_VALUE)
    ClientDto findById(@PathVariable("idClient") Integer id);

    @GetMapping(value =APP_ROOT+"/clients/{nom}/{prenom}",produces = MediaType.APPLICATION_JSON_VALUE)
    ClientDto findByNomAndPrenom(@PathVariable("nom") String nom,@PathVariable("prenom") String prenom);

    @GetMapping(value =APP_ROOT+"/clients/{adresse}",produces = MediaType.APPLICATION_JSON_VALUE)
    ClientDto findByAdresse(@PathVariable("adresse") String adresse);

    @GetMapping(value =APP_ROOT+"/clients/all",produces = MediaType.APPLICATION_JSON_VALUE)
    List<ClientDto> findAll();

    @DeleteMapping(value =APP_ROOT+"/clients/delete/{idClient}" )
    void deleteById(@PathVariable("idClient") Integer id);
}
