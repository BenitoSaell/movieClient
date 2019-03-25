package com.benitosaell.client.controller;

import java.util.Date;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import com.benitosaell.client.model.Comment;

import com.benitosaell.client.model.User;
import static com.benitosaell.client.constant.Urls.URL_ADMIN;
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
		if(sessionMain.getAttribute("userToken")==null) {
			return "redirect:/";
		}
		String token = (String) sessionMain.getAttribute("userToken");
		HttpHeaders headers = new HttpHeaders();
		
		headers.add("Authorization",token);
        HttpEntity<String> entity = new HttpEntity<String>(headers);
		
		ResponseEntity<User> response=restTemplate.exchange(URL_ADMIN+"/user",HttpMethod.GET,entity, User.class);
		sessionMain.setAttribute("userAdmin", response.getBody());
		
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
		String token = (String) sessionMain.getAttribute("userToken");
		HttpHeaders headers = new HttpHeaders();
		
		headers.add("Authorization",token);
        HttpEntity<String> entity = new HttpEntity<String>(headers);
		
		restTemplate.exchange(URL_ADMIN+"/salir",HttpMethod.GET,entity, boolean.class);
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
		User user= (User) sessionMain.getAttribute("userAdmin");
		comment.setEmail(user.getUsername());
		comment.setDate(new Date());
		String token = (String) sessionMain.getAttribute("userToken");
		HttpHeaders headers = new HttpHeaders();
		
		headers.add("Authorization",token);
        HttpEntity<Comment> entity = new HttpEntity<Comment>(comment,headers);
		
		restTemplate.postForEntity("http://localhost:3000/api/comentarios/comentario", entity, Comment.class);
		return "redirect:/pelicula/"+comment.getMovie().getId();
	}

}
