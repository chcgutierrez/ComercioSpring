package com.sts.comercio.servicio;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CargarFileServicio {

	// Ubicacion de los archivos dentro del proyecto
	private String folder = "imagenes//";

	public String GuardarImagen(MultipartFile oFile) throws IOException {

		if (!oFile.isEmpty()) {

			// Se guarda la imagen seleccionada en el proyecto
			byte[] arrBytes = oFile.getBytes();
			Path oRuta = Paths.get(folder + oFile.getOriginalFilename());
			Files.write(oRuta, arrBytes);
			return oFile.getOriginalFilename();

		} else {

			// Imagen por defecto si no se carga una imagen
			return "default.jpg";

		}

	}

	public void BorrarImagen(String imgNombre) {

		String rutaImg = folder;
		File oFileImg = new File(rutaImg + imgNombre);
		oFileImg.delete();

	}

}
