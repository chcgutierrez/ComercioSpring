package com.sts.comercio.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "det_orden")
public class DetaOrden {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idOrdenDeta;
	private String nombre;
	private double cantidad;
	private double precio;
	private double total;
	
	@ManyToOne
	private Orden orden;
	
	@ManyToOne
	private Producto producto;

	public DetaOrden() {
		super();
	}

	public DetaOrden(Integer idOrdenDeta, String nombre, double cantidad, double precio, double total, Orden orden,
			Producto producto) {
		super();
		this.idOrdenDeta = idOrdenDeta;
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.precio = precio;
		this.total = total;
		this.orden = orden;
		this.producto = producto;
	}

	public Integer getIdOrdenDeta() {
		return idOrdenDeta;
	}

	public void setIdOrdenDeta(Integer idOrdenDeta) {
		this.idOrdenDeta = idOrdenDeta;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getCantidad() {
		return cantidad;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Orden getOrden() {
		return orden;
	}

	public void setOrden(Orden orden) {
		this.orden = orden;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	@Override
	public String toString() {
		return "DetaOrden [idOrdenDeta=" + idOrdenDeta + ", nombre=" + nombre + ", cantidad=" + cantidad + ", precio="
				+ precio + ", total=" + total + ", orden=" + orden + ", producto=" + producto + "]";
	}	
	
}
