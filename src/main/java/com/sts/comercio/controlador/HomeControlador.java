package com.sts.comercio.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sts.comercio.servicio.ProductoServicio;

@Controller
@RequestMapping("/")
public class HomeControlador {

	@Autowired
	private ProductoServicio oProduService;

	@GetMapping("")
	public String Home(Model oModel) {

		oModel.addAttribute("productos_home", oProduService.VerTodo());

		return "usuario/home";

	}

}
