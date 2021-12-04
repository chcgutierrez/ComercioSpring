package com.sts.comercio.servicio;

import java.util.Optional;

import com.sts.comercio.modelo.Usuario;

public interface UsuarioServicio {
	
	public Optional<Usuario> BuscarUsuario(Integer idUsuario);

}
