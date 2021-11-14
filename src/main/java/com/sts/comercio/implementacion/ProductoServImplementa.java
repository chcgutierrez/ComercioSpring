package com.sts.comercio.implementacion;

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
	public Optional<Producto> BuscarProduId(Integer idProducto) {

		return oProduRepo.findById(idProducto);

	}

	@Override
	public Producto GuardarProducto(Producto oProducto) {
		
		//Pasa el Id en Null para crear
		return oProduRepo.save(oProducto);

	}

	@Override
	public void ActualizarProducto(Producto oProducto) {

		//Pasa el Id con valor para actualizar
		oProduRepo.save(oProducto);

	}

	@Override
	public void EliminarProducto(Integer idProducto) {

		oProduRepo.deleteById(idProducto);

	}

}
