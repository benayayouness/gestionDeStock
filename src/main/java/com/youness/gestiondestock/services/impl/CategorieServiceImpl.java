package com.youness.gestiondestock.services.impl;

import com.youness.gestiondestock.dto.CategorieDto;
import com.youness.gestiondestock.exception.EntityNotFoundException;
import com.youness.gestiondestock.exception.ErrorCodes;
import com.youness.gestiondestock.exception.InvalidEntityException;
import com.youness.gestiondestock.model.Categorie;
import com.youness.gestiondestock.repository.CategorieRepository;
import com.youness.gestiondestock.services.CategorieService;
import com.youness.gestiondestock.validator.CategorieValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CategorieServiceImpl implements CategorieService {
    private CategorieRepository categorieRepository;

    @Autowired
    public CategorieServiceImpl(CategorieRepository categorieRepository) {
        this.categorieRepository = categorieRepository;
    }

    @Override
    public CategorieDto save(CategorieDto dto) {
        List<String> errors= CategorieValidator.validate(dto);
        if (!errors.isEmpty()){
            log.error("Categorie is not valid {}",dto);
            throw new InvalidEntityException("Categorie n'est pas valide", ErrorCodes.CATEGORIE_NOT_VALID,errors);
        }
        return CategorieDto.fromEntity(
                categorieRepository.save(CategorieDto.toEntity(dto))
        );
    }

    @Override
    public CategorieDto findById(Integer id) {
        if (id==null){
            log.error("Categorie ID is null");
            return null;
        }
        Optional<Categorie> categorie=categorieRepository.findById(id);
        CategorieDto dto=CategorieDto.fromEntity(categorie.get());

        return Optional.of(dto).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucune categorie avec l'ID = " + id + "n'est trouve dans la BDD",
                        ErrorCodes.CATEGORIE_NOT_FOUND
                ));
    }

    @Override
    public CategorieDto findByCode(String code) {
        if (!StringUtils.hasLength(code)){
            log.error("Categorie CODE is null");
            return null;
        }
        Optional<Categorie> categorie=categorieRepository.findCategorieByCode(code);
        CategorieDto dto=CategorieDto.fromEntity(categorie.get());
        return Optional.of(dto).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucune categorie avec le CODE =" + code + "n'est trouve dans la BDD",
                        ErrorCodes.CATEGORIE_NOT_FOUND
                ));
    }

    @Override
    public List<CategorieDto> findAll() {
        return categorieRepository.findAll().stream()
                .map(CategorieDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Integer id) {
        if (id==null){
            log.error("Categorie ID is null");
            return;
        }
        categorieRepository.deleteById(id);

    }
}
