package com.example.veterinaria.modelos;

import java.sql.Time;
import java.util.Date;

public class Citas {
    private int idCita;
    private Date fecha;
    private Time hora;
    private int id_mascota;

    private int id_veterinario;


    public Citas(int idCita, Date fecha, Time hora, int id_mascota, int id_veterinario) {
        this.idCita = idCita;
        this.fecha = fecha;
        this.hora = hora;
        this.id_mascota = id_mascota;
        this.id_veterinario = id_veterinario;
    }

    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public int getId_mascota() {
        return id_mascota;
    }

    public void setId_mascota(int id_mascota) {
        this.id_mascota = id_mascota;
    }

    public int getId_veterinario() {
        return id_veterinario;
    }

    public void setId_veterinario(int id_veterinario) {
        this.id_veterinario = id_veterinario;
    }
}