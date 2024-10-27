package com.sg.facturacion.models;

import java.util.Calendar;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class)
public class Facturacion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private Integer id;

	@ManyToOne
	private Articulos articulo;

	@ManyToOne
	private Clientes cliente;

	@ManyToOne
	private Vendedores vendedor;

	@Column(name = "Fecha")
	private Date fecha;

	
	@Column(name = "Cantidad")
	private int cantidad;
	
	@Column(name = "Comentario", nullable = false)
	private String comentario;
	
    @Column(name = "Monto_Total")
	private double montoTotal;

	@OneToOne(mappedBy = "factura", cascade = CascadeType.ALL)
	private Devoluciones devolucion;

	@PrePersist
	public void prePersist() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha);	
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public Articulos getArticulo() {
		return articulo;
	}


	public void setArticulo(Articulos articulo) {
		this.articulo = articulo;
	}


	public Clientes getCliente() {
		return cliente;
	}


	public void setCliente(Clientes cliente) {
		this.cliente = cliente;
	}


	public Vendedores getVendedor() {
		return vendedor;
	}


	public void setVendedor(Vendedores vendedor) {
		this.vendedor = vendedor;
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


	public Devoluciones getDevolucion() {
		return devolucion;
	}


	public String getComentario() {
		return comentario;
	}


	public void setComentario(String comentario) {
		this.comentario = comentario;
	}


	public double getMontoTotal() {
		return montoTotal;
	}


	public void setMontoTotal(double montoTotal) {
		this.montoTotal = montoTotal;
	}


	public void setDevolucion(Devoluciones devolucion) {
		this.devolucion = devolucion;
	}

	

}
