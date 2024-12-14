package com.cuerposcelestes.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "planeta")
public class Planeta {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "masa")
    private Float masa;

    @Column(name = "distanciaSol")
    private Float distanciaSol;

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

    public Float getMasa() {
        return masa;
    }

    public void setMasa(Float masa) {
        this.masa = masa;
    }

    public Float getDistanciaSol() {
        return distanciaSol;
    }

    public void setDistanciaSol(Float distanciaSol) {
        this.distanciaSol = distanciaSol;
    }

}