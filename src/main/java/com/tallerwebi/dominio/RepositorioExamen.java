package com.tallerwebi.dominio;

import java.util.List;

public interface RepositorioExamen {
  List<Pregunta> buscarExamenPorLenguajeYDificultad(String lenguaje, String dificultad);
  void guardarExamen(Examen examen);
}
