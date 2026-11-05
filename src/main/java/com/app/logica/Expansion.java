
package com.app.logica;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Expansion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_expansion;

    private String nombre;
    private String fecha_lanzamiento;

    public Expansion() {
    }

    public Expansion(String nombre, String fecha_lanzamiento) {
        this.nombre = nombre;
        this.fecha_lanzamiento = fecha_lanzamiento;
    }

    public int getId_expansion() {
        return id_expansion;
    }

    public void setId_expansion(int id_expansion) {
        this.id_expansion = id_expansion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha_lanzamiento() {
        return fecha_lanzamiento;
    }

    public void setFecha_lanzamiento(String fecha_lanzamiento) {
        this.fecha_lanzamiento = fecha_lanzamiento;
    }
}
