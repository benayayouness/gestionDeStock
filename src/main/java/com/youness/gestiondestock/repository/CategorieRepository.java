package com.youness.gestiondestock.repository;

import com.youness.gestiondestock.dto.CategorieDto;
import com.youness.gestiondestock.model.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategorieRepository extends JpaRepository<Categorie,Integer> {
    Optional<Categorie> findCategorieByCode(String code);
}
