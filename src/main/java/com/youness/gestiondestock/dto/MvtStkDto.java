package com.youness.gestiondestock.dto;

import com.youness.gestiondestock.model.Article;
import com.youness.gestiondestock.model.MvtStk;
import com.youness.gestiondestock.model.TypeMvtStk;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
public class MvtStkDto {
    private Integer id;

    private Instant dateMvt;

    private BigDecimal quantite;

    private TypeMvtStk typeMvtStk;

    private ArticleDto article;

    private Integer idEntreprise;

    public static MvtStkDto fromEntity(MvtStk mvtStk){
        if (mvtStk==null){
            return null;
        }
        return MvtStkDto.builder()
                .id(mvtStk.getId())
                .dateMvt(mvtStk.getDateMvt())
                .quantite(mvtStk.getQuantite())
                .typeMvtStk(mvtStk.getTypeMvtStk())
                .article(ArticleDto.fromEntity(mvtStk.getArticle()))
                .idEntreprise(mvtStk.getIdEntreprise())
                .build();
    }
    public static MvtStk toEntity(MvtStkDto mvtStkDto){
        if (mvtStkDto==null){
            return null;
        }
        MvtStk mvtStk=new MvtStk();
        mvtStk.setId(mvtStkDto.getId());
        mvtStk.setArticle(ArticleDto.toEntity(mvtStkDto.getArticle()));
        mvtStk.setDateMvt(mvtStkDto.getDateMvt());
        mvtStk.setQuantite(mvtStkDto.getQuantite());
        mvtStk.setArticle(ArticleDto.toEntity(mvtStkDto.getArticle()));
        mvtStk.setIdEntreprise(mvtStkDto.getIdEntreprise());
        return mvtStk;
    }
}

