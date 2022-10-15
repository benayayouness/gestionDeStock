package com.youness.gestiondestock.services.impl;

import com.youness.gestiondestock.dto.ClientDto;
import com.youness.gestiondestock.dto.EntrepriseDto;
import com.youness.gestiondestock.exception.EntityNotFoundException;
import com.youness.gestiondestock.exception.ErrorCodes;
import com.youness.gestiondestock.exception.InvalidEntityException;
import com.youness.gestiondestock.model.Client;
import com.youness.gestiondestock.model.Entreprise;
import com.youness.gestiondestock.repository.EntrepriseRepository;
import com.youness.gestiondestock.services.EntrepriseService;
import com.youness.gestiondestock.validator.ClientValidator;
import com.youness.gestiondestock.validator.EntrepriseValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EntrepriseServiceImpl implements EntrepriseService {
    private EntrepriseRepository entrepriseRepository;

    @Autowired
    public EntrepriseServiceImpl(EntrepriseRepository entrepriseRepository) {
        this.entrepriseRepository = entrepriseRepository;
    }

    @Override
    public EntrepriseDto save(EntrepriseDto dto) {
        List<String> errors= EntrepriseValidator.validate(dto);
        if (!errors.isEmpty()){
            log.error("Entreprise is not valid {}",dto);
            throw new InvalidEntityException("L'entreprise n'est pas valide", ErrorCodes.ENTREPRISE_NOT_VALID,errors);
        }
        return EntrepriseDto.fromEntity(
                entrepriseRepository.save(
                        EntrepriseDto.toEntity(dto))
        );
    }

    @Override
    public EntrepriseDto findById(Integer id) {
        if (id==null){
            log.error("Entreprise ID is null");
            return null;
        }
        Optional<Entreprise> entreprise=entrepriseRepository.findById(id);

        EntrepriseDto dto=EntrepriseDto.fromEntity(entreprise.get());

        return Optional.of(dto).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucune entreprise avec l'ID = " + id + "n'est trouve dans la BDD",
                        ErrorCodes.ENTREPRISE_NOT_FOUND
                ));
    }

    @Override
    public EntrepriseDto findByNom(String nom) {
        if (!StringUtils.hasLength(nom)){
            log.error("Name is null");
            return null;
        }
        Optional<Entreprise> entreprise=entrepriseRepository.findByNom(nom);

        EntrepriseDto dto=EntrepriseDto.fromEntity(entreprise.get());

        return Optional.of(dto).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucune entreprise avec le nom :" + nom + "n'est trouve dans la BDD",
                        ErrorCodes.ENTREPRISE_NOT_FOUND
                ));
    }

    @Override
    public EntrepriseDto findByAdresse(String adresse) {
        if (!StringUtils.hasLength(adresse)){
            log.error("Adresse is null");
            return null;
        }
        Optional<Entreprise> entreprise=entrepriseRepository.findByAdresse_Adresse1(adresse);

        EntrepriseDto dto=EntrepriseDto.fromEntity(entreprise.get());

        return Optional.of(dto).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucune entreprise avec l'adresse :" + adresse + "n'est trouve dans la BDD",
                        ErrorCodes.ENTREPRISE_NOT_FOUND
                ));
    }

    @Override
    public EntrepriseDto findByCodeFiscal(String code) {
        if (!StringUtils.hasLength(code)){
            log.error("CODE FISCAL is null");
            return null;
        }
        Optional<Entreprise> entreprise=entrepriseRepository.findByCodeFiscal(code);

        EntrepriseDto dto=EntrepriseDto.fromEntity(entreprise.get());

        return Optional.of(dto).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucune entreprise avec le Code fiscal :" + code + "n'est trouve dans la BDD",
                        ErrorCodes.ENTREPRISE_NOT_FOUND
                ));
    }

    @Override
    public List<EntrepriseDto> findAll() {
        return entrepriseRepository.findAll().stream()
                .map(EntrepriseDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Integer id) {
        if (id==null){
            log.error("Entreprise ID is null");
            return;
        }
        entrepriseRepository.deleteById(id);
    }
}
