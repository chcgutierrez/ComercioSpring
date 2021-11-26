package com.sts.comercio.servicio;

import java.util.List;
import java.util.Optional;

import com.sts.comercio.modelo.Producto;

public interface ProductoServicio {

	public List<Producto> MostrarTodo();

	public Optional<Producto> BuscarProducto(Integer idProducto);

	public int InsertProducto(Producto oProducto);

	public int UpdateProducto(Producto oProducto);

	public int DeleteProducto(Integer idProducto);

}
