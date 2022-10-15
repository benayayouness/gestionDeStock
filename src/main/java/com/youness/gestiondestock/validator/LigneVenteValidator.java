package com.youness.gestiondestock.validator;

import com.youness.gestiondestock.dto.LigneCommandeFournisseurDto;
import com.youness.gestiondestock.dto.LigneVenteDto;

import java.util.ArrayList;
import java.util.List;

public class LigneVenteValidator {
    public static List<String> validate(LigneVenteDto dto){
        List<String> errors=new ArrayList<>();
        if(dto==null){
            errors.add("Veuillez renseigner la quantite de la ligneVente");
            errors.add("Veuillez renseigner le prix unitaire de la ligneVente");
            errors.add("Veuillez renseigner les articles de la ligneVente");
            errors.add("Veuillez renseigner les ventes de la ligneVente");
            return errors;
        }
        if(dto.getQuantite()==null){
            errors.add("Veuillez renseigner la quantite de la ligneVente");
        }
        if(dto.getPrixUnitaire()==null){
            errors.add("Veuillez renseigner le prix unitaire de la ligneVente");
        }
        if(dto.getArticle()==null){
            errors.add("Veuillez renseigner les articles de la ligneVente");
        }
        if(dto.getVente()==null){
            errors.add("Veuillez renseigner les ventes de la ligneVente");
        }

        return errors;

    }
}
