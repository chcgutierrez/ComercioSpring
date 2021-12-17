package com.sts.comercio.repositorio;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sts.comercio.modelo.DetaOrden;

@Repository
public interface DetaOrdenRepositorio extends JpaRepository<DetaOrden, Integer> {

	@Transactional
	@Modifying
	@Query(value = "{CALL SP_INSERT_DETA_ORDEN(:cantidad, :nombre, :precio, :total, :idOrden, :idProducto)}", nativeQuery = true)
	int GuardarDetOrden(@Param("cantidad") double cantidad, @Param("nombre") String nombre,
			@Param("precio") double precio, @Param("total") double total, @Param("idOrden") int idOrden,
			@Param("idProducto") int idProducto);
}
