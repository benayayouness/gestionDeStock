package com.youness.gestiondestock.repository;

import com.youness.gestiondestock.model.Adresse;
import com.youness.gestiondestock.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client,Integer> {
    Optional<Client> findClientByNomAndPrenom(String nom, String prenom);
    Optional<Client> findByAdresse_Adresse1(String adresse);

}
