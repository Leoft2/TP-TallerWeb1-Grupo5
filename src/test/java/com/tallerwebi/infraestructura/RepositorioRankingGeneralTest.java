package com.tallerwebi.infraestructura;


import com.tallerwebi.dominio.Usuario;
import com.tallerwebi.integracion.config.HibernateTestConfig;
import com.tallerwebi.integracion.config.SpringWebTestConfig;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = {SpringWebTestConfig.class, HibernateTestConfig.class})
public class RepositorioRankingGeneralTest {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    RepositorioUsuarioB repositorioUsuarioB;


    @Test
    @Transactional
    @Rollback
    public void queSePuedaGuardarUnUsuarioParaElRanking() {
        //given
        Usuario usuario = new Usuario();
        usuario.setPuntaje(50);
        //when
        repositorioUsuarioB.guardar(usuario);
        //then
        assertThat(usuario.getId(), notNullValue());
    }


    @Test
    @Transactional
    @Rollback
    public void queSePuedaObtenerElRankingDeUsuariosOrdenadoPorPuntajeDescendente() {

        //given
        Usuario usuario1 = new Usuario();
        usuario1.setPuntaje(50);
        Usuario usuario2 = new Usuario();
        usuario2.setPuntaje(120); // Este debería ser el #1
        Usuario usuario3 = new Usuario();
        usuario3.setPuntaje(90);

        this.sessionFactory.getCurrentSession().save(usuario1);
        this.sessionFactory.getCurrentSession().save(usuario2);
        this.sessionFactory.getCurrentSession().save(usuario3);

        //when
        List<Usuario> ranking = repositorioUsuarioB.obtenerRankingGeneral();

        //then
        assertThat(ranking, hasSize(3));
        assertThat(ranking.get(0).getPuntaje(), equalTo(120));
        assertThat(ranking.get(1).getPuntaje(), equalTo(90));
        assertThat(ranking.get(2).getPuntaje(), equalTo(50));

    }


}
