package com.youness.gestiondestock.services.impl;

import com.youness.gestiondestock.dto.ClientDto;
import com.youness.gestiondestock.dto.UtilisateurDto;
import com.youness.gestiondestock.exception.EntityNotFoundException;
import com.youness.gestiondestock.exception.ErrorCodes;
import com.youness.gestiondestock.exception.InvalidEntityException;
import com.youness.gestiondestock.model.Client;
import com.youness.gestiondestock.model.Utilisateur;
import com.youness.gestiondestock.repository.UtilisateurRepository;
import com.youness.gestiondestock.services.UtilisateurService;
import com.youness.gestiondestock.validator.ClientValidator;
import com.youness.gestiondestock.validator.UtilisateurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UtilisateurServiceImpl implements UtilisateurService {
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public UtilisateurDto save(UtilisateurDto dto) {
        List<String> errors= UtilisateurValidator.validate(dto);
        if (!errors.isEmpty()){
            log.error("Utilisateur is not valid {}",dto);
            throw new InvalidEntityException("L'utilisateur n'est pas valide", ErrorCodes.UTILISATEUR_NOT_VALID,errors);
        }
        return UtilisateurDto.fromEntity(
                utilisateurRepository.save(
                        UtilisateurDto.toEntity(dto))
        );
    }

    @Override
    public UtilisateurDto findById(Integer id) {
        if (id==null){
            log.error("Utilisateur ID is null");
            return null;
        }
        Optional<Utilisateur> utilisateur=utilisateurRepository.findById(id);

        UtilisateurDto dto=UtilisateurDto.fromEntity(utilisateur.get());

        return Optional.of(dto).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucun utilisateur avec l'ID = " + id + "n'est trouve dans la BDD",
                        ErrorCodes.UTILISATEUR_NOT_FOUND
                ));
    }

    @Override
    public UtilisateurDto findByNomAndPrenom(String nom, String prenom) {
        if (!StringUtils.hasLength(nom) || !StringUtils.hasLength(prenom)){
            log.error("User Fisrt or Last NAME is null");
            return null;
        }
        Optional<Utilisateur> utilisateur=utilisateurRepository.findUtilisateurByNomAndPrenom(nom,prenom);

        UtilisateurDto dto=UtilisateurDto.fromEntity(utilisateur.get());

        return Optional.of(dto).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucun utilisateur avec le nom = " + nom + "et le prenom " +prenom + "n'est trouve dans la BDD",
                        ErrorCodes.UTILISATEUR_NOT_FOUND
                ));
    }

    @Override
    public UtilisateurDto findByDateDeNaissance(String date) {
        if (!StringUtils.hasLength(date)){
            log.error("Date is null");
            return null;
        }
        Optional<Utilisateur> utilisateur=utilisateurRepository.findByDateDeNaissance(date);

        UtilisateurDto dto=UtilisateurDto.fromEntity(utilisateur.get());

        return Optional.of(dto).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucun utilisateur avec la date de naissance :" + date + "n'est trouve dans la BDD",
                        ErrorCodes.UTILISATEUR_NOT_FOUND
                ));
    }

    @Override
    public UtilisateurDto findByAdresse(String adresse) {
        if (!StringUtils.hasLength(adresse)){
            log.error("Adresse is null");
            return null;
        }
        Optional<Utilisateur> utilisateur=utilisateurRepository.findByAdresse_Adresse1(adresse);

        UtilisateurDto dto=UtilisateurDto.fromEntity(utilisateur.get());

        return Optional.of(dto).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucun utilisateur avec l'adresse' :" + adresse + "n'est trouve dans la BDD",
                        ErrorCodes.UTILISATEUR_NOT_FOUND
                ));
    }

    @Override
    public List<UtilisateurDto> findAll() {
        return utilisateurRepository.findAll().stream()
                .map(UtilisateurDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Integer id) {
        if (id==null){
            log.error("User ID is null");
            return;
        }
        utilisateurRepository.deleteById(id);
    }
}
