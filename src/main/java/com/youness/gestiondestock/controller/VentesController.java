package com.youness.gestiondestock.controller;

import com.youness.gestiondestock.controller.api.VentesApi;
import com.youness.gestiondestock.dto.VentesDto;
import com.youness.gestiondestock.services.VentesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VentesController implements VentesApi {
    private VentesService ventesService;

    @Autowired
    public VentesController(VentesService ventesService) {
        this.ventesService = ventesService;
    }

    @Override
    public VentesDto save(VentesDto dto) {
        return ventesService.save(dto);
    }

    @Override
    public VentesDto findById(Integer id) {
        return ventesService.findById(id);
    }

    @Override
    public VentesDto findByCode(String code) {
        return ventesService.findByCode(code);
    }

    @Override
    public VentesDto findByDateVente(String date) {
        return ventesService.findByDateVente(date);
    }

    @Override
    public List<VentesDto> findAll() {
        return ventesService.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        ventesService.deleteById(id);
    }
}
