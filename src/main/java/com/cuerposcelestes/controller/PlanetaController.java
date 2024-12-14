package com.cuerposcelestes.controller;

import com.cuerposcelestes.entity.Planeta;
import com.cuerposcelestes.repository.PlanetaRepository;

import java.util.List;


public class PlanetaController {

        private final PlanetaRepository repository;


        public PlanetaController(PlanetaRepository repository) {
            this.repository = repository;
        }

        public List<Planeta> getAllPlaneta() {
            return repository.findAll();
        }

        public Planeta getPlanetaById(Long id) {
            return repository.findById(id);
        }

        public void savePlaneta(Planeta planeta) {
            repository.save(planeta);
        }

        public void deletePlaneta(Long id) {
            repository.delete(id);
        }




}
