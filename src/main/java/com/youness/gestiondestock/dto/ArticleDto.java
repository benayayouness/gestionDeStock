package com.youness.gestiondestock.dto;

import com.youness.gestiondestock.model.Article;
import com.youness.gestiondestock.model.Categorie;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Data
public class ArticleDto {
    private Integer id;

    private String codeArticle;

    private String designation;

    private BigDecimal prixUnitaireHt;

    private BigDecimal tauxTva;

    private BigDecimal prixUnitaireTtc;

    private String photo;

    private Integer idEntreprise;

    private CategorieDto categorie;

    private List<LigneCommandeClientDto> ligneCommandeClients;

    private List<LigneCommandeFournisseurDto> ligneCommandeFournisseurs;

    private List<LigneVenteDto> ligneVentes;

    private List<MvtStkDto> mvtStks;

    private EntrepriseDto entreprise;

    public static ArticleDto fromEntity(Article article){
        if (article==null){
            return null;
        }
        return ArticleDto.builder()
                .id(article.getId())
                .codeArticle(article.getCodeArticle())
                .categorie(CategorieDto.fromEntity(article.getCategorie()))
                .designation(article.getDesignation())
                .entreprise(EntrepriseDto.fromEntity(article.getEntreprise()))
                .photo(article.getPhoto())
                .idEntreprise(article.getIdEntreprise())
                .prixUnitaireHt(article.getPrixUnitaireHt())
                .tauxTva(article.getTauxTva())
                .prixUnitaireTtc(article.getPrixUnitaireTtc())
                .build();


    }
    public static Article toEntity(ArticleDto articleDto){
        if (articleDto==null){
            return null;
        }
        Article article=new Article();
        article.setId(articleDto.getId());
        article.setCategorie(CategorieDto.toEntity(articleDto.getCategorie()));
        article.setCodeArticle(articleDto.getCodeArticle());
        article.setDesignation(articleDto.getDesignation());
        article.setEntreprise(EntrepriseDto.toEntity(articleDto.getEntreprise()));
        article.setPhoto(articleDto.getPhoto());
        article.setIdEntreprise(articleDto.getIdEntreprise());
        article.setPrixUnitaireHt(articleDto.getPrixUnitaireHt());
        article.setTauxTva(articleDto.getTauxTva());
        article.setPrixUnitaireTtc(articleDto.getPrixUnitaireTtc());

        return article;
    }
}
