package com.cuerposcelestes.controller;

import com.cuerposcelestes.entity.Estrella;
import com.cuerposcelestes.repository.EstrellaRepository;

import java.util.List;

public class EstrellaController {


    private final EstrellaRepository repository;


    public EstrellaController(EstrellaRepository repository) {
        this.repository = repository;
    }



    public List<Estrella> getAllEstrella() {
            return repository.findAll();
        }

        public Estrella getEstrellaById(Integer id) {
            return repository.findById(id);
        }

        public void saveEstrella(Estrella estrella) {
            repository.save(estrella);
        }

        public void deleteEstrella(Integer id) {
            repository.delete(id);
        }

        public void updateEstrella(Estrella estrella) {
            repository.updateEstrella(estrella);
            System.out.println("Estrella actualizada");
        }

}
