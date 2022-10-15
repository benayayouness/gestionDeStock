package com.youness.gestiondestock.services.impl;

import com.youness.gestiondestock.dto.CommandeClientDto;
import com.youness.gestiondestock.dto.LigneCommandeClientDto;
import com.youness.gestiondestock.exception.ErrorCodes;
import com.youness.gestiondestock.exception.InvalidEntityException;
import com.youness.gestiondestock.model.Article;
import com.youness.gestiondestock.model.Client;
import com.youness.gestiondestock.model.CommandeClient;
import com.youness.gestiondestock.model.LigneCommandeClient;
import com.youness.gestiondestock.repository.ArticleRepository;
import com.youness.gestiondestock.repository.ClientRepository;
import com.youness.gestiondestock.repository.CommandeClientRepository;
import com.youness.gestiondestock.repository.LigneCommandeClientRepository;
import com.youness.gestiondestock.services.CommandeClientService;
import com.youness.gestiondestock.validator.CommandeClientValidator;
import com.youness.gestiondestock.exception.EntityNotFoundException;
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
public class CommandeClientServiceImpl implements CommandeClientService {
    private CommandeClientRepository commandeClientRepository;
    private ClientRepository clientRepository;
    private ArticleRepository articleRepository;
    private LigneCommandeClientRepository ligneCommandeClientRepository;

    @Autowired
    public CommandeClientServiceImpl(CommandeClientRepository commandeClientRepository,
                                     ClientRepository clientRepository,
                                     ArticleRepository articleRepository,
                                     LigneCommandeClientRepository ligneCommandeClientRepository) {
        this.commandeClientRepository = commandeClientRepository;
        this.clientRepository = clientRepository;
        this.articleRepository = articleRepository;
        this.ligneCommandeClientRepository=ligneCommandeClientRepository;
    }

    @Override
    public CommandeClientDto save(CommandeClientDto dto) {
        List<String> errors=CommandeClientValidator.validate(dto);
        if(!errors.isEmpty()){
            log.error("Commande Client n'est pas valide");
            throw new InvalidEntityException("La Commande Client n'est pas valide",ErrorCodes.COMMANDE_CLIENT_NOT_VALID,errors);
        }
        Optional<Client> client=clientRepository.findById(dto.getClient().getId());
        if(client.isEmpty()){
            log.warn("Client with ID {} was not found in the DB",dto.getClient().getId());
            throw new EntityNotFoundException("Aucun client avec l'ID"+dto.getClient().getId() + "n'est ete trouve dans la BDD",
                    ErrorCodes.CLIENT_NOT_FOUND);
        }
        List<String> articleErrors=new ArrayList<>();
        if(dto.getLigneCommandeClients()!=null){
            dto.getLigneCommandeClients().forEach(ligCmdClt ->{
                if(ligCmdClt.getArticle()!=null){
                    Optional<Article> article=articleRepository.findById(ligCmdClt.getArticle().getId());
                    if (article.isEmpty()){
                        articleErrors.add("L'article avec l'ID"+ligCmdClt.getArticle().getId()+"n'existe pas");
                    }
                    else{
                        articleErrors.add("Impossible d'enregistrer une commande avec un article NULL");
                    }
                }
            });
        }
        if (!articleErrors.isEmpty()){
            log.warn("Article  was not found in the DB");
            throw new InvalidEntityException("Article n'existe pas dans la BD",ErrorCodes.ARTICLE_NOT_FOUND,articleErrors);
        }
        CommandeClient savedCmdClt=commandeClientRepository.save(CommandeClientDto.toEntity(dto));
        if (dto.getLigneCommandeClients()!=null){
            dto.getLigneCommandeClients().forEach(ligCmdClt->{
                LigneCommandeClient ligneCommandeClient= LigneCommandeClientDto.toEntity(ligCmdClt);
                ligneCommandeClient.setCommandeClient(savedCmdClt);
                ligneCommandeClientRepository.save(ligneCommandeClient);
            });
        }
        return CommandeClientDto.fromEntity(savedCmdClt);

    }

    @Override
    public CommandeClientDto findById(Integer id) {
        if (id==null){
            log.error("Commande Client ID is NULL");
            return null;
        }

        return commandeClientRepository.findById(id)
                .map(CommandeClientDto::fromEntity)
                .orElseThrow(()->
                        new EntityNotFoundException("Aucune Commande Client avec l'ID"+id+"n'a ete trouve dans la BDD",
                                ErrorCodes.COMMANDE_CLIENT_NOT_FOUND));
    }

    @Override
    public CommandeClientDto findByCode(String code) {
        if (!StringUtils.hasLength(code)){
            log.error("Commande Client CODE is NULL");
            return null;
        }
        return commandeClientRepository.findCommandeClientByCode(code)
                .map(CommandeClientDto::fromEntity)
                .orElseThrow(()->
                        new EntityNotFoundException("Aucune Commande Client avec le CODE"+code+"n'a ete trouve dans la BDD",
                                ErrorCodes.COMMANDE_CLIENT_NOT_FOUND));
    }

    @Override
    public List<CommandeClientDto> findAll() {
        return commandeClientRepository.findAll().stream()
                .map(CommandeClientDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Integer id) {
        if (id==null){
            log.error("Commande Client ID is NULL");
            return;
        }
        commandeClientRepository.deleteById(id);
    }
}
