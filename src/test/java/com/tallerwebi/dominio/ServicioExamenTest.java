package com.tallerwebi.dominio;

import com.tallerwebi.infraestructura.RespositorioExamenImpl;
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
        examenDto.setDificultad("basico");
        examenDto.setLenguaje("");

        assertThrows(OpcionInvalidaException.class, () -> servicioExamen.generarExamen(examenDto.getDificultad(), examenDto.getLenguaje()));
    }

    private void givenExamenExistente() {
    }
}
