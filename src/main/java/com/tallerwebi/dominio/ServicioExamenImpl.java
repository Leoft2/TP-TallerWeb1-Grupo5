package com.tallerwebi.dominio;

import java.util.List;
import javax.transaction.Transactional;
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
  public List<Pregunta> generarExamen(String lenguaje, String dificultad) {
    if (lenguaje.isEmpty() || dificultad.isEmpty()) {
      throw new OpcionInvalidaException();
    }

    return repositorioExamen.buscarExamenPorLenguajeYDificultad(lenguaje, dificultad);
  }

  @Override
  public Integer calcularPuntaje(Examen examen) {
    Integer puntajeFinal = 0;
    return puntajeFinal;
  }

  @Override
  public void guardarExamen(Examen examen) {
    repositorioExamen.guardarExamen(examen);
  }
}
