package com.youness.gestiondestock.services.impl;

import com.youness.gestiondestock.dto.ArticleDto;
import com.youness.gestiondestock.dto.CategorieDto;
import com.youness.gestiondestock.dto.ClientDto;
import com.youness.gestiondestock.exception.EntityNotFoundException;
import com.youness.gestiondestock.exception.ErrorCodes;
import com.youness.gestiondestock.exception.InvalidEntityException;
import com.youness.gestiondestock.model.Adresse;
import com.youness.gestiondestock.model.Article;
import com.youness.gestiondestock.model.Client;
import com.youness.gestiondestock.repository.ClientRepository;
import com.youness.gestiondestock.services.ClientService;
import com.youness.gestiondestock.validator.ArticleValidator;
import com.youness.gestiondestock.validator.ClientValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ClientServiceImpl implements ClientService {
    private ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public ClientDto save(ClientDto dto) {
        List<String> errors= ClientValidator.validate(dto);
        if (!errors.isEmpty()){
            log.error("Client is not valid {}",dto);
            throw new InvalidEntityException("Le client n'est pas valide", ErrorCodes.CLIENT_NOT_VALID,errors);
        }
        return ClientDto.fromEntity(
                clientRepository.save(
                        ClientDto.toEntity(dto))
        );
    }

    @Override
    public ClientDto findById(Integer id) {
        if (id==null){
            log.error("Client ID is null");
            return null;
        }
        Optional<Client> client=clientRepository.findById(id);

        ClientDto dto=ClientDto.fromEntity(client.get());

        return Optional.of(dto).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucun client avec l'ID = " + id + "n'est trouve dans la BDD",
                        ErrorCodes.CLIENT_NOT_FOUND
                ));
    }

    @Override
    public ClientDto findByNomAndPrenom(String nom, String prenom) {
        if (!StringUtils.hasLength(nom) || !StringUtils.hasLength(prenom)){
            log.error("Client Fisrt or Last NAME is null");
            return null;
        }
        Optional<Client> client=clientRepository.findClientByNomAndPrenom(nom,prenom);

        ClientDto dto=ClientDto.fromEntity(client.get());

        return Optional.of(dto).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucun client avec le nom = " + nom + "et le prenom " +prenom + "n'est trouve dans la BDD",
                        ErrorCodes.CLIENT_NOT_FOUND
                ));

    }

    @Override
    public ClientDto findByAdresse(String adresse) {
        if (!StringUtils.hasLength(adresse)){
            log.error("Adresse is null");
            return null;
        }
        Optional<Client> client=clientRepository.findByAdresse_Adresse1(adresse);

        ClientDto dto=ClientDto.fromEntity(client.get());

        return Optional.of(dto).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucun client avec l'adresse :" + adresse + "n'est trouve dans la BDD",
                        ErrorCodes.CLIENT_NOT_FOUND
                ));
    }

    @Override
    public List<ClientDto> findAll() {
        return clientRepository.findAll().stream()
                .map(ClientDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Integer id) {
        if (id==null){
            log.error("Client ID is null");
            return;
        }
        clientRepository.deleteById(id);
    }
}
