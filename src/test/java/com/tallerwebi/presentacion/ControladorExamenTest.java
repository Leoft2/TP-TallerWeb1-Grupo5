package com.tallerwebi.presentacion;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.mockito.Mockito.*;

import com.tallerwebi.dominio.*;
import com.tallerwebi.dominio.excepcion.OpcionInvalidaException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


public class ControladorExamenTest {

  /*El sistema debe generar una vista con un cuestionario técnico de opción múltiple
    con un temporizador visible, donde si el tiempo llega a cero la prueba se cierra
    automáticamente y suma 0 puntos en esa pregunta.*/

  private static final TipoLenguaje lenguje = TipoLenguaje.JAVA;
  private static final TipoDificultad dificultad = TipoDificultad.BASICO;

  ServicioExamen servicioExamen = mock(ServicioExamen.class);
  RepositorioPregunta repositorioPregunta = mock(RepositorioPregunta.class);
  RepositorioExamen repositorioExamen = mock(RepositorioExamen.class);
  ServicioExamenImpl servicioExamenImpl = new ServicioExamenImpl(repositorioExamen, repositorioPregunta);
  ControladorExamen controladorExamen = new ControladorExamen(servicioExamenImpl);
  HttpServletRequest request = mock(HttpServletRequest.class);
  HttpSession session = mock(HttpSession.class);

  @BeforeEach
  public void init() {
      when(request.getSession()).thenReturn(session);
  }

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
      verify(session).setAttribute(eq("preguntas"), any(List.class));
  }

  private ModelAndView whenGenerarExamen(TipoLenguaje lenguaje, TipoDificultad dificultad) {
    when(request.getSession()).thenReturn(session);
    ExamenDto examenDto = new ExamenDto();
    examenDto.setLenguaje(lenguaje);
    examenDto.setDificultad(dificultad);
    ModelAndView modelAndView = controladorExamen.generarExamen(examenDto, request);
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
