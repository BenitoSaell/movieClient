package com.benitosaell.client.controller;

import java.security.Principal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.catalina.realm.GenericPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
/*import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;*/
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.benitosaell.client.model.Comment;
import com.benitosaell.client.model.Movie;
import com.benitosaell.client.model.User;

@Controller
public class HomeController {
	RestTemplate restTemplate = new RestTemplate();
	
	
	//private BCryptPasswordEncoder encoder;

	@GetMapping("/")
	private String getHome(Model model) {
		List<Movie> movies = new LinkedList<>();
		ResponseEntity<Movie[]> response = restTemplate.getForEntity("http://localhost:3000/api/peliculas/peliculas2",
				Movie[].class);
		for (Movie movie : response.getBody()) {
			movies.add(movie);

		}
		model.addAttribute("movies", movies);

		return "Home";
	}

	@GetMapping("/pelicula/{id}")
	private String getDetail(@PathVariable("id") int id, Model model) {
		Movie movie = new Movie();
		ResponseEntity<Movie> response = restTemplate.getForEntity("http://localhost:3000/api/peliculas/ver/" + id,
				Movie.class);
		System.out.println("Respuesta  " + response.getBody());
		movie = response.getBody();

		ResponseEntity<Comment[]> commetsResponse = restTemplate
				.getForEntity("http://localhost:3000/api/comentarios/comentarios/" + id, Comment[].class);
		List<Comment> comments = new LinkedList<>();

		for (Comment comment : commetsResponse.getBody()) {
			comments.add(comment);
		}

		movie.setComments(comments);

		Comment comment = new Comment();
		comment.setMovie(movie);
		comment.setDate(new Date());

		model.addAttribute("commentNew", comment);
		model.addAttribute("movie", movie);
		return "Detail";
	}

	@GetMapping("/entrar")
	private String login(Model model) {
		return "Login";
	}

	@GetMapping("/registro")
	private String registry(Model model) {
		return "Registry";
	}

	@PostMapping("/registrar")
	private String insertUser(@Valid User user, BindingResult bindingResult, RedirectAttributes attributes) {
		System.out.println("UsuarioClient: "+user);
		if (bindingResult.hasErrors()) {
            return "Registry";
        }
		//String temp = encoder.encode(user.getPassword());
		System.out.println("UsuarioClient2: "+user);
		//user.setPassword(temp);
		ResponseEntity<User> response
		  = restTemplate.postForEntity("http://localhost:3000/api/usuarios/crear", user,User.class);
		System.out.println("Respuesta  "+ response.getBody());
		attributes.addFlashAttribute("message", "Usuario registrado");
		return "redirect:/";
	}
	
	/*@PostMapping("/login")
	private String login2(Model model, Authentication auth) {
		aut
		return "redirect:/admin/index";
	}*/
	
	
}
