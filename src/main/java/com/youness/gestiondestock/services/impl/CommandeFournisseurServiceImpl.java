package com.youness.gestiondestock.services.impl;

import com.youness.gestiondestock.dto.CommandeFournisseurDto;
import com.youness.gestiondestock.dto.LigneCommandeFournisseurDto;
import com.youness.gestiondestock.exception.EntityNotFoundException;
import com.youness.gestiondestock.exception.ErrorCodes;
import com.youness.gestiondestock.exception.InvalidEntityException;
import com.youness.gestiondestock.model.Article;
import com.youness.gestiondestock.model.CommandeFournisseur;
import com.youness.gestiondestock.model.Fournisseur;
import com.youness.gestiondestock.model.LigneCommandeFournisseur;
import com.youness.gestiondestock.repository.ArticleRepository;
import com.youness.gestiondestock.repository.CommandeFournisseurRepository;
import com.youness.gestiondestock.repository.FournisseurRepository;
import com.youness.gestiondestock.repository.LigneCommandeFournisseurRepository;
import com.youness.gestiondestock.services.CommandeFournisseurService;
import com.youness.gestiondestock.validator.CommandeFournisseurValidator;
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
public class CommandeFournisseurServiceImpl implements CommandeFournisseurService {
    private CommandeFournisseurRepository commandeFournisseurRepository;
    private ArticleRepository articleRepository;
    private FournisseurRepository fournisseurRepository;
    private LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository;

    @Autowired
    public CommandeFournisseurServiceImpl(CommandeFournisseurRepository commandeFournisseurRepository, ArticleRepository articleRepository, FournisseurRepository fournisseurRepository, LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository) {
        this.commandeFournisseurRepository = commandeFournisseurRepository;
        this.articleRepository = articleRepository;
        this.fournisseurRepository = fournisseurRepository;
        this.ligneCommandeFournisseurRepository = ligneCommandeFournisseurRepository;
    }

    @Override
    public CommandeFournisseurDto save(CommandeFournisseurDto dto) {
        List<String> errors= CommandeFournisseurValidator.validate(dto);
        if (!errors.isEmpty()){
            log.error("Commande Fourniseur n'est pas valide");
            throw new InvalidEntityException("Commande Fournisseur n'est pas valide", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_VALID,errors);
        }
        Optional<Fournisseur> fournisseur=fournisseurRepository.findById(dto.getFournisseur().getId());
        if (fournisseur.isEmpty()){
            log.warn("Fournisseur with ID {} was not found",dto.getFournisseur().getId());
            throw new EntityNotFoundException("Aucun Fournisseur avec l'ID"+dto.getFournisseur().getId()+"n'a pas ete trouve dans la BDD",
                    ErrorCodes.FOURNISSEUR_NOT_FOUND);
        }
        List<String> articleErrors=new ArrayList<>();
        if (dto.getLigneCommandeFournisseurs()!=null){
            dto.getLigneCommandeFournisseurs().forEach(ligCmdFour->{
                if (ligCmdFour.getArticle()!=null){
                    Optional<Article> article=articleRepository.findById(ligCmdFour.getArticle().getId());
                    if (article.isEmpty()){
                        articleErrors.add("L'article avec l'ID"+ligCmdFour.getArticle().getId()+"n'existe pas");
                    }
                    else {
                        articleErrors.add("Impossible d'enregistrer une commande avec un article NULL");
                    }
                }
            });
        }
        if (!articleErrors.isEmpty()){
            log.warn("Article was not found in the DB");
            throw new InvalidEntityException("Article n'existe pas dans la BD",ErrorCodes.ARTICLE_NOT_FOUND,articleErrors);
        }
        CommandeFournisseur savedCmdFour=commandeFournisseurRepository.save(CommandeFournisseurDto.toEntity(dto));
        if (dto.getLigneCommandeFournisseurs()!=null){
            dto.getLigneCommandeFournisseurs().forEach(ligCmdFour->{
                LigneCommandeFournisseur ligneCommandeFournisseur= LigneCommandeFournisseurDto.toEntity(ligCmdFour);
                ligneCommandeFournisseur.setCommandeFournisseur(savedCmdFour);
                ligneCommandeFournisseurRepository.save(ligneCommandeFournisseur);
            });
        }
        return CommandeFournisseurDto.fromEntity(savedCmdFour);
    }

    @Override
    public CommandeFournisseurDto findById(Integer id) {
        if (id==null){
            log.error("Commande Fournisseur ID is NULL");
            return null;
        }
        return commandeFournisseurRepository.findById(id)
                .map(CommandeFournisseurDto::fromEntity)
                .orElseThrow(()->
                    new EntityNotFoundException("Acune Commande Fournisseur avec l'ID"+id+"n'a ete trouve dans la BDD",
                            ErrorCodes.COMMANDE_FOURNISSEUR_NOT_FOUND));

    }

    @Override
    public CommandeFournisseurDto findByCode(String code) {
        if (!StringUtils.hasLength(code)){
            log.error("Commande Fournisseur CODE is NULL");
            return null;
        }
        return commandeFournisseurRepository.findCommandeFournisseurByCode(code)
                .map(CommandeFournisseurDto::fromEntity)
                .orElseThrow(()->
                        new EntityNotFoundException("Aucune Commande Fournisseur avec le CODE"+code+"n'a ete trouve dans la BDD",
                                ErrorCodes.COMMANDE_FOURNISSEUR_NOT_FOUND));
    }

    @Override
    public List<CommandeFournisseurDto> findAll() {
        return commandeFournisseurRepository.findAll().stream()
                .map(CommandeFournisseurDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Integer id) {
        if (id==null){
            log.error("Commande Fournisseur ID is NULL");
            return;
        }
        commandeFournisseurRepository.deleteById(id);

    }
}
