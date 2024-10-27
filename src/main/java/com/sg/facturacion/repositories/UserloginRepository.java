package com.sg.facturacion.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sg.facturacion.models.UserLogin;
@Repository

public interface UserloginRepository extends JpaRepository<UserLogin, Integer> {

}
