package com.sg.facturacion.controllers;

import com.sg.facturacion.models.AsientoContable;
import com.sg.facturacion.services.*;
import com.sg.facturacion.services.AsientoContableService;
import com.sg.facturacion.services.ClienteService;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@Controller
@RequestMapping("/asientos")
public class AsientosController {

    @Autowired
    private AsientoContableService asientoContableService; 

    @Autowired
    private ClienteService clienteService; 

    private static final Logger logger = LoggerFactory.getLogger(AsientosController.class);

    // Obtener todos los asientos contables
    @GetMapping
    public String getAsientos(Model model) {
        List<AsientoContable> asientosList = asientoContableService.listAsientosContables(); // Método que debes implementar en el servicio
        model.addAttribute("asientos", asientosList);
        return "asientos"; // Nombre de la vista
    }

    // Crear un nuevo asiento contable
    @PostMapping("/addnew")
    public String addNew(@ModelAttribute AsientoContable asientoContable, Model model) {
        try {
            asientoContableService.saveNew(asientoContable); // Método para guardar el asiento contable
            return "redirect:/asientos";
        } catch (Exception e) {
            logger.error("Error al guardar el asiento contable", e);
            model.addAttribute("errorMessage", "Error al guardar el asiento contable: " + e.getMessage());
            model.addAttribute("asiento", asientoContable);
            return "crearAsiento"; // Nombre de la vista para crear un nuevo asiento
        }
    }

    // Manejar excepciones
    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, Model model) {
        logger.error("Error en el sistema", e);
        model.addAttribute("errorMessage", "Error en el sistema: " + e.getMessage());
        return "error"; // Nombre de la vista para mostrar el error
    }

    // Obtener el formulario de edición para un asiento contable
    @GetMapping("/edit/{id}")
    @ResponseBody
    public ResponseEntity<AsientoContable> getEditAsientoForm(@PathVariable("id") Integer id) {
        AsientoContable asientoContable = asientoContableService.getAsientoContableById(id); // Método para obtener un asiento por ID
        if (asientoContable == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(asientoContable, HttpStatus.OK);
    }

    // Actualizar un asiento contable
    @PostMapping("/update")
    public String updateAsiento(@ModelAttribute AsientoContable asientoContable) {
        asientoContableService.updateAsientoContable(asientoContable); // Método para actualizar el asiento contable
        return "redirect:/asientos";
    }

    // Eliminar un asiento contable
    @PostMapping("/delete/{id}")
    public String deleteAsiento(@PathVariable("id") Integer id) {
        asientoContableService.deleteAsientoContable(id); // Método para eliminar el asiento contable
        return "redirect:/asientos";
    }
}
