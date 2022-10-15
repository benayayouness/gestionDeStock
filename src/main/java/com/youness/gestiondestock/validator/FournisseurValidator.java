package com.youness.gestiondestock.validator;

import com.youness.gestiondestock.dto.ClientDto;
import com.youness.gestiondestock.dto.FournisseurDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class FournisseurValidator {
    public static List<String> validate(FournisseurDto dto){
        List<String> errors=new ArrayList<>();

        if (dto==null){
            errors.add("Veuillez renseigner le nom du Founisseur");
            errors.add("Veuillez renseigner le prenom du Founisseur");
            errors.add("Veuillez renseigner le mail du Founisseur");
            errors.add("Veuillez renseigner le numero de telephone du Founisseur");
            return errors;

        }
        if(!StringUtils.hasLength(dto.getNom())){
            errors.add("Veuillez renseigner le nom du Founisseur");
        }
        if(!StringUtils.hasLength(dto.getPrenom())){
            errors.add("Veuillez renseigner le prenom du Founisseur");
        }
        if(!StringUtils.hasLength(dto.getMail())){
            errors.add("Veuillez renseigner le mail du Founisseur");
        }
        if(!StringUtils.hasLength(dto.getNumTel())){
            errors.add("Veuillez renseigner le numero de telephone du Founisseur");
        }
        return errors;
    }
}
