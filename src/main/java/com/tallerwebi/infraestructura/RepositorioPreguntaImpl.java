package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.Pregunta;
import com.tallerwebi.dominio.RepositorioPregunta;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioPreguntaImpl implements RepositorioPregunta {

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioPreguntaImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Pregunta buscarPorId(Long id) {
        return null;
    }
}
