package com.tallerwebi.dominio;

import java.util.List;

public interface RepositorioExamen {
  List<Pregunta> buscarExamenPorLenguajeYDificultad(Lenguaje lenguaje, Dificultad dificultad);
  void guardarExamen(Examen examen);
}
