package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.ServicioRanking;
import com.tallerwebi.dominio.Usuario;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorRanking {

  ServicioRanking servicioRanking;

  @Autowired
  public ControladorRanking(ServicioRanking servicioRanking) {
    this.servicioRanking = servicioRanking;
  }

  @RequestMapping("/ranking")
  public ModelAndView verRanking() {
    ModelMap model = new ModelMap();

    List<Usuario> ranking = servicioRanking.obtenerRankingGeneral();
    model.put("listaCandidatos", ranking);
    model.put("mensaje", "es la vista correcta");
    return new ModelAndView("vista-ranking", model);
  }
}
