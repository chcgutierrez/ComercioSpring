package com.sts.comercio.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sts.comercio.modelo.Producto;
import com.sts.comercio.servicio.ProductoServicio;

@Controller
@RequestMapping("/administrador")
public class AdminControlador {

	@Autowired
	private ProductoServicio oProduService;
	
	@GetMapping("")
	public String Home(Model oModel) {
		
		List<Producto> lstProductos=oProduService.VerTodo();
		oModel.addAttribute("productos_home", lstProductos);
		
		return "administrador/home";
		
	}

}
