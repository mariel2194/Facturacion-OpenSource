package com.sg.facturacion.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sg.facturacion.models.Categoria;
import com.sg.facturacion.repositories.CategoriaRepository;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    // Retorna la lista de categorías
    public List<Categoria> listCategorias() {
        return categoriaRepository.findAll();
    }

    // Obtiene una categoría por su ID
    public Categoria getCategoriaById(Integer id) {
        return categoriaRepository.findById(id).orElse(null);
    }

    // Guarda una nueva categoría
    public void saveNew(Categoria categoria) {
        categoriaRepository.save(categoria);
    }

    // Actualiza una categoría existente
    public void updateCategoria(Categoria categoria) {
        Categoria existingCategoria = categoriaRepository.findById(categoria.getId()).orElse(null);
        if (existingCategoria != null) {
            existingCategoria.setDescripcion(categoria.getDescripcion());
            existingCategoria.setActivo(categoria.isActivo());
            categoriaRepository.save(existingCategoria);
        }
    }

    // Elimina una categoría por su ID
    public void deleteCategoria(Integer id) {
        categoriaRepository.deleteById(id);
    }
}
