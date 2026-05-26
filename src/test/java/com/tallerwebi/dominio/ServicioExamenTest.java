package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.OpcionInvalidaException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

public class ServicioExamenTest {

    RepositorioExamen repositorioExamen = mock(RepositorioExamen.class);
    ServicioExamen servicioExamen = new ServicioExamenImpl(repositorioExamen);

    @Test
    public void dadoQueSiNoIngresoAlgunaOpcionALaHoraDeGenerarUnExamenMeTiraUnaException() {
        givenExamenExistente();
        ExamenDto examenDto = new ExamenDto();
        examenDto.setDificultad(Dificultad.BASICO);
        examenDto.setLenguaje(null);

        assertThrows(OpcionInvalidaException.class, () -> servicioExamen.generarExamen(examenDto.getLenguaje(), examenDto.getDificultad()));
    }

    private void givenExamenExistente() {
    }
}
