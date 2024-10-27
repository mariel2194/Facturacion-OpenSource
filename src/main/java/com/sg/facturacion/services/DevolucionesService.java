package com.sg.facturacion.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sg.facturacion.models.Devoluciones;
import com.sg.facturacion.repositories.DevolucionesRepository;

@Service
public class DevolucionesService {

    @Autowired
    private DevolucionesRepository devolucionesRepository;

    // Retorna la lista de devoluciones
    public List<Devoluciones> listDevoluciones() {
        return devolucionesRepository.findAll();
    }

    // Obtiene una devolución por su ID
    public Devoluciones getDevolucionById(Integer id) {
        return devolucionesRepository.findById(id).orElse(null);
    }

    // Guarda una nueva devolución
    public void saveNew(Devoluciones devolucion) {
        devolucionesRepository.save(devolucion);
    }

    // Actualiza una devolución existente
    public void updateDevolucion(Devoluciones devolucion) {
        Devoluciones existingDevolucion = devolucionesRepository.findById(devolucion.getId()).orElse(null);
        if (existingDevolucion != null) {
            existingDevolucion.setFactura(devolucion.getFactura());
            existingDevolucion.setCliente(devolucion.getCliente());
            existingDevolucion.setArticulo(devolucion.getArticulo());
            existingDevolucion.setFecha(devolucion.getFecha());
            existingDevolucion.setCantidad(devolucion.getCantidad());
            devolucionesRepository.save(existingDevolucion);
        }
    }

    // Elimina una devolución por su ID
    public void deleteDevolucion(Integer id) {
        devolucionesRepository.deleteById(id);
    }
}
