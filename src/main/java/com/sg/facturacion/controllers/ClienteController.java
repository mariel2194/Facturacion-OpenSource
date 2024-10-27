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

import com.sg.facturacion.models.Clientes;
import com.sg.facturacion.services.ClienteService;

import org.slf4j.Logger;

@Controller
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    private static final Logger logger = LoggerFactory.getLogger(ClienteController.class);

    @GetMapping("/clientes")
    public String getClientes(Model model) {
        List<Clientes> clientesList = clienteService.listClientes();
        model.addAttribute("clientes", clientesList);
        return "clientes";
    }

    @PostMapping("/clientes/addnew")
    public String addNew(Clientes cliente, Model model) {
        try {
            clienteService.saveNew(cliente);
            return "redirect:/clientes";
        } catch (Exception e) {
            logger.error("Error al guardar el cliente", e);
            model.addAttribute("errorMessage", "Error al guardar el cliente: " + e.getMessage());
            model.addAttribute("cliente", cliente);
            return "crearCliente";
        }
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, Model model) {
        logger.error("Error en el sistema", e);
        model.addAttribute("errorMessage", "Error en el sistema: " + e.getMessage());
        return "error";
    }

    @GetMapping("/clientes/edit/{id}")
    @ResponseBody
    public ResponseEntity<Clientes> getEditClienteForm(@PathVariable("id") Integer id) {
        Clientes cliente = clienteService.getClienteById(id);
        if (cliente == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    @PostMapping("/clientes/update")
    public String updateCliente(@ModelAttribute Clientes cliente) {
        clienteService.updateCliente(cliente);
        return "redirect:/clientes";
    }

    @PostMapping("/clientes/delete/{id}")
    public String deleteCliente(@PathVariable("id") Integer id) {
        clienteService.deleteCliente(id);
        return "redirect:/clientes";
    }
}
