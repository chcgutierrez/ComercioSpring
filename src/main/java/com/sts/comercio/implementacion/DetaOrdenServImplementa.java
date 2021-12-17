package com.sts.comercio.implementacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sts.comercio.modelo.DetaOrden;
import com.sts.comercio.repositorio.DetaOrdenRepositorio;
import com.sts.comercio.servicio.DetaOrdenServicio;

@Service
public class DetaOrdenServImplementa implements DetaOrdenServicio {

	@Autowired
	private DetaOrdenRepositorio oRepoDetaOrden;

	@Override
	public int GuardarDetaOrden(DetaOrden oDetOrden) {

		return oRepoDetaOrden.GuardarDetOrden(oDetOrden.getCantidad(), oDetOrden.getNombre(), oDetOrden.getPrecio(),
				oDetOrden.getTotal(), oDetOrden.getOrden().getIdOrden(), oDetOrden.getProducto().getIdProducto());

	}

}
