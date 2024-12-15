package com.cuerposcelestes.repository;

import com.cuerposcelestes.entity.Satelite;
import com.cuerposcelestes.entity.Satelite;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class SateliteRepository {

    private final EntityManager entityManager;

    public SateliteRepository() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
        this.entityManager = emf.createEntityManager();
}



    public List<Satelite> findAll() {
        return entityManager.createQuery("SELECT u FROM Satelite u", Satelite.class)
                .getResultList();
    }

    public Satelite findById(Integer id) {
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

    public void delete(Integer id) {
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


    public void updateSatelite(Satelite satelite) {
        try {
            entityManager.getTransaction().begin();

            Satelite sateliteExistente = entityManager.find(Satelite.class, satelite.getId());
            if (sateliteExistente == null) {
                throw new RuntimeException("No se encontró el satelite con ID: " + satelite.getId());
            }

            sateliteExistente.setNombre(satelite.getNombre());
            sateliteExistente.setTamano(satelite.getTamano());
            sateliteExistente.setOrbita(satelite.getOrbita());

            entityManager.merge(sateliteExistente);

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw new RuntimeException("Error al actualiar satelite: " + e.getMessage());
        }
    }
}