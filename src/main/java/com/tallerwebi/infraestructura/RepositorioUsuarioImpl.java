package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.Usuario;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("repositorioUsuario")
public class RepositorioUsuarioImpl implements RepositorioUsuario {

  private SessionFactory sessionFactory;

  @Autowired
  public RepositorioUsuarioImpl(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  @Override
  public Usuario buscarUsuario(String email, String password) {
    /* Se utiliza sessionFactory.getCurrentSession() directamente para que el recurso sea gestionado por Spring y PMD no exija cerrarlo manualmente */
    return (Usuario) sessionFactory
      .getCurrentSession()
      .createCriteria(Usuario.class)
      .add(Restrictions.eq("email", email))
      .add(Restrictions.eq("password", password))
      .uniqueResult();
  }

  @Override
  public void guardar(Usuario usuario) {
    sessionFactory.getCurrentSession().save(usuario);
  }

  @Override
  public Usuario buscar(String email) {
    return (Usuario) sessionFactory
      .getCurrentSession()
      .createCriteria(Usuario.class)
      .add(Restrictions.eq("email", email))
      .uniqueResult();
  }

  @Override
  public void modificar(Usuario usuario) {
    sessionFactory.getCurrentSession().update(usuario);
  }

  @Override
  public List<Usuario> obtenerTodosLosUsuarios() {
//    List<Usuario> usuariosFalsos = new ArrayList<>();

//    Usuario u1 = new Usuario();
//    u1.setNombreUsuario("juan22");
//    u1.setPuntaje(100);
//
//    Usuario u2 = new Usuario();
//    u2.setNombreUsuario("pedro44");
//    u2.setPuntaje(60);
//
//    Usuario u3 = new Usuario();
//    u3.setNombreUsuario("leoft2");
//    u3.setPuntaje(20);
//
//    usuariosFalsos.add(u3);
//    usuariosFalsos.add(u1);
//    usuariosFalsos.add(u2);

    return sessionFactory.getCurrentSession().createCriteria(Usuario.class).list();
  }

    @Override
    public Usuario buscarUsuarioPorRol(String rol) {
        return (Usuario) sessionFactory.getCurrentSession().createCriteria(Usuario.class)
                .add(Restrictions.eq("junior", rol)).uniqueResult();
    }
}
