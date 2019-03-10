package com.benitosaell.client.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
	@GetMapping(value="/index")
	public String showMainAdmin(/*Authentication authentication*/) {
		//System.out.println("Username: "+ authentication.getName());
		//for(GrantedAuthority rol: authentication.getAuthorities()) {
			//System.out.println("Rol: "+ rol.getAuthority());
		//}
		return "admin";
	}
	
	@GetMapping(value = "/logout")
	public String logout(HttpServletRequest request) {
		//SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
		//logoutHandler.logout(request, null, null);
		return "redirect:/entrar";
	}
	
	@PostMapping(value = "/comentario")
	public String insertComment(@ModelAttribute Comment comment) {
		//SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
		//logoutHandler.logout(request, null, null);
		comment.setEmail("benito-misael@outlook.com");
		comment.setDate(new Date());
		System.out.println("Nuevo comentario: " + comment);
		
		ResponseEntity<Comment> response
		  = restTemplate.postForEntity("http://localhost:3000/api/comentarios/comentario", comment, Comment.class);
		System.out.println("Respuesta  "+ response.getBody());
		return "redirect:/pelicula/"+comment.getMovie().getId();
	}

}
