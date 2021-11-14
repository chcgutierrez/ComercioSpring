package com.sts.comercio.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idUsuario;
	
	private String nombre;
	private String apellido;
	private String email;
	private String direccion;
	private String telefono;
	private String tipo;
	private String password;
	private String username;
	
}
