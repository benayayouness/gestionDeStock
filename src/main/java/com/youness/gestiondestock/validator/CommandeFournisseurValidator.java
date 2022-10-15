package com.youness.gestiondestock.validator;

import com.youness.gestiondestock.dto.CommandeClientDto;
import com.youness.gestiondestock.dto.CommandeFournisseurDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CommandeFournisseurValidator {
    public static List<String> validate(CommandeFournisseurDto dto){
        List<String> errors=new ArrayList<>();
        if (dto==null){
            errors.add("Veuillez renseigner le code de la commandeFournisseur");
            errors.add("Veuillez renseigner la date commande de la commandeFournisseur");
            return errors;
        }
        if(!StringUtils.hasLength(dto.getCode())){
            errors.add("Veuillez renseigner le code de la commandeFournisseur");
        }
        if(dto.getDateCommande()==null){
            errors.add("Veuillez renseigner la date commande de la commandeFournisseur");
        }
        return errors;
    }
}
