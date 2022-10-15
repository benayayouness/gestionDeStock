package com.youness.gestiondestock.controller.api;

import com.youness.gestiondestock.dto.VentesDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.youness.gestiondestock.utils.Constants.APP_ROOT;

public interface VentesApi {
    @PostMapping(value =APP_ROOT+"/ventes/create",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    VentesDto save(@RequestBody VentesDto dto);

    @GetMapping(value =APP_ROOT+"/ventes/{idVentes}",produces = MediaType.APPLICATION_JSON_VALUE)
    VentesDto findById(@PathVariable("idVentes") Integer id);

    @GetMapping(value =APP_ROOT+"/ventes/{code}",produces = MediaType.APPLICATION_JSON_VALUE)
    VentesDto findByCode(@PathVariable("code") String code);

    @GetMapping(value =APP_ROOT+"/ventes/{date}",produces = MediaType.APPLICATION_JSON_VALUE)
    VentesDto findByDateVente(@PathVariable("date") String date);

    @GetMapping(value =APP_ROOT+"/ventes/all",produces = MediaType.APPLICATION_JSON_VALUE)
    List<VentesDto> findAll();

    @DeleteMapping(value =APP_ROOT+"/ventes/delete/{idVentes}" )
    void deleteById(@PathVariable("idVentes") Integer id);
}
