package com.cuerposcelestes.repository;

import com.cuerposcelestes.entity.Planeta;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class PlanetaRepository {
    private final EntityManager entityManager;

    public PlanetaRepository() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
        this.entityManager = emf.createEntityManager();
    }

    public List<Planeta> findAll() {
        return entityManager.createQuery("SELECT u FROM Planeta u", Planeta.class)
                .getResultList();
    }

    public Planeta findById(Long id) {
        return entityManager.find(Planeta.class, id);
    }

    public void save(Planeta planeta) {
        try {
            entityManager.getTransaction().begin();
            if (planeta.getId() == null) {
                entityManager.persist(planeta);
            } else {
                entityManager.merge(planeta);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    public void delete(Long id) {
        try {
            entityManager.getTransaction().begin();
            Planeta planeta = findById(id);
            if (planeta != null) {
                entityManager.remove(planeta);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }
}