package com.sg.facturacion.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sg.facturacion.models.Clientes;

@Repository

public interface ClienteRepository extends JpaRepository<Clientes, Integer> {

}