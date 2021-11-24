package com.sts.comercio.servicio;

import java.util.List;
import java.util.Optional;

import com.sts.comercio.modelo.Producto;

public interface ProductoServicio {

	public Producto GuardarProducto(Producto oProducto);

	public void ActualizarProducto(Producto oProducto);

	public void EliminarProducto(Integer idProducto);
	
	//************************
	
	public List<Producto> MostrarTodo();
	public Optional<Producto> BuscarProducto(Integer idProducto);
	public void InsertProducto(Producto oProducto);

}
