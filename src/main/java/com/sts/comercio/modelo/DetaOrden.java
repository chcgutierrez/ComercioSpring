package com.sts.comercio.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	
}
