package com.sts.comercio.controlador;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.sts.comercio.modelo.Producto;
import com.sts.comercio.modelo.Usuario;
import com.sts.comercio.servicio.CargarFileServicio;
import com.sts.comercio.servicio.ProductoServicio;

@Controller
@RequestMapping("/productos")
public class ProductoControlador {

	private final Logger oLogger = LoggerFactory.getLogger(ProductoControlador.class);

	@Autowired
	private ProductoServicio oProduServicio;

	@Autowired
	private CargarFileServicio oFileServicio;
	
	//Imagen por defecto
	private String imgDefault="default.jpg";

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
	public String Guardar(Producto oProducto, @RequestParam("imgproducto") MultipartFile imgFile) throws IOException {

		oLogger.info("Objeto producto enviado desde el form: {}", oProducto);
		Usuario oUsuario = new Usuario(1, "", "", "", "", "", "", "", "");
		oProducto.setUsuario(oUsuario);

		// Guardar la imagen producto nuevo
		if (oProducto.getIdProducto() == null) {

			String imgNombre = oFileServicio.GuardarImagen(imgFile);
			oProducto.setImagen(imgNombre);

		}

		oProduServicio.GuardarProducto(oProducto);
		return "redirect:/productos";
	}

	// Bloque para realizar la modificacion del producto
	// Se llama el form para hacer la edicion pasando los datos del producto
	@GetMapping("/editar/{idProducto}")
	public String EditarProductos(@PathVariable Integer idProducto, Model oModelo) {

		Producto oProducto = new Producto();
		Optional<Producto> optProducto = oProduServicio.BuscarProduId(idProducto);
		oProducto = optProducto.get();
		oModelo.addAttribute("producto_edit", oProducto);
		oLogger.info("Objeto recuperado desde el form: {}", oProducto);
		return "producto/edit";

	}

	// Bloque para Modificar los datos del producto en BD
	@PostMapping("/modificar")
	public String Modificar(Producto oProducto, @RequestParam("imgproducto") MultipartFile imgFile) throws IOException {

		Producto oProduAux = new Producto();

		// Modifico el producto dejando la misma imagen
		if (imgFile.isEmpty()) {

			oProduAux = oProduServicio.BuscarProduId(oProducto.getIdProducto()).get();
			oProducto.setImagen(oProduAux.getImagen());

		} else {

			// Modifico el producto y cambio la imagen
			oProduAux = oProduServicio.BuscarProduId(oProducto.getIdProducto()).get();

			// Elimina la imagen del Servidor si no es la imagen default
			if (!oProduAux.getImagen().equals(imgDefault)) {

				oFileServicio.BorrarImagen(oProduAux.getImagen());

			}

			String imgNombre = oFileServicio.GuardarImagen(imgFile);
			oProducto.setImagen(imgNombre);

		}

		oProduServicio.ActualizarProducto(oProducto);
		return "redirect:/productos";

	}

	// Bloque para Eliminar los datos del producto en BD
	@GetMapping("/eliminar/{idProducto}")
	public String Eliminar(@PathVariable Integer idProducto) {

		Producto oProduAux = new Producto();
		oProduAux = oProduServicio.BuscarProduId(idProducto).get();

		// Elimina la imagen del Servidor si no es la imagen default
		if (!oProduAux.getImagen().equals(imgDefault)) {

			oFileServicio.BorrarImagen(oProduAux.getImagen());

		}

		oProduServicio.EliminarProducto(idProducto);
		return "redirect:/productos";

	}

}
