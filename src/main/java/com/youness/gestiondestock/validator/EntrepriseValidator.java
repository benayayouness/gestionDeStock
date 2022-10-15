package com.youness.gestiondestock.validator;

import com.youness.gestiondestock.dto.EntrepriseDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class EntrepriseValidator {
    public static List<String> validate(EntrepriseDto dto){
        List<String> errors=new ArrayList<>();
        if (dto==null){
            errors.add("Veuillez renseigner le nom de l'entreprise");
            errors.add("Veuillez renseigner le prenom de l'entreprise");
            errors.add("Veuillez renseigner le mail de l'entreprise");
            errors.add("Veuillez renseigner le numero de telephone de l'entreprise");
            return errors;

        }
        if(!StringUtils.hasLength(dto.getNom())){
            errors.add("Veuillez renseigner le nom de l'entreprise");
        }
        if(!StringUtils.hasLength(dto.getDescription())){
            errors.add("Veuillez renseigner la description de l'entreprise");
        }
        if(!StringUtils.hasLength(dto.getCodeFiscal())){
            errors.add("Veuillez renseigner le Code fiscal de l'entreprise");
        }
        if(!StringUtils.hasLength(dto.getNumTel())){
            errors.add("Veuillez renseigner le numero de telephone de l'entreprise");
        }
        if(dto.getAdresse()==null){
            errors.add("Veuillez renseigner l'adresse de l'Entreprise");
        }else {
            if ( !StringUtils.hasLength(dto.getAdresse().getAdresse1())){
                errors.add("Le champs 'Adresse 1' est obligatoire");
            }
            if ( !StringUtils.hasLength(dto.getAdresse().getVille())){
                errors.add("Le champs 'Ville' est obligatoire");
            }
            if ( !StringUtils.hasLength(dto.getAdresse().getCodePostale())){
                errors.add("Le champs 'Code postale' est obligatoire");
            }
            if ( !StringUtils.hasLength(dto.getAdresse().getPays())){
                errors.add("Le champs 'Pays' est obligatoire");
            }
        }
        return errors;
    }
}
