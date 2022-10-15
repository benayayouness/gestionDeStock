package com.youness.gestiondestock.services.impl;

import com.youness.gestiondestock.dto.ClientDto;
import com.youness.gestiondestock.dto.FournisseurDto;
import com.youness.gestiondestock.exception.EntityNotFoundException;
import com.youness.gestiondestock.exception.ErrorCodes;
import com.youness.gestiondestock.exception.InvalidEntityException;
import com.youness.gestiondestock.model.Client;
import com.youness.gestiondestock.model.Fournisseur;
import com.youness.gestiondestock.repository.FournisseurRepository;
import com.youness.gestiondestock.services.FournisseurService;
import com.youness.gestiondestock.validator.ClientValidator;
import com.youness.gestiondestock.validator.FournisseurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FournisseurServiceImpl implements FournisseurService {
    private FournisseurRepository fournisseurRepository;

    @Autowired
    public FournisseurServiceImpl(FournisseurRepository fournisseurRepository) {
        this.fournisseurRepository = fournisseurRepository;
    }

    @Override
    public FournisseurDto save(FournisseurDto dto) {
        List<String> errors= FournisseurValidator.validate(dto);
        if (!errors.isEmpty()){
            log.error("Fournisseur is not valid {}",dto);
            throw new InvalidEntityException("Le fournisseur n'est pas valide",
                    ErrorCodes.FOURNISSEUR_NOT_VALID,errors);
        }
        return FournisseurDto.fromEntity(
                fournisseurRepository.save(
                        FournisseurDto.toEntity(dto))
        );
    }

    @Override
    public FournisseurDto findById(Integer id) {
        if (id==null){
            log.error("Fournisseur ID is null");
            return null;
        }
        Optional<Fournisseur> fournisseur=fournisseurRepository.findById(id);

        FournisseurDto dto=FournisseurDto.fromEntity(fournisseur.get());

        return Optional.of(dto).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucun fournisseur avec l'ID = " + id + "n'est trouve dans la BDD",
                        ErrorCodes.FOURNISSEUR_NOT_FOUND
                ));
    }

    @Override
    public FournisseurDto findByNomAndPrenom(String nom, String prenom) {
        if (!StringUtils.hasLength(nom) || !StringUtils.hasLength(prenom)){
            log.error("Fournisseur Fisrt or Last NAME is null");
            return null;
        }
        Optional<Fournisseur> fournisseur=fournisseurRepository.findFournisseurByNomAndPrenom(nom,prenom);

        FournisseurDto dto=FournisseurDto.fromEntity(fournisseur.get());

        return Optional.of(dto).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucun Fournisseur avec le nom = " + nom + "et le prenom " +prenom + "n'est trouve dans la BDD",
                        ErrorCodes.FOURNISSEUR_NOT_FOUND
                ));
    }

    @Override
    public FournisseurDto findByAdresse(String adresse) {
        if (!StringUtils.hasLength(adresse)){
            log.error("Adresse is null");
            return null;
        }
        Optional<Fournisseur> fournisseur=fournisseurRepository.findByAdresse_Adresse1(adresse);

        FournisseurDto dto=FournisseurDto.fromEntity(fournisseur.get());

        return Optional.of(dto).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucun Fournisseur avec l'adresse :" + adresse + "n'est trouve dans la BDD",
                        ErrorCodes.FOURNISSEUR_NOT_FOUND
                ));
    }

    @Override
    public List<FournisseurDto> findAll() {
        return fournisseurRepository.findAll().stream()
                .map(FournisseurDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Integer id) {
        if (id==null){
            log.error("Fournisseur ID is null");
            return;
        }
        fournisseurRepository.deleteById(id);

    }
}
