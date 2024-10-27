package com.sg.facturacion.models;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class)
public class Clientes {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer cliente_id;
	
    @Column(name = "Nombre", nullable=false)
    private String nombre;
    
    @Column(name = "Cedula")
    private String cedula;  
    
    @Column(name = "RNC")
    private String rnc;
    
    @Column(name = "Tipo_Persona")
    private String tipoPersona;    
        
    @Column(name = "Activo")    
    private boolean activo;
    
   
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<AsientoContable> asiento_contable;
    
   
    public Integer getId() {
        return cliente_id;
    }

    public void setId(Integer id) {
        this.cliente_id = id;
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

	public String getTipoPersona() {
		return tipoPersona;
	}

	public void setTipoPersona(String tipoPersona) {
		this.tipoPersona = tipoPersona;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public String getRnc() {
		return rnc;
	}

	public void setRnc(String rnc) {
		this.rnc = rnc;
	}



	

   
    }

    







   


   