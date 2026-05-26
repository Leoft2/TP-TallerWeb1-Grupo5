package com.tallerwebi.presentacion;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

import com.tallerwebi.dominio.Dificultad;
import com.tallerwebi.dominio.ExamenDto;
import com.tallerwebi.dominio.Lenguaje;
import com.tallerwebi.dominio.excepcion.OpcionInvalidaException;
import com.tallerwebi.dominio.ServicioExamen;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

public class ControladorExamenTest {

  /*El sistema debe generar una vista con un cuestionario técnico de opción múltiple
    con un temporizador visible, donde si el tiempo llega a cero la prueba se cierra
    automáticamente y suma 0 puntos en esa pregunta.*/

  private static final Lenguaje lenguje = Lenguaje.JAVA;
  private static final Dificultad dificultad = Dificultad.BASICO;

  ServicioExamen servicioExamen = mock(ServicioExamen.class);
  ControladorExamen controladorExamen = new ControladorExamen(servicioExamen);

  @Test
  public void elExamenSeCreaSiIngresaElLenguajeYDificultadAdecuadoDeLasOpciones() {
    givenExamenExistente();
    ModelAndView model = whenGenerarExamen(lenguje, dificultad);
    thenExamenGeneradoExitosamente(model);
  }

  private void thenExamenGeneradoExitosamente(ModelAndView modelAndView) {
    assertThat(modelAndView.getViewName(), equalToIgnoringCase("examen-generado"));
    assertThat(
      modelAndView.getModel().get("generado").toString(),
      equalToIgnoringCase("El examen se genero correctamente")
    );
  }

  private ModelAndView whenGenerarExamen(Lenguaje lenguaje, Dificultad dificultad) {
    ExamenDto examenDto = new ExamenDto();
    examenDto.setLenguaje(lenguaje);
    examenDto.setDificultad(dificultad);
    ModelAndView modelAndView = controladorExamen.generarExamen(examenDto);
    return modelAndView;
  }

  private void givenExamenExistente() {}

  @Test
  public void dadoQueElUsuarioNoEligioLasOpcionesDeLenguajeODificultadLanzaOpcionInvalidaException() {
    givenUsuarioExistente();
    doThrow(OpcionInvalidaException.class).when(servicioExamen).generarExamen(null, null);
    ModelAndView model = whenGenerarExamen(null, null);
    thenExamenNoGeneradoExitosamente(model);
  }

  private void thenExamenNoGeneradoExitosamente(ModelAndView model) {
    assertThat(model.getViewName(), equalToIgnoringCase("examenes"));
    assertThat(
      model.getModel().get("error").toString(),
      equalToIgnoringCase("Debe elegir las opciones")
    );
  }

  private void givenUsuarioExistente() {}

  @Test
  public void dadoQueNoExisteUnExamenALaHoraDeCrearloLoGuardoEnUnaBaseDeDatos() {
      givenUsuarioExistente();
      ModelAndView model = whenGenerarExamen(lenguje, dificultad);
      thenExamenGeneradoExitosamente(model);
  }
}
