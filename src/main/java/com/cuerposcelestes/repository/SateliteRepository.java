package com.cuerposcelestes.repository;

import com.cuerposcelestes.entity.Satelite;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class SateliteRepository {

    private final EntityManager entityManager;

    public SateliteRepository() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("defaultPU");
        this.entityManager = emf.createEntityManager();
}



    public List<Satelite> findAll() {
        return entityManager.createQuery("SELECT u FROM Satelite u", Satelite.class)
                .getResultList();
    }

    public Satelite findById(Long id) {
        return entityManager.find(Satelite.class, id);
    }

    public void save(Satelite satelite) {
        try {
            entityManager.getTransaction().begin();
            if (satelite.getId() == null) {
                entityManager.persist(satelite);
            } else {
                entityManager.merge(satelite);
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
            Satelite satelite = findById(id);
            if (satelite != null) {
                entityManager.remove(satelite);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }
}