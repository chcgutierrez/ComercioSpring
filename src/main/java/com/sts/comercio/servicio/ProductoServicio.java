package com.sts.comercio.servicio;

import java.util.List;
import java.util.Optional;

import com.sts.comercio.modelo.Producto;

public interface ProductoServicio {
	
	public List<Producto> VerTodo();

	public Optional<Producto> BuscarProduId(Integer idProducto);

	public Producto GuardarProducto(Producto oProducto);

	public void ActualizarProducto(Producto oProducto);

	public void EliminarProducto(Integer idProducto);

}
