package com.youness.gestiondestock.repository;

import com.youness.gestiondestock.model.Client;
import com.youness.gestiondestock.model.Fournisseur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FournisseurRepository extends JpaRepository<Fournisseur,Integer> {
    Optional<Fournisseur> findFournisseurByNomAndPrenom(String nom,String prenom);
    Optional<Fournisseur> findByAdresse_Adresse1(String adresse);

}
