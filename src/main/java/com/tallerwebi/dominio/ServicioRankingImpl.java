package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.RankingNoDisponibleException;
import com.tallerwebi.infraestructura.RepositorioUsuario;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ServicioRankingImpl implements ServicioRanking {

  RepositorioUsuario repositorioUsuario;

  @Autowired
  public ServicioRankingImpl(RepositorioUsuario repositorioUsuario) {
    this.repositorioUsuario = repositorioUsuario;
  }

  @Override
  public List<Usuario> obtenerRankingGeneral() {
    List<Usuario> usuariosOriginales = repositorioUsuario.obtenerTodosLosUsuarios();

    if (usuariosOriginales == null) {
      throw new RankingNoDisponibleException();
    }

    List<Usuario> usuariosOrdenables = new ArrayList<>(usuariosOriginales);
    usuariosOrdenables.sort(Comparator.comparing(Usuario::getPuntaje).reversed());
    return usuariosOrdenables;
  }
}
