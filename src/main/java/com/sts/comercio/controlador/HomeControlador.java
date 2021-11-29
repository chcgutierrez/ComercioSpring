package com.sts.comercio.controlador;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sts.comercio.servicio.ProductoServicio;

@Controller
@RequestMapping("/")
public class HomeControlador {
	
	private final Logger oLogger = LoggerFactory.getLogger(HomeControlador.class);

	@Autowired
	private ProductoServicio oProduService;

	@GetMapping("")
	public String Home(Model oModel) {

		oModel.addAttribute("productos_home", oProduService.MostrarTodo());

		return "usuario/home";

	}
	
	@GetMapping("producto_home/{idProducto}")
	public String HomeProducto(@PathVariable Integer idProducto) {

		oLogger.info("ID producto enviado como parámetro: {}", idProducto);

		return "usuario/producto_home";

	}

}
