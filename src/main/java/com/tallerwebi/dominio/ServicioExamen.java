package com.tallerwebi.dominio;

import java.util.List;

public interface ServicioExamen {
  List<Pregunta> generarExamen(Lenguaje lenguaje, Dificultad dificultad);
  void guardarExamen(Examen examen);
  Integer calcularPuntaje(Examen examen);
}
