package com.sg.facturacion.models;


import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class)
public class Devoluciones {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;
	
    
	    @OneToOne
	    private Facturacion factura;	

	    @ManyToOne
	    @JoinColumn(name = "clienteId")
	    private Clientes cliente;
	    
	    @ManyToOne
	    private Articulos articulo;
	    
	    @Column(name = "Fecha")
	    private Date fecha;	    
	    
	    @Column(name = "Cantidad")
	    private int cantidad;
	    
	   		
		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public Facturacion getFactura() {
			return factura;
		}

		public void setFactura(Facturacion factura) {
			this.factura = factura;
		}

		public Clientes getCliente() {
			return cliente;
		}

		public void setCliente(Clientes cliente) {
			this.cliente = cliente;
		}

		public Articulos getArticulo() {
			return articulo;
		}

		public void setArticulo(Articulos articulo) {
			this.articulo = articulo;
		}

		public Date getFecha() {
			return fecha;
		}

		public void setFecha(Date fecha) {
			this.fecha = fecha;
		}

		public int getCantidad() {
			return cantidad;
		}

		public void setCantidad(int cantidad) {
			this.cantidad = cantidad;
		}

		
   
    }

    







   


   