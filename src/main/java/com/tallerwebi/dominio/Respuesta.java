package com.tallerwebi.dominio;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Respuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    private List<String> respuestaUsuario = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<String> getRespuestaUsuario() {
        return respuestaUsuario;
    }

    public void setRespuestaUsuario(List<String> respuestaUsuario) {
        this.respuestaUsuario = respuestaUsuario;
    }
}
