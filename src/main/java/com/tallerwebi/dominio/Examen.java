package com.tallerwebi.dominio;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
public class Examen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer puntaje;

    @Enumerated(EnumType.STRING)
    private TipoDificultad dificultad;
    @Enumerated(EnumType.STRING)
    private TipoLenguaje lenguaje;

    private LocalTime tiempoInicio;
    private LocalTime tiempoFinal;

    @ManyToOne
    private Usuario idUsuario;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public TipoDificultad getDificultad() {
        return dificultad;
    }

    public void setDificultad(TipoDificultad dificultad) {
        this.dificultad = dificultad;
    }

    public TipoLenguaje getLenguaje() {
        return lenguaje;
    }

    public void setLenguaje(TipoLenguaje lenguaje) {
        this.lenguaje = lenguaje;
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

    public LocalTime getTiempoFinal() {
        return tiempoFinal;
    }

    public void setTiempoFinal(LocalTime tiempoFinal) {
        this.tiempoFinal = tiempoFinal;
    }

}
