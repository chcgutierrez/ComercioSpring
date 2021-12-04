package com.sts.comercio.implementacion;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sts.comercio.modelo.Usuario;
import com.sts.comercio.repositorio.UsuarioRepositorio;
import com.sts.comercio.servicio.UsuarioServicio;

@Service
public class UsuarioServImplementa implements UsuarioServicio {
	
	@Autowired
	private UsuarioRepositorio oUsuarioRepo;

	@Override
	public Optional<Usuario> BuscarUsuario(Integer idUsuario) {
		
		return oUsuarioRepo.BuscarUsuario(idUsuario);
		
	}

}
