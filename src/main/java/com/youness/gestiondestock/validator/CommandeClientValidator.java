package com.youness.gestiondestock.validator;

import com.youness.gestiondestock.dto.CommandeClientDto;
import com.youness.gestiondestock.dto.UtilisateurDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CommandeClientValidator {
    public static List<String> validate(CommandeClientDto dto){
        List<String> errors=new ArrayList<>();
        if (dto==null){
            errors.add("Veuillez renseigner le code de la commandeClient");
            errors.add("Veuillez renseigner la date commande de la commandeClient");
            errors.add("Veuillez renseigner l'id du Client de la commandeClient");
            errors.add("Veuillez renseigner le Client de la commandeClient");
            return errors;
        }
        if(!StringUtils.hasLength(dto.getCode())){
            errors.add("Veuillez renseigner le code de la commandeClient");
        }
        if(dto.getDateCommande()==null){
            errors.add("Veuillez renseigner la date commande de la commandeClient");
        }
        if(dto.getClient()==null){
            errors.add("Veuillez renseigner le Client de la commandeClient");
        }
        if(dto.getClient().getId()==null){
            errors.add("Veuillez renseigner l'id du Client de la commandeClient");
        }
        return errors;
    }
}
