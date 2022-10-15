package com.youness.gestiondestock.validator;

import com.youness.gestiondestock.dto.LigneVenteDto;
import com.youness.gestiondestock.dto.MvtStkDto;

import java.util.ArrayList;
import java.util.List;

public class MvtStkValidator {
    public static List<String> validate(MvtStkDto dto) {
        List<String> errors = new ArrayList<>();
        if (dto == null) {
            errors.add("Veuillez renseigner la quantite du mvt stock");
            errors.add("Veuillez renseigner la quantite du mvt stock");
            errors.add("Veuillez renseigner le type du mvt stock");
            errors.add("Veuillez renseigner les articles du mvt stock");
            return errors;
        }
        if(dto.getQuantite()==null){
            errors.add("Veuillez renseigner la quantite du mvt stock");
        }
        if(dto.getDateMvt()==null){
            errors.add("Veuillez renseigner la date du mvt stock");
        }
        if(dto.getTypeMvtStk()==null){
            errors.add("Veuillez renseigner le type du mvt stock");
        }
        if(dto.getArticle()==null){
            errors.add("Veuillez renseigner les articles du mvt stock");
        }
        return errors;
    }
}
