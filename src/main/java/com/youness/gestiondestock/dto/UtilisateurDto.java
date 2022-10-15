package com.youness.gestiondestock.dto;

import com.youness.gestiondestock.model.Adresse;
import com.youness.gestiondestock.model.Entreprise;
import com.youness.gestiondestock.model.Roles;
import com.youness.gestiondestock.model.Utilisateur;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.Instant;
import java.util.List;

@Data
@Builder
public class UtilisateurDto {
    private Integer id;

    private String nom;

    private String prenom;

    private String mail;

    private Instant dateDeNaissance;

    private String motDePasse;

    private AdresseDto adresse;

    private String photo;

    private EntrepriseDto entreprise;

    private Integer idEntreprise;

    private List<RolesDto> roles;

    public static UtilisateurDto fromEntity(Utilisateur utilisateur) {
        if (utilisateur==null){
            return null;
        }
        return UtilisateurDto.builder()
                .id(utilisateur.getId())
                .nom(utilisateur.getNom())
                .prenom(utilisateur.getPrenom())
                .adresse(AdresseDto.fromEntity(utilisateur.getAdresse()))
                .dateDeNaissance(utilisateur.getDateDeNaissance())
                .entreprise(EntrepriseDto.fromEntity(utilisateur.getEntreprise()))
                .photo(utilisateur.getPhoto())
                .motDePasse(utilisateur.getMotDePasse())
                .mail(utilisateur.getMail())
                .idEntreprise(utilisateur.getIdEntreprise())
                .build();

    }

    public static Utilisateur toEntity(UtilisateurDto utilisateurDto) {
        if (utilisateurDto==null){
            return null;
        }
        Utilisateur utilisateur=new Utilisateur();
        utilisateur.setId(utilisateurDto.getId());
        utilisateur.setNom(utilisateurDto.getNom());
        utilisateur.setPrenom(utilisateurDto.getPrenom());
        utilisateur.setAdresse(AdresseDto.toEntity(utilisateurDto.getAdresse()));
        utilisateur.setDateDeNaissance(utilisateurDto.getDateDeNaissance());
        utilisateur.setEntreprise(EntrepriseDto.toEntity(utilisateurDto.getEntreprise()));
        utilisateur.setMail(utilisateurDto.getMail());
        utilisateur.setMotDePasse(utilisateurDto.getMotDePasse());
        utilisateur.setPhoto(utilisateurDto.getPhoto());
        utilisateur.setIdEntreprise(utilisateurDto.getIdEntreprise());

        return utilisateur;
    }
}
