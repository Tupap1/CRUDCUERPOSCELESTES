package com.cuerposcelestes.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "satelites")
public class Satelite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "tamano")
    private Float tamano;

    @Column(name = "orbita")
    private String orbita;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Float getTamano() {
        return tamano;
    }

    public void setTamano(Float tamano) {
        this.tamano = tamano;
    }

    public String getOrbita() {
        return orbita;
    }

    public void setOrbita(String orbita) {
        this.orbita = orbita;
    }

}