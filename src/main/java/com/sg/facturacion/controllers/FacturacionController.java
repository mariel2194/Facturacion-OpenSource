package com.sg.facturacion.controllers;

import org.slf4j.LoggerFactory;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sg.facturacion.models.Facturacion;
import com.sg.facturacion.services.FacturacionService;

import org.slf4j.Logger;

@Controller
public class FacturacionController {

    @Autowired
    private FacturacionService facturacionService;

    private static final Logger logger = LoggerFactory.getLogger(FacturacionController.class);

    @GetMapping("/facturacion")
    public String getFacturaciones(Model model) {
        List<Facturacion> facturacionesList = facturacionService.listFacturaciones();
        model.addAttribute("facturaciones", facturacionesList);
        return "facturacion";
    }

    @PostMapping("/facturacion/addnew")
    public String addNew(Facturacion facturacion, Model model) {
        try {
            facturacionService.saveNew(facturacion);
            return "redirect:/facturacion";
        } catch (Exception e) {
            logger.error("Error al guardar la facturación", e);
            model.addAttribute("errorMessage", "Error al guardar la facturación: " + e.getMessage());
            model.addAttribute("facturacion", facturacion);
            return "crearFacturacion";
        }
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, Model model) {
        logger.error("Error en el sistema", e);
        model.addAttribute("errorMessage", "Error en el sistema: " + e.getMessage());
        return "error";
    }

    @GetMapping("/facturacion/edit/{id}")
    @ResponseBody
    public ResponseEntity<Facturacion> getEditFacturacionForm(@PathVariable("id") Integer id) {
        Facturacion facturacion = facturacionService.getFacturacionById(id);
        if (facturacion == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(facturacion, HttpStatus.OK);
    }

    @PostMapping("/facturacion/update")
    public String updateFacturacion(@ModelAttribute Facturacion facturacion) {
        facturacionService.updateFacturacion(facturacion);
        return "redirect:/facturacion";
    }

    @PostMapping("/facturacion/delete/{id}")
    public String deleteFacturacion(@PathVariable("id") Integer id) {
        facturacionService.deleteFacturacion(id);
        return "redirect:/facturacion";
    }
}
