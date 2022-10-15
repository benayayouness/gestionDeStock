package com.youness.gestiondestock.validator;

import com.youness.gestiondestock.dto.LigneCommandeClientDto;
import com.youness.gestiondestock.dto.LigneCommandeFournisseurDto;

import java.util.ArrayList;
import java.util.List;

public class LigneCommandeFournisseurValidator {
    public static List<String> validate(LigneCommandeFournisseurDto dto){
        List<String> errors=new ArrayList<>();
        if(dto==null){
            errors.add("Veuillez renseigner la quantite de la ligneCommandeFournisseur");
            errors.add("Veuillez renseigner le prix unitaire de la ligneCommandeFournisseur");
            errors.add("Veuillez renseigner les articles de la ligneCommandeFournisseur");
            return errors;
        }
        if(dto.getQuantite()==null){
            errors.add("Veuillez renseigner la quantite de la ligneCommandeFournisseur");
        }
        if(dto.getPrixUnitaire()==null){
            errors.add("Veuillez renseigner le prix unitaire de la ligneCommandeFournisseur");
        }
        if(dto.getArticle()==null){
            errors.add("Veuillez renseigner les articles de la ligneCommandeFournisseur");
        }
        return errors;

    }
}
