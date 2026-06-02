package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.Usuario;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RepositorioUsuarioBImpl implements RepositorioUsuarioB {


    SessionFactory sessionFactory;

    @Autowired
    public RepositorioUsuarioBImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void guardar(Usuario usuario) {
        this.sessionFactory.getCurrentSession().save(usuario);
    }

    @Override
    public List<Usuario> obtenerRankingGeneral() {
        return this.sessionFactory.getCurrentSession().createCriteria(Usuario.class).addOrder(Order.desc("puntaje")).list();

    }
}
