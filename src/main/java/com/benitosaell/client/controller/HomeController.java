package com.benitosaell.client.controller;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.validation.Valid;

import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import com.benitosaell.client.model.Comment;
import com.benitosaell.client.model.Movie;
import com.benitosaell.client.model.Profile;
import com.benitosaell.client.model.User;

@Controller
public class HomeController {
	RestTemplate restTemplate = new RestTemplate();
	@GetMapping("/")
	private String getHome(Model model) {
		
		//final Movie responseEntity =  restTemplate.getForObject("http://localhost:3000/api/peliculas/peliculas2", Movie.class);
		/*/ResponseEntity<String> response
		  = restTemplate.getForEntity("http://localhost:3000/api/peliculas/peliculas2", String.class);
		System.out.println("Respuesta  "+ response.getBody());*/
		
         
		List<Movie> movies= new LinkedList<>();
		ResponseEntity<Movie[]> response
		  = restTemplate.getForEntity("http://localhost:3000/api/peliculas/peliculas2", Movie[].class);
		for(Movie movie: response.getBody()) {
			movies.add(movie);
			
		}	
		model.addAttribute("movies", movies);
		
		return "Home";
	}
	
	@GetMapping("/pelicula/{id}")
	private String getDetail(@PathVariable("id") int id, Model model) {
		Movie movie= new Movie();
		ResponseEntity<Movie> response
		  = restTemplate.getForEntity("http://localhost:3000/api/peliculas/ver/"+id, Movie.class);
		System.out.println("Respuesta  "+ response.getBody());
		movie=response.getBody();
		
		ResponseEntity<Comment[]> commetsResponse
		  = restTemplate.getForEntity("http://localhost:3000/api/comentarios/comentarios/"+id, Comment[].class);
		List<Comment> comments= new LinkedList<>();
		
		for(Comment comment:commetsResponse.getBody()) {
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
		User user= new User();
		model.addAttribute("user", user);
		return "Registry";
	}
	
	@PostMapping("/registrar")
	private String insertUser(@Valid User user, BindingResult bindingResult) {
		System.out.println("UsuarioClient: "+user);
		if (bindingResult.hasErrors()) {
            return "Registry";
        }
		ResponseEntity<Profile> response
		  = restTemplate.postForEntity("http://localhost:3000/api/usuarios/crear", user,Profile.class);
		System.out.println("Respuesta  "+ response.getBody());
		return "redirect:/";
	}

}
