package com.tallerwebi.presentacion;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.instanceOf;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.tallerwebi.dominio.ServicioRanking;
import com.tallerwebi.dominio.Usuario;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

public class ControladorRankingGeneralTest {

  private ControladorRanking controladorRanking;
  private ServicioRanking servicioRankingMock;

  @BeforeEach
  public void init() {
    servicioRankingMock = mock(ServicioRanking.class);
    controladorRanking = new ControladorRanking(servicioRankingMock);
  }

  @Test
  public void queAlPedirElRankingMeDevuelvaLaVistaCorrecta() {
    givenExisteUsuario();
    ModelAndView modelAndView = whenIngresoARanking();
    thenDevuelveVistaRanking(modelAndView, "es la vista correcta");
  }

  @Test
  public void queAlPedirElRankingMeDevuelvaUnaListaDeUsuarios() {
    givenExisteUnaListaDeUsuariosEnElServicio();
    ModelAndView modelAndView = whenIngresoARanking();
    thenDevuelveLaListaDeUsuariosAlModelo(modelAndView);
  }

  private void givenExisteUsuario() {}

  private ModelAndView whenIngresoARanking() {
    return controladorRanking.verRanking();
  }

  private void thenDevuelveVistaRanking(ModelAndView modelAndView, String mensaje) {
    assertThat(modelAndView.getViewName(), equalToIgnoringCase("vista-ranking"));
    assertThat(modelAndView.getModel().get("mensaje").toString(), equalToIgnoringCase(mensaje));
  }

  private void givenExisteUnaListaDeUsuariosEnElServicio() {
    List<Usuario> usuariosFalsos = new ArrayList<>();
    usuariosFalsos.add(new Usuario());
    when(servicioRankingMock.obtenerRankingGeneral()).thenReturn(usuariosFalsos);
  }

  private void thenDevuelveLaListaDeUsuariosAlModelo(ModelAndView modelAndView) {
    assertThat(modelAndView.getModel().get("listaCandidatos"), instanceOf(List.class));
  }
}
