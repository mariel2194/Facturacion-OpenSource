package com.sg.facturacion.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sg.facturacion.models.Devoluciones;

@Repository

public interface DevolucionesRepository extends JpaRepository<Devoluciones, Integer> {

}
