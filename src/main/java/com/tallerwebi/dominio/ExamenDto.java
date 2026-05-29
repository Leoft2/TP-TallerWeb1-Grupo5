package com.tallerwebi.dominio;

public class ExamenDto {

  private TipoDificultad dificultad;
  private TipoLenguaje lenguaje;

    public TipoDificultad getDificultad() {
        return dificultad;
    }

    public void setDificultad(TipoDificultad dificultad) {
        this.dificultad = dificultad;
    }

    public TipoLenguaje getLenguaje() {
        return lenguaje;
    }

    public void setLenguaje(TipoLenguaje lenguaje) {
        this.lenguaje = lenguaje;
    }
}
