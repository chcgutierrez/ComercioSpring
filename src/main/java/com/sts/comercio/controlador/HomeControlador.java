package com.sts.comercio.controlador;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sts.comercio.modelo.Producto;
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
	public String HomeProducto(@PathVariable Integer idProducto, Model oModelo) {

		Producto oProducto = new Producto();
		Optional<Producto> optProducto = oProduService.BuscarProducto(idProducto);
		oProducto = optProducto.get();
		oModelo.addAttribute("producto_home", oProducto);
		oLogger.info("Objeto recuperado desde el form: {}", oProducto);
		
		return "usuario/producto_home";

	}
	
	@PostMapping("/carrito")
	public String AgregarCarrito(Model oModel) {

		oModel.addAttribute("productos_home", oProduService.MostrarTodo());

		return "usuario/carrito";

	}

}
