package com.youness.gestiondestock.dto;

import com.youness.gestiondestock.model.Adresse;
import com.youness.gestiondestock.model.Client;
import com.youness.gestiondestock.model.CommandeClient;
import lombok.Builder;
import lombok.Data;


import java.util.List;

@Data
@Builder
public class ClientDto {
    private Integer id;

    private String nom;

    private String prenom;


    private AdresseDto adresse;

    private String photo;

    private String mail;

    private String numTel;

    private Integer idEntreprise;

    private List<CommandeClientDto> commandeClients;

    public static ClientDto fromEntity(Client client){
        if (client==null){
            return null;
        }
        return ClientDto.builder()
                .id(client.getId())
                .adresse(AdresseDto.fromEntity(client.getAdresse()))
                .mail(client.getMail())
                .nom(client.getNom())
                .prenom(client.getPrenom())
                .numTel(client.getNumTel())
                .photo(client.getPhoto())
                .idEntreprise(client.getIdEntreprise())
                .build();
    }
    public static Client toEntity(ClientDto clientDto){
        if (clientDto==null){
            return null;
        }

        Client client=new Client();
        client.setId(clientDto.getId());
        client.setNom(clientDto.getNom());
        client.setPrenom(clientDto.getPrenom());
        client.setAdresse(AdresseDto.toEntity(clientDto.getAdresse()));
        client.setMail(clientDto.getMail());
        client.setNumTel(clientDto.getNumTel());
        client.setPhoto(clientDto.getPhoto());
        client.setIdEntreprise(clientDto.getIdEntreprise());

        return client;
    }
}
