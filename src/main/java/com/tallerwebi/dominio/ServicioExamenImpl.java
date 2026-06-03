package com.tallerwebi.dominio;

import java.util.List;
import javax.transaction.Transactional;

import com.tallerwebi.dominio.excepcion.OpcionInvalidaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ServicioExamenImpl implements ServicioExamen {

    RepositorioExamen repositorioExamen;

    @Autowired
    public ServicioExamenImpl(RepositorioExamen repositorioExamen) {
        this.repositorioExamen = repositorioExamen;
    }

    @Override
    public List<Pregunta> generarExamen(TipoLenguaje lenguaje, TipoDificultad dificultad) {
        if (lenguaje == null || dificultad == null) {
            throw new OpcionInvalidaException();
        }

        return repositorioExamen.buscarExamenPorLenguajeYDificultad(lenguaje, dificultad);
    }

    @Override
    public Integer calcularPuntaje(Examen examen, List<Pregunta> preguntas) {
        List<String> respuestasUsuario = examen.getRespuesta().getRespuestaUsuario();
        int puntaje = 0;

        for (int i = 0; i < preguntas.size(); i++) {
            String correcta = preguntas.get(i).getRespuestaCorrecta();
            String elegida = respuestasUsuario.get(i).toUpperCase().trim();

            if (correcta.equals(elegida)) {
                puntaje++;
            }
        }
        return puntajePorDificultad(puntaje, examen.getDificultad());
    }

    public Integer puntajePorDificultad(Integer puntaje, TipoDificultad dificultad) {
        Integer puntajeUsuario = puntaje;
        Integer puntajeFinal = 0;

        switch (dificultad) {
            case BASICO:
                puntajeFinal = puntajeUsuario * 2;
                break;
            case MEDIO:
                puntajeFinal = puntajeUsuario * 3;
                break;
            case DIFICIL:
                puntajeFinal = puntajeUsuario * 4;
                break;
            default:
                puntajeFinal = puntaje;
                break;
        }

        return puntajeFinal;
    }

    @Override
    public void guardarExamen(Examen examen) {
        repositorioExamen.guardarExamen(examen);
    }
}
