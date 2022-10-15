package com.youness.gestiondestock.validator;

import com.youness.gestiondestock.dto.FournisseurDto;
import com.youness.gestiondestock.dto.LigneCommandeClientDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class LigneCommandeClientValidator {
    public static List<String> validate(LigneCommandeClientDto dto){
        List<String> errors=new ArrayList<>();
        if(dto==null){
            errors.add("Veuillez renseigner la quantite de la ligneCommandeClient");
            errors.add("Veuillez renseigner le prix unitaire de la ligneCommandeClient");
            errors.add("Veuillez renseigner les articles de la ligneCommandeClient");
            return errors;
        }
        if(dto.getQuantite()==null){
            errors.add("Veuillez renseigner la quantite de la ligneCommandeClient");
        }
        if(dto.getPrixUnitaire()==null){
            errors.add("Veuillez renseigner le prix unitaire de la ligneCommandeClient");
        }
        if(dto.getArticle()==null){
            errors.add("Veuillez renseigner les articles de la ligneCommandeClient");
        }
        return errors;

    }
}
