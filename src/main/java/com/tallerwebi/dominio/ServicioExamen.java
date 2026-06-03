package com.tallerwebi.dominio;

import java.util.List;

public interface ServicioExamen {
  List<Pregunta> generarExamen(TipoLenguaje lenguaje, TipoDificultad dificultad);
  void guardarExamen(Examen examen);
  Integer calcularPuntaje(Examen examen, List<Pregunta> preguntas);
}
