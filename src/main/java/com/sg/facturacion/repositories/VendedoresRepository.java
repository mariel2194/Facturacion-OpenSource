package com.sg.facturacion.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sg.facturacion.models.Vendedores;
@Repository

public interface VendedoresRepository extends JpaRepository<Vendedores, Integer> {

}
