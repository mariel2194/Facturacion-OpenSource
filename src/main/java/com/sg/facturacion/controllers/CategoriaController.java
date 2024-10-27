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

import com.sg.facturacion.models.Categoria;
import com.sg.facturacion.services.CategoriaService;

import org.slf4j.Logger;

@Controller
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    private static final Logger logger = LoggerFactory.getLogger(CategoriaController.class);

    @GetMapping("/categorias")
    public String getCategorias(Model model) {
        List<Categoria> categoriasList = categoriaService.listCategorias();
        model.addAttribute("categorias", categoriasList);
        return "categorias";
    }

    @PostMapping("/categorias/addnew")
    public String addNew(Categoria categoria, Model model) {
        try {
            categoriaService.saveNew(categoria);
            return "redirect:/categorias";
        } catch (Exception e) {
            logger.error("Error al guardar la categoría", e);
            model.addAttribute("errorMessage", "Error al guardar la categoría: " + e.getMessage());
            model.addAttribute("categoria", categoria);
            return "crearCategoria";
        }
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, Model model) {
        logger.error("Error en el sistema", e);
        model.addAttribute("errorMessage", "Error en el sistema: " + e.getMessage());
        return "error";
    }

    @GetMapping("/categorias/edit/{id}")
    @ResponseBody
    public ResponseEntity<Categoria> getEditCategoriaForm(@PathVariable("id") Integer id) {
        Categoria categoria = categoriaService.getCategoriaById(id);
        if (categoria == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(categoria, HttpStatus.OK);
    }

    @PostMapping("/categorias/update")
    public String updateCategoria(@ModelAttribute Categoria categoria) {
        categoriaService.updateCategoria(categoria);
        return "redirect:/categorias";
    }

    @PostMapping("/categorias/delete/{id}")
    public String deleteCategoria(@PathVariable("id") Integer id) {
        categoriaService.deleteCategoria(id);
        return "redirect:/categorias";
    }
}
