package com.youness.gestiondestock.model;

import com.youness.gestiondestock.dto.AdresseDto;
import lombok.*;

import javax.persistence.*;
import java.util.List;
//@Builder
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "entreprise")
public class Entreprise extends AbstractEntity {
    @Column(name = "nom")
    private String nom;

    @Column(name = "description")
    private String description;

    @Embedded
    private Adresse adresse;

    @Column(name = "codefiscal")
    private String codeFiscal;

    @Column(name = "photo")
    private String photo;

    @Column(name = "numtel")
    private String numTel;

    @Column(name = "siteweb")
    private String siteWeb;

    @OneToMany(mappedBy = "entreprise")
    private List<Article> articles;

    @OneToMany(mappedBy = "entreprise")
    private List<Utilisateur> utilisateurs;



}
