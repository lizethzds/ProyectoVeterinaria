package com.example.veterinaria.modelos;

public class Citas {

    private int id;
    private String nombre;
    private String telefono;
    private String especialidad;

    public Citas(int id, String nombre, String telefono, String especialidad) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.especialidad = especialidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
}
