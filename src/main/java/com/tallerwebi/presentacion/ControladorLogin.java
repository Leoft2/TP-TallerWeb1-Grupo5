package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.ServicioLogin;
import com.tallerwebi.dominio.ServicioRanking;
import com.tallerwebi.dominio.Usuario;
import com.tallerwebi.dominio.excepcion.UsuarioExistente;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ControladorLogin {

  private ServicioLogin servicioLogin;
  private ServicioRanking servicioRanking;

  @Autowired
  public ControladorLogin(ServicioLogin servicioLogin, ServicioRanking servicioRanking) {
    this.servicioLogin = servicioLogin;
    this.servicioRanking = servicioRanking;
  }

  @RequestMapping("/login")
  public ModelAndView irALogin() {
    ModelMap modelo = new ModelMap();
    modelo.put("datosLogin", new DatosLogin());
    return new ModelAndView("login", modelo);
  }

  @RequestMapping(path = "/validar-login", method = RequestMethod.POST)
  public ModelAndView validarLogin(
    @ModelAttribute("datosLogin") DatosLogin datosLogin,
    HttpServletRequest request
  ) {
    Usuario usuarioBuscado = servicioLogin.consultarUsuario(
      datosLogin.getEmail(),
      datosLogin.getPassword()
    );
    if (usuarioBuscado != null) {
      request.getSession().setAttribute("ROL", usuarioBuscado.getRol());
      return new ModelAndView("redirect:/home");
    } else {
      /* Se instancia el ModelMap solo cuando es necesario (en el flujo de error) para evitar anomalías en el flujo de datos (DU-anomaly de PMD) */
      ModelMap model = new ModelMap();
      model.put("error", "Usuario o clave incorrecta");
      return new ModelAndView("login", model);
    }
  }

  @RequestMapping(path = "/registrarme", method = RequestMethod.POST)
  public ModelAndView registrarme(@ModelAttribute("usuario") Usuario usuario) {
    ModelMap model = new ModelMap();
    try {
      servicioLogin.registrar(usuario);
    } catch (UsuarioExistente e) {
      model.put("error", "El usuario ya existe");
      return new ModelAndView("nuevo-usuario", model);
    } catch (Exception e) {
      model.put("error", "Error al registrar el nuevo usuario");
      return new ModelAndView("nuevo-usuario", model);
    }
    return new ModelAndView("redirect:/login");
  }

  @RequestMapping(path = "/nuevo-usuario", method = RequestMethod.GET)
  public ModelAndView nuevoUsuario() {
    ModelMap model = new ModelMap();
    model.put("usuario", new Usuario());
    return new ModelAndView("nuevo-usuario", model);
  }

  @RequestMapping(path = "/home", method = RequestMethod.GET)
  public ModelAndView irAHome() {
    return new ModelAndView("home");
  }

  @RequestMapping(path = "/", method = RequestMethod.GET)
  public ModelAndView inicio() {
    return new ModelAndView("redirect:/login");
  }

  @RequestMapping(path = "inicio-usuario", method = RequestMethod.GET)
  public ModelAndView vistaRol(HttpServletRequest request){
      ModelMap model = new ModelMap();
      Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");

//      Usuario usuario = new Usuario();
//      usuario.setId(3L);

    if (usuario == null) {
        model.put("error", "usuario no registrado");
        return new ModelAndView("redirect:/login");
    }

      List<Usuario> listaUsuarios = servicioRanking.obtenerRankingGeneral();
      Usuario usuarioEncontrado = servicioLogin.buscarUsuarioPorRankingGeneral(listaUsuarios, usuario.getId());
      model.put("usuario", usuarioEncontrado);
      return new ModelAndView("home-junior", model);
  }
}
