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

import com.sg.facturacion.models.Devoluciones;
import com.sg.facturacion.services.DevolucionesService;

import org.slf4j.Logger;

@Controller
public class DevolucionesController {

    @Autowired
    private DevolucionesService devolucionesService;

    private static final Logger logger = LoggerFactory.getLogger(DevolucionesController.class);

    @GetMapping("/devoluciones")
    public String getDevoluciones(Model model) {
        List<Devoluciones> devolucionesList = devolucionesService.listDevoluciones();
        model.addAttribute("devoluciones", devolucionesList);
        return "devoluciones";
    }

    @PostMapping("/devoluciones/addnew")
    public String addNew(Devoluciones devolucion, Model model) {
        try {
            devolucionesService.saveNew(devolucion);
            return "redirect:/devoluciones";
        } catch (Exception e) {
            logger.error("Error al guardar la devolución", e);
            model.addAttribute("errorMessage", "Error al guardar la devolución: " + e.getMessage());
            model.addAttribute("devolucion", devolucion);
            return "crearDevolucion";
        }
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, Model model) {
        logger.error("Error en el sistema", e);
        model.addAttribute("errorMessage", "Error en el sistema: " + e.getMessage());
        return "error";
    }

    @GetMapping("/devoluciones/edit/{id}")
    @ResponseBody
    public ResponseEntity<Devoluciones> getEditDevolucionForm(@PathVariable("id") Integer id) {
        Devoluciones devolucion = devolucionesService.getDevolucionById(id);
        if (devolucion == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(devolucion, HttpStatus.OK);
    }

    @PostMapping("/devoluciones/update")
    public String updateDevolucion(@ModelAttribute Devoluciones devolucion) {
        devolucionesService.updateDevolucion(devolucion);
        return "redirect:/devoluciones";
    }

    @PostMapping("/devoluciones/delete/{id}")
    public String deleteDevolucion(@PathVariable("id") Integer id) {
        devolucionesService.deleteDevolucion(id);
        return "redirect:/devoluciones";
    }
}
