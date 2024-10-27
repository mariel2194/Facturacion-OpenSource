package com.sg.facturacion.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sg.facturacion.models.Vendedores;
import com.sg.facturacion.repositories.VendedoresRepository;
@Service
public class VendedoresService {

    @Autowired
    private VendedoresRepository vendedoresRepository;

    // Retorna la lista de vendedores
    public List<Vendedores> listVendedores() {
        return vendedoresRepository.findAll();
    }

    // Obtiene un vendedor por su ID
    public Vendedores getVendedorById(Integer id) {
        return vendedoresRepository.findById(id).orElse(null);
    }

    // Guarda un nuevo vendedor
    public void saveNew(Vendedores vendedor) {
        vendedoresRepository.save(vendedor);
    }

    // Actualiza un vendedor existente
    public void updateVendedor(Vendedores vendedor) {
        Vendedores existingVendedor = vendedoresRepository.findById(vendedor.getId()).orElse(null);
        if (existingVendedor != null) {
            existingVendedor.setNombre(vendedor.getNombre());
            existingVendedor.setCedula(vendedor.getCedula());
            existingVendedor.setNoCarnet(vendedor.getNoCarnet());
            existingVendedor.setComision(vendedor.getComision());
            existingVendedor.setTanda(vendedor.getTanda());
            existingVendedor.setFechaIngreso(vendedor.getFechaIngreso());
            existingVendedor.setActivo(vendedor.isActivo());
            vendedoresRepository.save(existingVendedor);
        }
    }

    // Elimina un vendedor por su ID
    public void deleteVendedor(Integer id) {
        vendedoresRepository.deleteById(id);
    }
}
