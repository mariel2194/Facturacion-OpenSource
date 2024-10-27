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

import com.sg.facturacion.models.Vendedores;
import com.sg.facturacion.services.VendedoresService;

import org.slf4j.Logger;

@Controller
public class VendedoresController {

    @Autowired
    private VendedoresService vendedoresService;

    private static final Logger logger = LoggerFactory.getLogger(VendedoresController.class);

    @GetMapping("/vendedores")
    public String getVendedores(Model model) {
        List<Vendedores> vendedoresList = vendedoresService.listVendedores();
        model.addAttribute("vendedores", vendedoresList);
        return "vendedores";
    }

    @PostMapping("/vendedores/addnew")
    public String addNew(Vendedores vendedor, Model model) {
        try {
            vendedoresService.saveNew(vendedor);
            return "redirect:/vendedores";
        } catch (Exception e) {
            logger.error("Error al guardar el vendedor", e);
            model.addAttribute("errorMessage", "Error al guardar el vendedor: " + e.getMessage());
            model.addAttribute("vendedor", vendedor);
            return "crearVendedor";
        }
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, Model model) {
        logger.error("Error en el sistema", e);
        model.addAttribute("errorMessage", "Error en el sistema: " + e.getMessage());
        return "error";
    }

    @GetMapping("/vendedores/edit/{id}")
    @ResponseBody
    public ResponseEntity<Vendedores> getEditVendedorForm(@PathVariable("id") Integer id) {
        Vendedores vendedor = vendedoresService.getVendedorById(id);
        if (vendedor == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(vendedor, HttpStatus.OK);
    }

    @PostMapping("/vendedores/update")
    public String updateVendedor(@ModelAttribute Vendedores vendedor) {
        vendedoresService.updateVendedor(vendedor);
        return "redirect:/vendedores";
    }

    @PostMapping("/vendedores/delete/{id}")
    public String deleteVendedor(@PathVariable("id") Integer id) {
        vendedoresService.deleteVendedor(id);
        return "redirect:/vendedores";
    }
}
