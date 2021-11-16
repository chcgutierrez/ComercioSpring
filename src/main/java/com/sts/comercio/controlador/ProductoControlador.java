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
		Usuario oUsuario = new Usuario(1, "", "", "", "", "", "", "", "");
		oProducto.setUsuario(oUsuario);
		oProduServicio.GuardarProducto(oProducto);
		return "redirect:/productos";
	}

	// Bloque para realizar la modificacion del producto
	// Se llama el form para hacer la edicion pasando los datos del producto que se
	// va a modificar
	@GetMapping("/editar/{idProducto}")
	public String EditarProductos(@PathVariable Integer idProducto, Model oModelo) {

		Producto oProducto = new Producto();
		Optional<Producto> optProducto = oProduServicio.BuscarProduId(idProducto);
		oProducto = optProducto.get();
		oModelo.addAttribute("producto_edit", oProducto);
		oLogger.info("Objeto recuperado desde el form: {}", oProducto);
		return "producto/edit";

	}

	@PostMapping("/modificar")
	public String Modificar(Producto oProducto) {

		oProduServicio.ActualizarProducto(oProducto);
		return "redirect:/productos";
		
	}
	
	// Bloque para realizar la modificacion del producto
	@GetMapping("/eliminar/{idProducto}")
	public String Eliminar(@PathVariable Integer idProducto) {
		
		oProduServicio.EliminarProducto(idProducto);
		return "redirect:/productos";
		
	}

}
