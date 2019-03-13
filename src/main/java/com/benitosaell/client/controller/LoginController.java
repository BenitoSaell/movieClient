package com.benitosaell.client.controller;

import java.util.Date;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import com.benitosaell.client.model.Comment;

@Controller
@RequestMapping("/admin")
public class LoginController {
	RestTemplate restTemplate = new RestTemplate();
	
	/*
	 * Envia la vista de bienvenida.
	 * @param sessionMain: Evitar el acceso si no esta autentificado..
	 * @return : Dirección de vista de bienvenida.
	*/
	@GetMapping(value="/index")
	public String showMainAdmin(HttpSession sessionMain) {
		if(sessionMain.getAttribute("userAdmin")==null) {
			return "redirect:/";
		}
		return "Admin";
	}
	
	/*
	 * Elimina la autentificación.
	 * 
	 * @param sessionMain: Evita el acceso si no esta el usuario autentificado.
	 * @return : Dirección de inicio o el de ingreso.
	*/
	@GetMapping(value = "/salir")
	public String logout(HttpSession sessionMain) {
		if(sessionMain.getAttribute("userAdmin")==null) {
			return "redirect:/";
		}
		sessionMain.setAttribute("userAdmin", null);
		return "redirect:/entrar";
	}
	
	/*
	 * Guarda un comentario en la base de datos.
	 * 
	 * @param comment: Información del nuevo comentario.
	 * @param sessionMain: Obtener el usuario para referencia del comentario.
	 * @return: redirecciona a la vista de la pelicula seleccionada.
	*/
	@PostMapping(value = "/comentario")
	public String insertComment(@ModelAttribute Comment comment, HttpSession sessionMain) {
		
		comment.setEmail(sessionMain.getAttribute("userAdmin").toString());
		comment.setDate(new Date());
		
		restTemplate.postForEntity("http://localhost:3000/api/comentarios/comentario", comment, Comment.class);
		return "redirect:/pelicula/"+comment.getMovie().getId();
	}

}
