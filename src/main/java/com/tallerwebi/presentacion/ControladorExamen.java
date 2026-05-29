package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.*;

import java.time.LocalTime;
import java.util.List;

import com.tallerwebi.dominio.excepcion.OpcionInvalidaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorExamen {

    ServicioExamen servicioExamen;

    @Autowired
    public ControladorExamen(ServicioExamen servicioExamen) {
        this.servicioExamen = servicioExamen;
    }

    @RequestMapping("/examenes")
    public ModelAndView opcionesExamen() {
        ModelMap model = new ModelMap();
        model.put("examenDto", new ExamenDto());
        return new ModelAndView("elegir-examen", model);
    }

    @RequestMapping(path = "/examen", method = RequestMethod.POST)
    public ModelAndView generarExamen(@ModelAttribute("examenDto") ExamenDto examenDto) {
        ModelMap model = new ModelMap();
        Examen examen = new Examen();

        try {
            Pregunta pregunta = servicioExamen.generarExamen(examenDto.getLenguaje(), examenDto.getDificultad());
            model.put("preguntas", pregunta);
            Respuesta respuestas = new Respuesta();
            respuestas.setPregunta(pregunta);
            model.put("respuestas", respuestas);
        } catch (OpcionInvalidaException e) {
            model.put("error", "Debe elegir las opciones");
            return new ModelAndView("examenes", model);
        }

        examen.setTiempoInicio(LocalTime.now());
        examen.setDificultad(examenDto.getDificultad());
        examen.setLenguaje(examenDto.getLenguaje());

        model.put("examen", examen);
        model.put("generado", "El examen se genero correctamente");
        return new ModelAndView("examen-generado", model);
    }

    @RequestMapping(path = "/guardar-examen", method = RequestMethod.POST)
    public ModelAndView guardarExamen(@ModelAttribute("examen") Examen examen) {
        Integer puntaje = servicioExamen.calcularPuntaje(examen);
        examen.setTiempoFinal(LocalTime.now());
        examen.setPuntaje(puntaje);
        servicioExamen.guardarExamen(examen);

        ModelMap model = new ModelMap();
        model.put("puntaje", puntaje);

        return new ModelAndView("resultado-puntaje", model);
    }
}
