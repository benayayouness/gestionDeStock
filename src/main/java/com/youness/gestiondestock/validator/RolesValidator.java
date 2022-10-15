package com.youness.gestiondestock.validator;

import com.youness.gestiondestock.dto.LigneVenteDto;
import com.youness.gestiondestock.dto.RolesDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class RolesValidator {
    public static List<String> validate(RolesDto dto){
        List<String> errors=new ArrayList<>();
        if(dto==null){
            return errors;
        }
        if ( !StringUtils.hasLength(dto.getRoleName())){
            errors.add("Veuillez renseigner le role de l'utilisateur");
        }
        if(dto.getUtilisateur()==null){
            errors.add("Veuillez renseigner l'utilsateur");
        }
        return errors;
    }
}
