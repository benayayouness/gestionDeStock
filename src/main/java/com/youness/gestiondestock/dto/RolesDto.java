package com.youness.gestiondestock.dto;

import com.youness.gestiondestock.model.Roles;
import com.youness.gestiondestock.model.Utilisateur;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Builder
public class RolesDto {
    private Integer id;

    private String roleName;

    private UtilisateurDto utilisateur;

    private Integer idEntreprise;

    public static RolesDto fromEntity(Roles roles){
        if (roles==null){
            return null;
        }
        return RolesDto.builder()
                .id(roles.getId())
                .roleName(roles.getRoleName())
                .utilisateur(UtilisateurDto.fromEntity(roles.getUtilisateur()))
                .idEntreprise(roles.getIdEntreprise())
                .build();
    }
    public static Roles toEntity(RolesDto rolesDto){
        if (rolesDto==null){
            return null;
        }
        Roles roles=new Roles();
        roles.setId(rolesDto.getId());
        roles.setRoleName(rolesDto.getRoleName());
        roles.setUtilisateur(UtilisateurDto.toEntity(rolesDto.getUtilisateur()));
        roles.setIdEntreprise(rolesDto.getIdEntreprise());
        return roles;
    }

}
