package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.*;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RespositorioExamenImpl implements RepositorioExamen {

   SessionFactory sessionFactory;

   @Autowired
   public RespositorioExamenImpl(SessionFactory sessionFactory) {
       this.sessionFactory = sessionFactory;
   }

  @Override
  public List<Pregunta> buscarExamenPorLenguajeYDificultad(TipoLenguaje lenguaje, TipoDificultad dificultad) {
      return sessionFactory.getCurrentSession()
              .createCriteria(Pregunta.class)
              .add(Restrictions.eq("lenguaje", lenguaje))
              .add(Restrictions.eq("dificultad", dificultad)).list();
  }

  @Override
  public void guardarExamen(Examen examen) {
        sessionFactory.getCurrentSession().save(examen);
  }
}
