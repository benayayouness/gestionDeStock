package com.youness.gestiondestock.controller.api;

import com.youness.gestiondestock.dto.CommandeClientDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.youness.gestiondestock.utils.Constants.APP_ROOT;

public interface CommandeClientApi {
    @PostMapping(value =APP_ROOT+"/commandesclients/create",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CommandeClientDto> save(@RequestBody CommandeClientDto dto);

    @GetMapping(value =APP_ROOT+"/commandesclients/{idCommandeClient}",produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CommandeClientDto> findById(@PathVariable("idCommandeClient") Integer id);

    @GetMapping(value =APP_ROOT+"/commandesclients/{code}",produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CommandeClientDto> findByCode(@PathVariable("code") String code);

    @GetMapping(value =APP_ROOT+"/commandesclients/all",produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<CommandeClientDto>> findAll();

    @DeleteMapping(value =APP_ROOT+"/commandesclients/delete/{idCommandeClient}")
    ResponseEntity deleteById(@PathVariable("idCommandeClient") Integer id);
}
