package com.sts.comercio.controlador;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sts.comercio.modelo.Producto;
import com.sts.comercio.modelo.Usuario;
import com.sts.comercio.servicio.ProductoServicio;

@Controller
@RequestMapping("/productos")
public class ProductoControlador {

	private final Logger oLogger = LoggerFactory.getLogger(ProductoControlador.class);
	
	@Autowired
	private ProductoServicio oProduServicio;

	@GetMapping("")
	public String VerProductos(Model oModelo) {

		oModelo.addAttribute("productos_all", oProduServicio.VerTodo());
		return "producto/show";

	}

	// Bloque para realizar la creacion del producto
	// Se llama el form para hacer la creacion
	@GetMapping("/crear")
	public String CrearProductos() {

		return "producto/create";

	}

	@PostMapping("/guardar")
	public String Guardar(Producto oProducto) {
		oLogger.info("Objeto producto enviado desde el form: {}", oProducto);
		Usuario oUsuario=new Usuario(1, "", "", "", "", "", "", "", "");
		oProducto.setUsuario(oUsuario);
		oProduServicio.GuardarProducto(oProducto);
		return "redirect:/productos";
	}

	// Bloque para realizar la creacion del producto
	// Se llama el form para hacer la creacion
	@GetMapping("/editar")
	public String EditarProductos() {

		return "producto/edit";

	}

}
