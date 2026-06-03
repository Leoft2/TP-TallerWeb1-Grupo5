package com.tallerwebi.dominio;

import java.util.List;

public interface RepositorioExamen {
  List<Pregunta> buscarExamenPorLenguajeYDificultad(TipoLenguaje lenguaje, TipoDificultad dificultad);
  void guardarExamen(Examen examen);
}
