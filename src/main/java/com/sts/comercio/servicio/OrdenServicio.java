package com.sts.comercio.servicio;

import java.util.List;

import com.sts.comercio.modelo.Orden;

public interface OrdenServicio {
	
	public List<Orden> MostrarOrdenes();
	
	public int GuardarOrden (Orden oEncOrden);
	
	public String ConseOrden();

}
