package com.sg.facturacion.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sg.facturacion.models.AsientoContable;
import com.sg.facturacion.repositories.AsientoContableRepository;

@Service
public class AsientoContableService {

    @Autowired
    private AsientoContableRepository AsientoContableRepository;

    // Retorna la lista de Asientos contables
    public List<AsientoContable> listAsientosContables() {
        return AsientoContableRepository.findAll();
    }

    // Obtiene una Asiento contable por su ID
    public AsientoContable getAsientoContableById(Integer id) {
        return AsientoContableRepository.findById(id).orElse(null);
    }

    // Guarda una nueva Asiento contable
    public void saveNew(AsientoContable AsientoContable) {
        AsientoContableRepository.save(AsientoContable);
    }

    // Actualiza una Asiento contable existente
    public void updateAsientoContable(AsientoContable AsientoContable) {
        AsientoContable existingAsientoContable = AsientoContableRepository.findById(AsientoContable.getId()).orElse(null);
        if (existingAsientoContable != null) {
            existingAsientoContable.setDescripcion(AsientoContable.getDescripcion());
            existingAsientoContable.setCuenta(AsientoContable.getCuenta());
            existingAsientoContable.setTipoMovimiento(AsientoContable.getTipoMovimiento());
            existingAsientoContable.setFecha(AsientoContable.getFecha());
            AsientoContableRepository.save(existingAsientoContable);
        }
    }

    // Elimina una Asiento contable por su ID
    public void deleteAsientoContable(Integer id) {
        AsientoContableRepository.deleteById(id);
    }
}
