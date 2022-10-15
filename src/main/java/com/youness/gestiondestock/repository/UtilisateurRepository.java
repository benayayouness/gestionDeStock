package com.youness.gestiondestock.repository;

import com.youness.gestiondestock.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur,Integer> {
    Optional<Utilisateur> findUtilisateurByNomAndPrenom(String nom, String prenom);

    Optional<Utilisateur> findByDateDeNaissance(String date);

    Optional<Utilisateur> findByAdresse_Adresse1(String adresse);
}
