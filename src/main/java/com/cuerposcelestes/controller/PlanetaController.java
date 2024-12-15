package com.cuerposcelestes.controller;

import com.cuerposcelestes.entity.Estrella;
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

        public Planeta getPlanetaById(Integer id) {
            return repository.findById(id);
        }

        public void savePlaneta(Planeta planeta) {
            repository.save(planeta);
        }

        public void deletePlaneta(Integer id) {
            repository.delete(id);
        }

        public void updatePlaneta(Planeta planeta) {
        repository.updatePlaneta(planeta);
        System.out.println("planeta actualizado");
         }




}
