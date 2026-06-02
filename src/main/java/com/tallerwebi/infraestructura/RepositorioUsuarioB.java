package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.Usuario;

import java.util.List;

public interface RepositorioUsuarioB {
    void guardar(Usuario usuario);

    List<Usuario> obtenerRankingGeneral();
}
