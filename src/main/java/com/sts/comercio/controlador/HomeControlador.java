package com.sts.comercio.controlador;

import java.util.ArrayList;
import java.util.List;
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

import com.sts.comercio.modelo.DetaOrden;
import com.sts.comercio.modelo.Orden;
import com.sts.comercio.modelo.Producto;
import com.sts.comercio.servicio.ProductoServicio;

@Controller
@RequestMapping("/")
public class HomeControlador {

	private final Logger oLogger = LoggerFactory.getLogger(HomeControlador.class);

	// Recoge el detalle de la orden
	List<DetaOrden> mDetalleOrd = new ArrayList<DetaOrden>();

	// Recoge el encabezado de la orden
	Orden mCabeOrden = new Orden();

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
	public String AgregarCarrito(@RequestParam Integer idProducto, @RequestParam Integer cantproducto, Model oModelo) {

		DetaOrden detOrden = new DetaOrden();
		Producto detProducto = new Producto();
		Optional<Producto> optProducto = oProduService.BuscarProducto(idProducto);
		double sumaTotal = 0;
		double totalProdu = 0;

		detProducto = optProducto.get();

		totalProdu = (detProducto.getValor() * cantproducto);

		detOrden.setNombre(detProducto.getNombre());
		detOrden.setPrecio(detProducto.getValor());
		detOrden.setCantidad(cantproducto);
		detOrden.setTotal(totalProdu);
		detOrden.setProducto(detProducto);

		mDetalleOrd.add(detOrden);
		
		sumaTotal = mDetalleOrd.stream().mapToDouble(x -> x.getTotal()).sum();
		mCabeOrden.setTotal(sumaTotal);
		
		oModelo.addAttribute("det_ord_carro", mDetalleOrd);
		oModelo.addAttribute("orden_carro", mCabeOrden);
		
		oLogger.info("Detalle Orden: {}", mDetalleOrd);
		oLogger.info("Cabecera Orden: {}", mCabeOrden);

		return "usuario/carrito";

	}

}
