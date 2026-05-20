package com.tallerwebi.dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalTime;

@Entity
public class Examen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idReclutador;
    private Integer puntaje;
    private String dificultad;
    private String lenguaje;
    private LocalTime tiempoInicio;
    private LocalTime tiempoFinal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdReclutador() {
        return idReclutador;
    }

    public void setIdReclutador(Long idReclutador) {
        this.idReclutador = idReclutador;
    }

    public String getDificultad() {
        return dificultad;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }

    public Integer getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(Integer puntaje) {
        this.puntaje = puntaje;
    }

    public LocalTime getTiempoInicio() {
        return tiempoInicio;
    }

    public void setTiempoInicio(LocalTime tiempoInicio) {
        this.tiempoInicio = tiempoInicio;
    }

    public String getLenguaje() {
        return lenguaje;
    }

    public void setLenguaje(String lenguaje) {
        this.lenguaje = lenguaje;
    }

    public LocalTime getTiempoFinal() {
        return tiempoFinal;
    }

    public void setTiempoFinal(LocalTime tiempoFinal) {
        this.tiempoFinal = tiempoFinal;
    }
}
