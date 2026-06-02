package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.RankingNoDisponibleException;
import com.tallerwebi.infraestructura.RepositorioUsuarioB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ServicioRankingImpl implements ServicioRanking {

    RepositorioUsuarioB repositorioUsuario;

    @Autowired
    public ServicioRankingImpl(RepositorioUsuarioB repositorioUsuario) {
        this.repositorioUsuario = repositorioUsuario;
    }

    @Override
    public List<Usuario> obtenerRankingGeneral() {
        List<Usuario> rankingOrdenado = repositorioUsuario.obtenerRankingGeneral();

        if (rankingOrdenado == null) {
            throw new RankingNoDisponibleException();
        }

        return rankingOrdenado;
    }
}
