package com.youness.gestiondestock.repository;

import com.youness.gestiondestock.model.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EntrepriseRepository extends JpaRepository<Entreprise,Integer> {
    Optional<Entreprise> findByNom(String nom);

    Optional<Entreprise> findByAdresse_Adresse1(String adresse);

    Optional<Entreprise> findByCodeFiscal(String code);
}
