package com.sts.comercio.implementacion;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sts.comercio.modelo.Producto;
import com.sts.comercio.repositorio.ProductoRepositorio;
import com.sts.comercio.servicio.ProductoServicio;

@Service
public class ProductoServImplementa implements ProductoServicio {

	@Autowired
	private ProductoRepositorio oProduRepo;

	@Override
	public List<Producto> MostrarTodo() {

		return oProduRepo.MostrarProductos();

	}

	@Override
	public Optional<Producto> BuscarProducto(Integer idProducto) {

		return oProduRepo.BuscarProducto(idProducto);

	}

	@Override
	public int InsertProducto(Producto oProducto) {

		return oProduRepo.InsertProducto(oProducto.getCantidad(), oProducto.getDescripcion(), oProducto.getImagen(),
				oProducto.getNombre(), oProducto.getValor(), oProducto.getUsuario().getIdUsuario());

	}

	@Override
	public int UpdateProducto(Producto oProducto) {

		return oProduRepo.UpdateProducto(oProducto.getIdProducto(), oProducto.getCantidad(), oProducto.getDescripcion(),
				oProducto.getImagen(), oProducto.getNombre(), oProducto.getValor(),
				oProducto.getUsuario().getIdUsuario());

	}

	@Override
	public int DeleteProducto(Integer idProducto) {

		return oProduRepo.DeleteProducto(idProducto);

	}

}
