package com.sg.facturacion.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sg.facturacion.models.Facturacion;

@Repository

public interface FacturacionRepository extends JpaRepository<Facturacion, Integer> {

}
