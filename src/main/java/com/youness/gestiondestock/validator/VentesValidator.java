package com.youness.gestiondestock.validator;

import com.youness.gestiondestock.dto.LigneCommandeClientDto;
import com.youness.gestiondestock.dto.LigneVenteDto;
import com.youness.gestiondestock.dto.VentesDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class VentesValidator {
    public static List<String> validate(VentesDto dto){
        List<String> errors=new ArrayList<>();
        if(dto==null){
            errors.add("Veuillez renseigner l' ID des ventes");
            errors.add("Veuillez renseigner le code des ventes");
            errors.add("Veuillez renseigner la date des ventes");

            return errors;
        }
        if(dto.getId()==null){
            errors.add("Veuillez renseigner l' ID des ventes");
        }
        if(!StringUtils.hasLength(dto.getCode())){
            errors.add("Veuillez renseigner le code des ventes");
        }
        if(dto.getDateVente()==null){
            errors.add("Veuillez renseigner la date des ventes");
        }
        return errors;

    }
}
