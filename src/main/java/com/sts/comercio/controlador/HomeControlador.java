package com.sts.comercio.controlador;

import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sts.comercio.modelo.DetaOrden;
import com.sts.comercio.modelo.Orden;
import com.sts.comercio.modelo.Producto;
import com.sts.comercio.modelo.Usuario;
import com.sts.comercio.servicio.DetaOrdenServicio;
import com.sts.comercio.servicio.OrdenServicio;
import com.sts.comercio.servicio.ProductoServicio;
import com.sts.comercio.servicio.UsuarioServicio;

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

	@Autowired
	private UsuarioServicio oUsuarioService;

	@Autowired
	private OrdenServicio oOrdenService;

	@Autowired
	private DetaOrdenServicio oDetaService;

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

	// Agregar productos en el carrito de compras
	@PostMapping("agregar/carrito")
	public String AgregarCarrito(@RequestParam Integer idProducto, @RequestParam Integer cantproducto, Model oModelo,
			RedirectAttributes oAtributoMsj) {

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

		// Validar que no se agregue 2 veces el mismo producto
		Integer idProduAux = detProducto.getIdProducto();
		boolean ingresado = mDetalleOrd.stream().anyMatch(p -> p.getProducto().getIdProducto() == idProduAux);

		if (!ingresado) {

			mDetalleOrd.add(detOrden);

		} else {

			oAtributoMsj.addFlashAttribute("info", "Este producto ya está agregado.");
			return "redirect:/producto_home/" + idProduAux;

		}

		sumaTotal = mDetalleOrd.stream().mapToDouble(x -> x.getTotal()).sum();
		mCabeOrden.setTotal(sumaTotal);

		oModelo.addAttribute("det_ord_carro", mDetalleOrd);
		oModelo.addAttribute("orden_carro", mCabeOrden);

		oLogger.info("Detalle Orden: {}", mDetalleOrd);
		oLogger.info("Cabecera Orden: {}", mCabeOrden);

		return "usuario/carrito";

	}

	// Retirar los productos del carrito de compras

	/**
	 * @param idProducto
	 * @param oModelo
	 * @return Creo un nuevo detalle a partir de lo que está en la lista y lo
	 *         reasigno con los productos restantes y actualizo la sumatoria
	 */
	@GetMapping("quitar/carrito/{idProducto}")
	public String RetirarCarrito(@PathVariable Integer idProducto, Model oModelo) {

		List<DetaOrden> auxDetalleOrd = new ArrayList<DetaOrden>();

		for (DetaOrden oDetOrden : mDetalleOrd) {

			if (oDetOrden.getProducto().getIdProducto() != idProducto) {

				auxDetalleOrd.add(oDetOrden);

			}

		}

		mDetalleOrd = auxDetalleOrd;

		double sumaTotal = 0;
		sumaTotal = mDetalleOrd.stream().mapToDouble(x -> x.getTotal()).sum();
		mCabeOrden.setTotal(sumaTotal);

		oModelo.addAttribute("det_ord_carro", mDetalleOrd);
		oModelo.addAttribute("orden_carro", mCabeOrden);

		return "usuario/carrito";
	}

	@GetMapping("/mostrar/carrito")
	public String MostrarCarrito(Model oModelo) {

		oModelo.addAttribute("det_ord_carro", mDetalleOrd);
		oModelo.addAttribute("orden_carro", mCabeOrden);

		return "usuario/carrito";

	}

	@GetMapping("/orden")
	public String ResumenOrden(Model oModelo) {

		Usuario oUsuario = oUsuarioService.BuscarUsuario(1).get();

		oModelo.addAttribute("det_ord_resumen", mDetalleOrd);
		oModelo.addAttribute("orden_resumen", mCabeOrden);
		oModelo.addAttribute("usuario_orden", oUsuario);

		return "usuario/resumenorden";

	}

	// Guarda la Orden con los productos
	@GetMapping("/orden/guardar")
	public String GuardarOrden() {

		Usuario oUsuario = oUsuarioService.BuscarUsuario(1).get();
		Date dteFechaCrea = new Date();

		// Guarda Encabezado
		mCabeOrden.setFechaCrea(dteFechaCrea);
		mCabeOrden.setNumero(oOrdenService.ConseOrden());
		mCabeOrden.setUsuario(oUsuario);

		int idOrden = oOrdenService.GuardarOrden(mCabeOrden);
		oLogger.info("Orden Creada: {}", idOrden);
		mCabeOrden.setIdOrden(idOrden);
		
		oLogger.info("Cabecera Orden Guardar: {}", mCabeOrden);
		
		// Guarda Detalle
		for (DetaOrden dtGuardar : mDetalleOrd) {

			dtGuardar.setOrden(mCabeOrden);

			oLogger.info("Detalle Orden Guardar: {}", dtGuardar);
			oDetaService.GuardarDetaOrden(dtGuardar);

		}

		mCabeOrden = new Orden();
		mDetalleOrd.clear();

		return "redirect:/";

	}

}
