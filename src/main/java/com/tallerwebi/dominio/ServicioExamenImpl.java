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
    public List<Pregunta> generarExamen(Lenguaje lenguaje, Dificultad dificultad) {
        if (lenguaje == null || dificultad == null) {
            throw new OpcionInvalidaException();
        }

        return repositorioExamen.buscarExamenPorLenguajeYDificultad(lenguaje, dificultad);
    }

    @Override
    public Integer calcularPuntaje(Examen examen) {
        Integer puntajeUsuario = 0;
        Integer puntajeFinal = 0;

        switch (examen.getDificultad()) {
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
                puntajeFinal = 0;
                break;
        }

        return puntajeFinal;
    }

    @Override
    public void guardarExamen(Examen examen) {
        repositorioExamen.guardarExamen(examen);
    }
}
