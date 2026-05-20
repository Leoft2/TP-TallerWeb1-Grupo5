package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.Examen;
import com.tallerwebi.dominio.Pregunta;
import com.tallerwebi.dominio.RepositorioExamen;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class RespositorioExamenImpl implements RepositorioExamen {

  private static final String DIFICULTAD_BASICO = "basico";

  @Override
  public List<Pregunta> buscarExamenPorLenguajeYDificultad(String lenguaje, String dificultad) {
    List<Pregunta> preguntas = new ArrayList<>();

    // ─── JAVA ────────────────────────────────────────────
    Pregunta p1 = new Pregunta();
    p1.setLenguaje("Java");
    p1.setDificultad(dificultad);
    p1.setConsigna("¿Qué es la herencia en Java?");
    p1.setOpcionA("Copiar código de otra clase");
    p1.setOpcionB("Una clase que hereda atributos y métodos de otra");
    p1.setOpcionC("Un tipo de variable especial");
    p1.setRespuestaCorrecta("B");
    preguntas.add(p1);

    Pregunta p2 = new Pregunta();
    p2.setLenguaje("Java");
    p2.setDificultad(DIFICULTAD_BASICO);
    p2.setConsigna("¿Cuál es el tipo de dato para números enteros en Java?");
    p2.setOpcionA("float");
    p2.setOpcionB("String");
    p2.setOpcionC("int");
    p2.setRespuestaCorrecta("C");
    preguntas.add(p2);

    Pregunta p3 = new Pregunta();
    p3.setLenguaje("Java");
    p3.setDificultad("medio");
    p3.setConsigna("¿Qué hace la palabra reservada static?");
    p3.setOpcionA("Hace que una variable no se pueda modificar");
    p3.setOpcionB("Hace que una clase sea abstracta");
    p3.setOpcionC("Permite que un método pertenezca a la clase y no a una instancia");
    p3.setRespuestaCorrecta("C");
    preguntas.add(p3);

    // ─── PYTHON ──────────────────────────────────────────
    Pregunta p4 = new Pregunta();
    p4.setLenguaje("Python");
    p4.setDificultad(DIFICULTAD_BASICO);
    p4.setConsigna("¿Cómo se define una función en Python?");
    p4.setOpcionA("function miFuncion()");
    p4.setOpcionB("def miFuncion():");
    p4.setOpcionC("void miFuncion()");
    p4.setRespuestaCorrecta("B");
    preguntas.add(p4);

    Pregunta p5 = new Pregunta();
    p5.setLenguaje("Python");
    p5.setDificultad(DIFICULTAD_BASICO);
    p5.setConsigna("¿Qué tipo de dato es [1, 2, 3] en Python?");
    p5.setOpcionA("Tupla");
    p5.setOpcionB("Diccionario");
    p5.setOpcionC("Lista");
    p5.setRespuestaCorrecta("C");
    preguntas.add(p5);

    Pregunta p6 = new Pregunta();
    p6.setLenguaje("Python");
    p6.setDificultad("medio");
    p6.setConsigna("¿Qué hace el método append() en una lista?");
    p6.setOpcionA("Elimina el último elemento");
    p6.setOpcionB("Agrega un elemento al final de la lista");
    p6.setOpcionC("Ordena la lista");
    p6.setRespuestaCorrecta("B");
    preguntas.add(p6);

    // ─── SQL ─────────────────────────────────────────────
    Pregunta p7 = new Pregunta();
    p7.setLenguaje("SQL");
    p7.setDificultad(DIFICULTAD_BASICO);
    p7.setConsigna("¿Qué hace la sentencia SELECT en SQL?");
    p7.setOpcionA("Elimina registros de una tabla");
    p7.setOpcionB("Inserta nuevos registros");
    p7.setOpcionC("Consulta y devuelve datos de una tabla");
    p7.setRespuestaCorrecta("C");
    preguntas.add(p7);

    Pregunta p8 = new Pregunta();
    p8.setLenguaje("SQL");
    p8.setDificultad(DIFICULTAD_BASICO);
    p8.setConsigna("¿Qué cláusula se usa para filtrar registros en SQL?");
    p8.setOpcionA("WHERE");
    p8.setOpcionB("ORDER BY");
    p8.setOpcionC("GROUP BY");
    p8.setRespuestaCorrecta("A");
    preguntas.add(p8);

    Pregunta p9 = new Pregunta();
    p9.setLenguaje("SQL");
    p9.setDificultad("medio");
    p9.setConsigna("¿Qué hace un INNER JOIN?");
    p9.setOpcionA("Devuelve todos los registros de ambas tablas");
    p9.setOpcionB("Devuelve solo los registros que tienen coincidencia en ambas tablas");
    p9.setOpcionC("Elimina registros duplicados");
    p9.setRespuestaCorrecta("B");
    preguntas.add(p9);

    List<Pregunta> listaParaEnviar = new ArrayList<>();

    for (Pregunta p : preguntas) {
      if (
        p.getLenguaje().equalsIgnoreCase(lenguaje) && p.getDificultad().equalsIgnoreCase(dificultad)
      ) {
        listaParaEnviar.add(p);
      }
    }

    return listaParaEnviar;
  }

  @Override
  public void guardarExamen(Examen examen) {
      //Query para completar
  }
}
