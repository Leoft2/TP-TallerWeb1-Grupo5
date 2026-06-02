package com.tallerwebi.dominio;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Examen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Usuario idUsuario;
    private Integer puntaje;
    private Dificultad dificultad;
    private Lenguaje lenguaje;
    private LocalTime tiempoInicio;
    private LocalTime tiempoFinal;

    //@Transient no la gurada en la base de datos, pero si en memoria para poder usarla
    @Transient
    private List<String> respuestas = new ArrayList<>();

    @Transient
    private List<Long> preguntaIds = new ArrayList<>();

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

    public Dificultad getDificultad() {
        return dificultad;
    }

    public void setDificultad(Dificultad dificultad) {
        this.dificultad = dificultad;
    }

    public Lenguaje getLenguaje() {
        return lenguaje;
    }

    public void setLenguaje(Lenguaje lenguaje) {
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

    public List<String> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(List<String> respuestas) {
        this.respuestas = respuestas;
    }

    public List<Long> getPreguntaIds() {
        return preguntaIds;
    }

    public void setPreguntaIds(List<Long> preguntaIds) {
        this.preguntaIds = preguntaIds;
    }
}
