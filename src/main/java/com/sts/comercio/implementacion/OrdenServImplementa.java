package com.sts.comercio.implementacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sts.comercio.modelo.Orden;
import com.sts.comercio.repositorio.OrdenRepositorio;
import com.sts.comercio.servicio.OrdenServicio;

@Service
public class OrdenServImplementa implements OrdenServicio {

	@Autowired
	private OrdenRepositorio oRepoOrden;

	@Override
	public int GuardarOrden(Orden oEncOrden) {
		
		return oRepoOrden.GuardarEncOrden(oEncOrden.getFechaCrea(), oEncOrden.getFechaRecibe(), oEncOrden.getNumero(),
				oEncOrden.getTotal(), oEncOrden.getUsuario().getIdUsuario());
		
	}

}
