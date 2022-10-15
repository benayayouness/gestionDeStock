package com.youness.gestiondestock.model;

import lombok.*;

import javax.persistence.*;
//@Builder
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
public class Roles extends AbstractEntity {
    @Column(name = "rolename")
    private String roleName;

    @Column(name = "identreprise",insertable = false,updatable = false)
    private Integer idEntreprise;

    @ManyToOne
    @JoinColumn(name = "idroles")
    private Utilisateur utilisateur;

}
