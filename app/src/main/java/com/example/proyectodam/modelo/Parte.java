package com.example.proyectodam.modelo;

public class Parte {
    String nombre, poblacion, comentarios, fecha;

    public Parte() {


    }

    public Parte(String nombre, String poblacion, String comentarios, String fecha) {
        this.nombre = nombre;
        this.poblacion = poblacion;
        this.comentarios = comentarios;
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
