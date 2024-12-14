package com.cuerposcelestes.repository;

import com.cuerposcelestes.entity.Estrella;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class EstrellaRepository {
    private final EntityManager entityManager;

    public EstrellaRepository() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
        this.entityManager = emf.createEntityManager();
}


public List<Estrella> findAll() {
    return entityManager.createQuery("SELECT u FROM Estrella u", Estrella.class)
            .getResultList();
}

public Estrella findById(Integer id) {
    return entityManager.find(Estrella.class, id);
}

public void save(Estrella estrella) {
    try {
        entityManager.getTransaction().begin();
        if (estrella.getId() == null) {
            entityManager.persist(estrella);
        } else {
            entityManager.merge(estrella);
        }
        entityManager.getTransaction().commit();
    } catch (Exception e) {
        entityManager.getTransaction().rollback();
        throw e;
    }
}

public void delete(Integer id) {
    try {
        entityManager.getTransaction().begin();
        Estrella estrella = findById(id);
        if (estrella != null) {
            entityManager.remove(estrella);
        }
        entityManager.getTransaction().commit();
    } catch (Exception e) {
        entityManager.getTransaction().rollback();
        throw e;
    }
}


    public void updateEstrella(Estrella estrella) {
        try {
            entityManager.getTransaction().begin();

            Estrella estrellaExistente = entityManager.find(Estrella.class, estrella.getId());
            if (estrellaExistente == null) {
                throw new RuntimeException("No se encontró la estrella con ID: " + estrella.getId());
            }

            estrellaExistente.setNombre(estrella.getNombre());
            estrellaExistente.setTipo(estrella.getTipo());
            estrellaExistente.setEdad(estrella.getEdad());

            entityManager.merge(estrellaExistente);

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw new RuntimeException("Error al actualizar estrella: " + e.getMessage());
        }
    }
}



