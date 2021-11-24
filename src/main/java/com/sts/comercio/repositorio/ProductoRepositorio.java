package com.sts.comercio.repositorio;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sts.comercio.modelo.Producto;

@Repository
public interface ProductoRepositorio extends JpaRepository<Producto, Integer> {

	// Se crean las consultas llamando los SP en BD

	@Query(value = "{CALL SP_MOSTRAR_PRODUCTOS()}", nativeQuery = true)
	List<Producto> MostrarProductos();

	@Query(value = "{CALL SP_MOSTRAR_PRODUCTO_ID(:idProducto)}", nativeQuery = true)
	Optional<Producto> BuscarProducto(@Param("idProducto") int idProducto);

	@Transactional
	@Modifying
	@Query(value = "{CALL SP_INSERT_PRODUCTO(:cantidad, :descripcion, :imagen, :nombre, :valor, :idUsuario)}", nativeQuery = true)
	void InsertProducto(@Param("cantidad") int cantidad, @Param("descripcion") String descripcion,
			@Param("imagen") String imagen, @Param("nombre") String nombre, @Param("valor") double valor,
			@Param("idUsuario") int idUsuario);

}
