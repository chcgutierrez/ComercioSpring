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

	// Imagen por defecto
	private String imgDefault = "default.jpg";

	// ***********************************************
	@GetMapping("")
	public String MostrarProductos(Model oModelo) {

		oModelo.addAttribute("productos_all", oProduServicio.MostrarTodo());
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

		Usuario oUsuario = new Usuario(1, "", "", "", "", "", "", "", "");
		oProducto.setUsuario(oUsuario);

		// Guardar la imagen producto nuevo
		if (oProducto.getIdProducto() == null) {

			String imgNombre = oFileServicio.GuardarImagen(imgFile);
			oProducto.setImagen(imgNombre);

		}

		oLogger.info("Objeto producto enviado desde el form: {}", oProducto);
		var insertado = oProduServicio.InsertProducto(oProducto);
		// oProduServicio.GuardarProducto(oProducto);

		oLogger.info("Fila creada desde el form: {}", insertado);

		return "redirect:/productos";

	}

	// Bloque para realizar la modificacion del producto
	// Se llama el form para hacer la edicion pasando los datos del producto
	@GetMapping("/editar/{idProducto}")
	public String EditarProductos(@PathVariable Integer idProducto, Model oModelo) {

		Producto oProducto = new Producto();
		Optional<Producto> optProducto = oProduServicio.BuscarProducto(idProducto);
		oProducto = optProducto.get();
		oModelo.addAttribute("producto_edit", oProducto);
		oLogger.info("Objeto recuperado desde el form: {}", oProducto);
		return "producto/edit";

	}

	// Bloque para Modificar los datos del producto en BD
	@PostMapping("/modificar")
	public String Modificar(Producto oProducto, @RequestParam("imgproducto") MultipartFile imgFile) throws IOException {

		Producto oProduAux = new Producto();
		oProduAux = oProduServicio.BuscarProducto(oProducto.getIdProducto()).get();

		// Modifico el producto dejando la misma imagen
		if (imgFile.isEmpty()) {

			oProducto.setImagen(oProduAux.getImagen());

		} else {

			// Elimina la imagen del Servidor si no es la imagen default
			if (!oProduAux.getImagen().equals(imgDefault)) {

				oFileServicio.BorrarImagen(oProduAux.getImagen());

			}

			String imgNombre = oFileServicio.GuardarImagen(imgFile);
			oProducto.setImagen(imgNombre);

		}

		oProducto.setUsuario(oProduAux.getUsuario());

		oLogger.info("Objeto producto enviado desde el form: {}", oProducto);
		var insertado = oProduServicio.UpdateProducto(oProducto);

		oLogger.info("Fila editada desde el form: {}", insertado);

		return "redirect:/productos";

	}

	// Bloque para Eliminar los datos del producto en BD
	@GetMapping("/eliminar/{idProducto}")
	public String Eliminar(@PathVariable Integer idProducto) {

		Producto oProduAux = new Producto();
		oProduAux = oProduServicio.BuscarProducto(idProducto).get();

		// Elimina la imagen del Servidor si no es la imagen default
		if (!oProduAux.getImagen().equals(imgDefault)) {

			oFileServicio.BorrarImagen(oProduAux.getImagen());

		}

		oLogger.info("ID producto enviado desde el form: {}", idProducto);

		var borrado = oProduServicio.DeleteProducto(idProducto);

		oLogger.info("Fila borrada desde el form: {}", borrado);

		return "redirect:/productos";

	}

}
