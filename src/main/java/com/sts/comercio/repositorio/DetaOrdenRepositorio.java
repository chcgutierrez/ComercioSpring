package com.sts.comercio.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sts.comercio.modelo.DetaOrden;

@Repository
public interface DetaOrdenRepositorio extends JpaRepository<DetaOrden, Integer> {

	@Transactional
	@Modifying
	@Query(value = "{CALL SP_INSERT_DETA_ORDEN(:cantidad, :descripcion, :imagen, :nombre, :valor, :idUsuario)}", nativeQuery = true)
	
}
