package com.youness.gestiondestock.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.youness.gestiondestock.model.Article;
import com.youness.gestiondestock.model.Categorie;
import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
public class CategorieDto {
    private Integer id;

    private String code;

    private String designation;

    private Integer idEntreprise;

    @JsonIgnore
    private List<ArticleDto> articles;

    //Mapping Categorie -> CategorieDto
    public static CategorieDto fromEntity(Categorie categorie){
        if (categorie==null){
            return null;
            //TODO throw exception
        }
        return CategorieDto.builder()
                .id(categorie.getId())
                .code(categorie.getCode())
                .designation(categorie.getDesignation())
                .idEntreprise(categorie.getIdEntreprise())
                .build();
    }
    //Mapping CategorieDto -> Categorie
    public static Categorie toEntity(CategorieDto categorieDto){
        if (categorieDto==null){
            return null;
            //TODO throw exception
        }
        Categorie categorie=new Categorie();
        categorie.setId(categorieDto.getId());
        categorie.setCode(categorieDto.getCode());
        categorie.setDesignation(categorieDto.getDesignation());
        categorie.setIdEntreprise(categorieDto.getIdEntreprise());

        return categorie;
    }

}
