package com.youness.gestiondestock.dto;

import com.youness.gestiondestock.model.Article;
import com.youness.gestiondestock.model.LigneVente;
import com.youness.gestiondestock.model.Ventes;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Data
@Builder
public class LigneVenteDto {
    private Integer id;

    private BigDecimal quantite;

    private BigDecimal prixUnitaire;

    private ArticleDto article;

    private VentesDto vente;

    private Integer idEntreprise;

    public static LigneVenteDto fromEntity(LigneVente ligneVente){
        if (ligneVente==null){
            return null;
        }
        return LigneVenteDto.builder()
                .id(ligneVente.getId())
                .quantite(ligneVente.getQuantite())
                .prixUnitaire(ligneVente.getPrixUnitaire())
                .article(ArticleDto.fromEntity(ligneVente.getArticle()))
                .vente(VentesDto.fromEntity(ligneVente.getVente()))
                .idEntreprise(ligneVente.getIdEntreprise())
                .build();
    }
    public static LigneVente toEntity(LigneVenteDto ligneVenteDto){
        if (ligneVenteDto==null){
            return null;
        }
        LigneVente ligneVente=new LigneVente();
        ligneVente.setId(ligneVenteDto.getId());
        ligneVente.setQuantite(ligneVenteDto.getQuantite());
        ligneVente.setPrixUnitaire(ligneVenteDto.getPrixUnitaire());
        ligneVente.setArticle(ArticleDto.toEntity(ligneVenteDto.getArticle()));
        ligneVente.setVente(VentesDto.toEntity(ligneVenteDto.getVente()));
        ligneVente.setIdEntreprise(ligneVenteDto.getIdEntreprise());
        return ligneVente;

    }
}
