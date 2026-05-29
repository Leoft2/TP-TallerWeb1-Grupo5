package com.tallerwebi.dominio;

import java.util.List;

public interface RepositorioExamen {
    Pregunta buscarExamenPorLenguajeYDificultad(TipoLenguaje lenguaje, TipoDificultad dificultad);
  void guardarExamen(Examen examen);
}
