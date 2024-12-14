package com.cuerposcelestes.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "estrella")
public class Estrella {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "id", nullable = false)
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "edad")
    private Float edad;

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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Float getEdad() {
        return edad;
    }

    public void setEdad(Float edad) {
        this.edad = edad;
    }

}