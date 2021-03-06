package com.benitosaell.client.util;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public class Util {
	public static String saveImage(MultipartFile multiPart, HttpServletRequest request) {
		// Obtenemos el nombre original del archivo
		String originalName = multiPart.getOriginalFilename();
		System.out.println("pruebautileriaNombre: "+ originalName);
		originalName = originalName.replace(" ", "-");
		String nameEnd = randomAlphaNumeric(8)+originalName;
		// Obtenemos la ruta ABSOLUTA del directorio images
		String rutaFinal = request.getServletContext().getRealPath("/resources/images/");
		try {
		
			File imageFile = new File(rutaFinal + nameEnd);
			// Se guarda fisicamente el archivo en el disco duro
			multiPart.transferTo(imageFile);
			return nameEnd;
		} catch (IOException e) {
			System.out.println("Error " + e.getMessage());
			return null;
		}
	}
	
	public static String randomAlphaNumeric(int count) {
		String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVYXZ0123456789";
		StringBuilder builder = new StringBuilder();
		while(count-- != 0) {
			int character =(int) (Math.random()*CHARACTERS.length());
			builder.append(CHARACTERS.charAt(character));
		}
		return builder.toString();
	}

}
