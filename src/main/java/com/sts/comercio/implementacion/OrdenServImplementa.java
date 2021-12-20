package com.sts.comercio.implementacion;

import java.util.ArrayList;
import java.util.List;

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

	@Override
	public List<Orden> MostrarOrdenes() {

		return oRepoOrden.MostrarOrdenes();

	}

	@Override
	public String ConseOrden() {

		int numero = 0;
		String numConca = "";

		List<Orden> lstOrdenes = MostrarOrdenes();
		List<Integer> lstNumeros = new ArrayList<Integer>();

		// Agrego los numeros de todas las ordenes generadas en lstNumeros
		lstOrdenes.stream().forEach(x -> lstNumeros.add(Integer.valueOf(x.getNumero())));

		if (lstOrdenes.isEmpty()) {
			numero = 1;
		} else {
			// Selecciono el numero mayor y se incrementa en +1
			numero = lstNumeros.stream().max(Integer::compare).get();
			numero++;
		}

		if (numero < 10) {

			numConca = "00000" + String.valueOf(numero);

		} else if (numero >= 10 && numero < 100) {

			numConca = "0000" + String.valueOf(numero);

		} else if (numero >= 100 && numero < 1000) {

			numConca = "000" + String.valueOf(numero);

		}

		return numConca;

	}

}
