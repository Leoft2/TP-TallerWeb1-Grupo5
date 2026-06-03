package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.UsuarioExistente;
import com.tallerwebi.infraestructura.RepositorioUsuario;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("servicioLogin")
@Transactional
public class ServicioLoginImpl implements ServicioLogin {

  private final RepositorioUsuario repositorioUsuario;

  @Autowired
  public ServicioLoginImpl(RepositorioUsuario repositorioUsuario) {
    this.repositorioUsuario = repositorioUsuario;
  }

  @Override
  public Usuario consultarUsuario(String email, String password) {
    return repositorioUsuario.buscarUsuario(email, password);
  }

  @Override
  public void registrar(Usuario usuario) throws UsuarioExistente {
    Usuario usuarioEncontrado = repositorioUsuario.buscarUsuario(
      usuario.getEmail(),
      usuario.getPassword()
    );
    if (usuarioEncontrado != null) {
      throw new UsuarioExistente();
    }
    repositorioUsuario.guardar(usuario);
  }

    @Override
    public Usuario buscarUsarioPorRol(String rol) {
        return repositorioUsuario.buscarUsuarioPorRol(rol);
    }

    @Override
    public Usuario buscarUsuarioPorRankingGeneral(List<Usuario> listaUsuarios, Long id) {
        Usuario usuarioBuscado = null;

        for (Usuario usuarios : listaUsuarios) {
            if (usuarios.getId().equals(id)) {
                usuarioBuscado = usuarios;
            }
        }
        return usuarioBuscado;
    }
}
