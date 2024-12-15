package com.cuerposcelestes.controller;

import com.cuerposcelestes.entity.Planeta;
import com.cuerposcelestes.entity.Satelite;
import com.cuerposcelestes.repository.SateliteRepository;

import java.util.List;

public class SateliteController {
    private final SateliteRepository repository;


    public SateliteController(SateliteRepository repository) {
        this.repository = repository;
    }

    public List<Satelite> getAllSatelite() {
        return repository.findAll();
    }

    public Satelite getSateliteById(Integer id) {
        return repository.findById(id);
    }

    public void saveSatelite(Satelite satelite) {
        repository.save(satelite);
    }

    public void deleteSatelite(Integer id) {
        repository.delete(id);
    }
    public void updateSatelite(Satelite satelite) {
        repository.updateSatelite(satelite);
        System.out.println("Satelite actualizado");
    }




}
