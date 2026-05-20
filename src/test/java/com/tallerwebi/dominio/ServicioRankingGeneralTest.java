package com.tallerwebi.dominio;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.tallerwebi.dominio.excepcion.RankingNoDisponibleException;
import com.tallerwebi.infraestructura.RepositorioUsuario;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ServicioRankingGeneralTest {

  ServicioRanking servicioRanking;
  RepositorioUsuario repositorioUsuarioMock;
  List<Usuario> listaUsuarios;

  @BeforeEach
  public void init() {
    repositorioUsuarioMock = mock(RepositorioUsuario.class);
    servicioRanking = new ServicioRankingImpl(repositorioUsuarioMock);
    listaUsuarios = new ArrayList<>();
  }

  @Test
  public void queAlPedirElRankingDevuelvaTresUsuariosOrdenadosPorPuntajeDescendente() {
    givenExistenTresUsuariosDesordenadosEnLaBaseDeDatos();
    List<Usuario> ranking = whenOrdenoLosUsuariosYPidoElRankingGeneral();
    thenDevuelveLosUsuariosOrdenadosDeMayorAMenor(ranking);
  }

  @Test
  public void queAlPedirElRankingYNoHayaUsuariosDevuelvaUnaListaVacia() {
    givenNoExistenUsuariosEnLaBaseDeDatos();
    List<Usuario> ranking = whenOrdenoLosUsuariosYPidoElRankingGeneral();
    thenDevuelveUnRankingVacio(ranking);
  }

  @Test
  public void queSiElRepositorioFallaYDevuelveNullLanceUnaExcepcion() {
    givenRepositorioDevuelveNull();
    assertThrows(
      RankingNoDisponibleException.class,
      () -> whenOrdenoLosUsuariosYPidoElRankingGeneral()
    );
  }

  private void givenRepositorioDevuelveNull() {
    when(repositorioUsuarioMock.obtenerTodosLosUsuarios()).thenReturn(null);
  }

  private void givenNoExistenUsuariosEnLaBaseDeDatos() {
    when(repositorioUsuarioMock.obtenerTodosLosUsuarios()).thenReturn(new ArrayList<>());
  }

  private void thenDevuelveUnRankingVacio(List<Usuario> ranking) {
    assertThat(ranking, is(empty()));
    assertThat(ranking, hasSize(0));
  }

  private void thenDevuelveLosUsuariosOrdenadosDeMayorAMenor(List<Usuario> ranking) {
    assertThat(ranking, hasSize(3));
    assertThat(ranking.get(0).getPuntaje(), is(100));
    assertThat(ranking.get(1).getPuntaje(), is(60));
    assertThat(ranking.get(2).getPuntaje(), is(20));
  }

  private List<Usuario> whenOrdenoLosUsuariosYPidoElRankingGeneral() {
    return servicioRanking.obtenerRankingGeneral();
  }

  private void givenExistenTresUsuariosDesordenadosEnLaBaseDeDatos() {
    Usuario usuarioPeor = new Usuario();
    usuarioPeor.setPuntaje(20);

    Usuario usuarioMedio = new Usuario();
    usuarioMedio.setPuntaje(60);

    Usuario usuarioMejor = new Usuario();
    usuarioMejor.setPuntaje(100);

    listaUsuarios.add(usuarioPeor);
    listaUsuarios.add(usuarioMedio);
    listaUsuarios.add(usuarioMejor);

    when(repositorioUsuarioMock.obtenerTodosLosUsuarios()).thenReturn(listaUsuarios);
  }
}
