package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.RankingNoDisponibleException;
import com.tallerwebi.infraestructura.RepositorioUsuarioB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class ServicioRankingGeneralTest {

    ServicioRanking servicioRanking;
    RepositorioUsuarioB repositorioUsuarioMock;

    @BeforeEach
    public void init() {
        repositorioUsuarioMock = mock(RepositorioUsuarioB.class);
        servicioRanking = new ServicioRankingImpl(repositorioUsuarioMock);
    }

    @Test
    public void queAlPedirElRankingDevuelvaLosUsuariosYaOrdenadosDeMayorAMenorPorElRepositorio() {
        givenDevuelveTresUsuariosYaOrdenados();
        List<Usuario> ranking = whenPidoElRankingGeneral();
        thenDevuelveLosUsuariosOrdenadosDeMayorAMenor(ranking);
    }

    @Test
    public void queAlPedirElRankingYNoHayaUsuariosDevuelvaUnaListaVacia() {
        givenNoExistenUsuariosEnLaBaseDeDatos();
        List<Usuario> ranking = whenPidoElRankingGeneral();
        thenDevuelveUnRankingVacio(ranking);
    }

    @Test
    public void queSiElRepositorioFallaYDevuelveNullLanceUnaExcepcion() {
        givenRepositorioDevuelveNull();
        assertThrows(RankingNoDisponibleException.class, () -> whenPidoElRankingGeneral());
    }

    private void givenRepositorioDevuelveNull() {
        when(repositorioUsuarioMock.obtenerRankingGeneral()).thenReturn(null);
    }

    private void givenNoExistenUsuariosEnLaBaseDeDatos() {
        when(repositorioUsuarioMock.obtenerRankingGeneral()).thenReturn(new ArrayList<>());
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
        verify(repositorioUsuarioMock, times(1)).obtenerRankingGeneral();
    }

    private List<Usuario> whenPidoElRankingGeneral() {
        return servicioRanking.obtenerRankingGeneral();
    }

    private void givenDevuelveTresUsuariosYaOrdenados() {
        Usuario usuarioPeor = new Usuario();
        usuarioPeor.setPuntaje(20);

        Usuario usuarioMedio = new Usuario();
        usuarioMedio.setPuntaje(60);

        Usuario usuarioMejor = new Usuario();
        usuarioMejor.setPuntaje(100);

        List<Usuario> listaYaOrdenada = List.of(usuarioMejor, usuarioMedio, usuarioPeor);

        when(repositorioUsuarioMock.obtenerRankingGeneral()).thenReturn(listaYaOrdenada);
    }
}
