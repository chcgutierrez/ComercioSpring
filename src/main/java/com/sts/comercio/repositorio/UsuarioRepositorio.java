package com.sts.comercio.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sts.comercio.modelo.Usuario;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer> {

	// Se crean las consultas llamando los SP en BD

	@Query(value = "{CALL SP_BUSCAR_USUARIO_ID(:idUsuario)}", nativeQuery = true)
	Optional<Usuario> BuscarUsuario(@Param("idUsuario") int idUsuario);

}
