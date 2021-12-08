package com.sts.comercio.repositorio;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sts.comercio.modelo.Orden;

@Repository
public interface OrdenRepositorio extends JpaRepository<Orden, Integer> {

	@Transactional
	@Modifying
	@Query(value = "{CALL SP_INSERT_ENC_ORDEN(:fec_crea, :fec_recibe, :num_orden, :total_ord, :id_usuario)}", nativeQuery = true)
	int GuardarEncOrden(@Param("fec_crea") Date fec_crea, @Param("fec_recibe") Date fec_recibe,
			@Param("num_orden") String num_orden, @Param("total_ord") double total_ord,
			@Param("id_usuario") int id_usuario);

}
