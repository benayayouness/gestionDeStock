package com.youness.gestiondestock.services.impl;

import com.youness.gestiondestock.dto.ClientDto;
import com.youness.gestiondestock.dto.LigneVenteDto;
import com.youness.gestiondestock.dto.VentesDto;
import com.youness.gestiondestock.exception.EntityNotFoundException;
import com.youness.gestiondestock.exception.ErrorCodes;
import com.youness.gestiondestock.exception.InvalidEntityException;
import com.youness.gestiondestock.model.Article;
import com.youness.gestiondestock.model.Client;
import com.youness.gestiondestock.model.LigneVente;
import com.youness.gestiondestock.model.Ventes;
import com.youness.gestiondestock.repository.ArticleRepository;
import com.youness.gestiondestock.repository.LigneVenteRepository;
import com.youness.gestiondestock.repository.VentesRepository;
import com.youness.gestiondestock.services.VentesService;
import com.youness.gestiondestock.validator.ClientValidator;
import com.youness.gestiondestock.validator.VentesValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class VentesServiceImpl implements VentesService {
    private VentesRepository ventesRepository;
    private LigneVenteRepository ligneVenteRepository;
    private ArticleRepository articleRepository;

    @Autowired
    public VentesServiceImpl(VentesRepository ventesRepository, LigneVenteRepository ligneVenteRepository, ArticleRepository articleRepository) {
        this.ventesRepository = ventesRepository;
        this.ligneVenteRepository = ligneVenteRepository;
        this.articleRepository = articleRepository;
    }

    @Override
    public VentesDto save(VentesDto dto) {
        List<String> errors= VentesValidator.validate(dto);
        if (!errors.isEmpty()){
            log.error("Vente is not valid {}",dto);
            throw new InvalidEntityException(" Vente n'est pas valide", ErrorCodes.VENTE_NOT_VALID,errors);
        }

        List<String> articleErrors=new ArrayList<>();
        if(dto.getLigneVentes()!=null){
            dto.getLigneVentes().forEach(ligneVenteDto ->{
                if(ligneVenteDto.getArticle()!=null){
                    Optional<Article> article=articleRepository.findById(ligneVenteDto.getArticle().getId());
                    if (article.isEmpty()){
                        articleErrors.add("L'article avec l'ID"+ligneVenteDto.getArticle().getId()+"n'existe pas");
                    }
                    else{
                        articleErrors.add("Impossible d'enregistrer une vente avec un article NULL");
                    }
                }
            });
        }
        if (!articleErrors.isEmpty()){
            log.error("One or more Article were not found in the DB,{}",errors);
            throw new InvalidEntityException("un ou plusieurs Article n'ont pas ete trouve dans la BD",ErrorCodes.VENTE_NOT_FOUND,articleErrors);
        }
        Ventes savedVentes=ventesRepository.save(VentesDto.toEntity(dto));
        dto.getLigneVentes().forEach(ligneVenteDto -> {
            LigneVente ligneVente= LigneVenteDto.toEntity(ligneVenteDto);
            ligneVente.setVente(savedVentes);
            ligneVenteRepository.save(ligneVente);
        });
        return VentesDto.fromEntity(savedVentes);

    }

    @Override
    public VentesDto findById(Integer id) {
        if (id==null){
            log.error("Vente ID is null");
            return null;
        }
        Optional<Ventes> ventes=ventesRepository.findById(id);

        VentesDto dto=VentesDto.fromEntity(ventes.get());

        return Optional.of(dto).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucune vente avec l'ID = " + id + "n'est trouve dans la BDD",
                        ErrorCodes.VENTE_NOT_FOUND
                ));
    }

    @Override
    public VentesDto findByCode(String code) {
        if (!StringUtils.hasLength(code)){
            log.error("Ventes CODE is null");
            return null;
        }
        Optional<Ventes> ventes=ventesRepository.findVentesByCode(code);

        VentesDto dto=VentesDto.fromEntity(ventes.get());

        return Optional.of(dto).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucune vente avec le code :" + code + "n'est trouve dans la BDD",
                        ErrorCodes.VENTE_NOT_FOUND
                ));
    }

    @Override
    public VentesDto findByDateVente(String date) {
        if (!StringUtils.hasLength(date)){
            log.error("Ventes Date is null");
            return null;
        }
        Optional<Ventes> ventes=ventesRepository.findByDateVente(date);

        VentesDto dto=VentesDto.fromEntity(ventes.get());

        return Optional.of(dto).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucune vente avec la date Vente :" + date + "n'est trouve dans la BDD",
                        ErrorCodes.VENTE_NOT_FOUND
                ));
    }

    @Override
    public List<VentesDto> findAll() {
        return ventesRepository.findAll().stream()
                .map(VentesDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Integer id) {
        if (id==null){
            log.error("Vente ID is null");
            return;
        }
        ventesRepository.deleteById(id);
    }
}
