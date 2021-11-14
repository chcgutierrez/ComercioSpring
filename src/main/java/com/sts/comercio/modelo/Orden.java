package com.sts.comercio.modelo;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "enc_orden")
public class Orden {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idOrden;	
	private String numero;
	private Date fechaCrea;
	private Date fechaRecibe;
	private double total;
	
	@ManyToOne
	private Usuario usuario;
	
	@OneToMany(mappedBy = "orden")
	private List<DetaOrden> detalle;

	public Orden() {
		super();
	}

	public Orden(Integer idOrden, String numero, Date fechaCrea, Date fechaRecibe, double total, Usuario usuario,
			List<DetaOrden> detalle) {
		super();
		this.idOrden = idOrden;
		this.numero = numero;
		this.fechaCrea = fechaCrea;
		this.fechaRecibe = fechaRecibe;
		this.total = total;
		this.usuario = usuario;
		this.detalle = detalle;
	}

	public Integer getIdOrden() {
		return idOrden;
	}

	public void setIdOrden(Integer idOrden) {
		this.idOrden = idOrden;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Date getFechaCrea() {
		return fechaCrea;
	}

	public void setFechaCrea(Date fechaCrea) {
		this.fechaCrea = fechaCrea;
	}

	public Date getFechaRecibe() {
		return fechaRecibe;
	}

	public void setFechaRecibe(Date fechaRecibe) {
		this.fechaRecibe = fechaRecibe;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<DetaOrden> getDetalle() {
		return detalle;
	}

	public void setDetalle(List<DetaOrden> detalle) {
		this.detalle = detalle;
	}

	@Override
	public String toString() {
		return "Orden [idOrden=" + idOrden + ", numero=" + numero + ", fechaCrea=" + fechaCrea + ", fechaRecibe="
				+ fechaRecibe + ", total=" + total + ", usuario=" + usuario + ", detalle=" + detalle + "]";
	}
	
}
