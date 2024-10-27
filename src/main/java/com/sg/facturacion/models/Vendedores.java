package com.sg.facturacion.models;


import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class)
public class Vendedores {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;
	
    @Column(name = "Nombre", nullable=false)
    private String nombre;
    
    @Column(name = "Cedula")
    private String cedula;  
    
    @Column(name = "no_Carnet")
    private String noCarnet;
    
    @Column(name = "Comision")
    private double comision;
    
    public double getComision() {
		return comision;
	}

	public void setComision(double comision) {
		this.comision = comision;
	}

	@Column(name = "Tanda")
    private String tanda;

    @Column(name = "Fecha de Ingreso")
    private Date fechaIngreso;
    
    
    @Column(name = "Activo")    
    private boolean activo;
        
   
    public String getTanda() {
		return tanda;
	}

	public void setTanda(String tanda) {
		this.tanda = tanda;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

      public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNoCarnet() {
		return noCarnet;
	}

	public void setNoCarnet(String noCarnet) {
		this.noCarnet = noCarnet;
	}	

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
   
    }

    







   


   