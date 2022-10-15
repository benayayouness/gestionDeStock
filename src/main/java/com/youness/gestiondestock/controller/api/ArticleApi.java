package com.youness.gestiondestock.controller.api;

import com.youness.gestiondestock.dto.ArticleDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.youness.gestiondestock.utils.Constants.APP_ROOT;

@Api(APP_ROOT + "/articles")
public interface ArticleApi {
    @PostMapping(value =APP_ROOT+"/articles/create",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value ="Enregistrer/modifier un article",response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "L'article a ete cree/modifie"),
            @ApiResponse(code = 400,message = "L'article n'est pas valide")
    })
    ArticleDto save(@RequestBody ArticleDto dto);

    @GetMapping(value =APP_ROOT+"/articles/{idArticle}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value ="Rechercher un article par l'ID",response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "L'article a ete trouve dans la BDD"),
            @ApiResponse(code = 404,message = "Aucun article n'existe dans la BDD avec l'ID fourni")
    })
    ArticleDto findById(@PathVariable("idArticle") Integer id);

    @GetMapping(value =APP_ROOT+"/articles/{codeArticle}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value ="Rechercher un article par son code",response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "L'article a ete cree/modifie"),
            @ApiResponse(code = 404,message = "Aucun article n'existe dans la BDD avec le CODE fourni")
    })
    ArticleDto findByCodeArticle(@PathVariable("codeArticle") String codeArticle);

    @GetMapping(value =APP_ROOT+"/articles/all",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value ="Renvoyer la liste des articles",responseContainer ="List<ArticleDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "La liste des articles")
    })
    List<ArticleDto> findAll();

    @DeleteMapping(value =APP_ROOT+"/articles/delete/{idArticle}" )
    @ApiOperation(value ="Supprimer un article par ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "L'article a ete supprime")
    })
    void deleteById(@PathVariable("idArticle") Integer id);
}
