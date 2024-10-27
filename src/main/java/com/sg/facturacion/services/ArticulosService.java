package com.sg.facturacion.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sg.facturacion.models.Articulos;
import com.sg.facturacion.repositories.ArticulosRepository;


@Service
public class ArticulosService {

    @Autowired
    private ArticulosRepository articulosRepository;

    // Retorna la lista de artículos
    public List<Articulos> listArticulos() {
        return articulosRepository.findAll();
    }

    // Obtiene un artículo por su ID
    public Articulos getArticuloById(Integer id) {
        return articulosRepository.findById(id).orElse(null);
    }

    // Guarda un nuevo artículo
    public void saveNew(Articulos articulo) {
        articulosRepository.save(articulo);
    }

    // Actualiza un artículo existente
    public void updateArticulo(Articulos articulo) {
        Articulos existingArticulo = articulosRepository.findById(articulo.getId()).orElse(null);
        if (existingArticulo != null) {
            existingArticulo.setDescripcion(articulo.getDescripcion());
            existingArticulo.setPrecio_unitario(articulo.getPrecio_unitario());
            existingArticulo.setActivo(articulo.getActivo());
            existingArticulo.setCantidad(articulo.getCantidad());
            existingArticulo.setCategoria(articulo.getCategoria()); 
            articulosRepository.save(existingArticulo);
        }
    }

    // Elimina un artículo por su ID
    public void deleteArticulo(Integer id) {
        articulosRepository.deleteById(id);
    }
}
