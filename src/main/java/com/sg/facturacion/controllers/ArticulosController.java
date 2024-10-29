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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sg.facturacion.models.Articulos;
import com.sg.facturacion.models.Categoria;
import com.sg.facturacion.services.ArticulosService;
import com.sg.facturacion.services.CategoriaService;

import org.slf4j.Logger;

@Controller
public class ArticulosController {

    @Autowired
    private ArticulosService articulosService;

    @Autowired
    private CategoriaService categoriaService;

    private static final Logger logger = LoggerFactory.getLogger(ArticulosController.class);

    @GetMapping("/articulos")
    public String getArticulos(Model model) {
        List<Articulos> articulosList = articulosService.listArticulos();
        List<Categoria> categoriasList = categoriaService.listCategorias();
        model.addAttribute("articulos", articulosList);
        model.addAttribute("categorias", categoriasList);
        return "articulos";
    }

    @PostMapping("/articulos/addnew")
    public String addNew(Articulos articulo, Model model) {
        try {
            articulosService.saveNew(articulo);
            return "redirect:/articulos";
        } catch (Exception e) {
            logger.error("Error al guardar el artículo", e);
            model.addAttribute("errorMessage", "Error al guardar el artículo: " + e.getMessage());
            model.addAttribute("articulo", articulo);
            List<Categoria> categoriasList = categoriaService.listCategorias();
            model.addAttribute("categorias", categoriasList);
            return "crearArticulo";
        }
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, Model model) {
        logger.error("Error en el sistema", e);
        model.addAttribute("errorMessage", "Error en el sistema: " + e.getMessage());
        return "error";
    }

    @GetMapping("/articulos/edit/{id}")
    @ResponseBody
    public ResponseEntity<Articulos> getEditArticulosForm(@PathVariable("id") Integer id) {
        Articulos articulo = articulosService.getArticuloById(id);
        if (articulo == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(articulo, HttpStatus.OK);
    }

    @PostMapping("/articulos/update")
    public String updateArticulo(@ModelAttribute Articulos articulo) {
        articulosService.updateArticulo(articulo);
        return "redirect:/articulos";
    }

    @PostMapping("/articulos/delete/{id}")
    public String deleteArticulo(@PathVariable("id") Integer id, @RequestParam("cantidad") Integer cantidad, Model model) {
        Articulos articulo = articulosService.getArticuloById(id);

        if (articulo != null) {
            if (articulo.getCantidad() > cantidad) {
                articulo.setCantidad(articulo.getCantidad() - cantidad);
                articulosService.updateArticulo(articulo);
            } else {
                model.addAttribute("error", "No se puede disminuir más de la cantidad disponible.");
                return "redirect:/articulos";
            }
        }
        return "redirect:/articulos";
    }


}

