package com.sg.facturacion.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sg.facturacion.models.AsientoContable;


@Repository

public interface AsientoContableRepository extends JpaRepository<AsientoContable, Integer> {

}
