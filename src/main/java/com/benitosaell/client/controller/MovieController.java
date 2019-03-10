package com.benitosaell.client.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.benitosaell.client.model.Movie;

@Controller
@RequestMapping("/peliculas")
public class MovieController {
	RestTemplate restTemplate = new RestTemplate();

	@GetMapping("/")
	private String getMovies(Model model) {
		List<Movie> movies = new LinkedList<>();
		ResponseEntity<Movie[]> response = restTemplate.getForEntity("http://localhost:3000/api/peliculas/peliculas",
				Movie[].class);
		for (Movie movie : response.getBody()) {
			movies.add(movie);
		}

		model.addAttribute("movies", movies);
		return "movies/ListMovies";
	}

	@GetMapping("/nueva")
	private String newMovie(@ModelAttribute Movie movie, Model model) {
		return "movies/Movie";
	}

	@GetMapping("/editar/{id}")
	public String edit(@PathVariable("id") int idMovie, Model model) {
		Movie movie = new Movie();
		ResponseEntity<Movie> response = restTemplate.getForEntity("http://localhost:3000/api/peliculas/ver/" + idMovie,
				Movie.class);
		System.out.println("Respuesta  " + response.getBody());
		movie = response.getBody();

		model.addAttribute("movie", movie);
		return "movies/Movie";
	}

	@GetMapping("/eliminar/{id}")
	public String delete(@PathVariable("id") int idMovie, RedirectAttributes attribute) {
		restTemplate.delete("http://localhost:3000/api/peliculas/eliminar/" + idMovie);
		attribute.addFlashAttribute("message", "La pelicula fue eliminada");
		return "redirect:/peliculas/";
	}

	@PostMapping(value = "/guardar")
	public String save(@ModelAttribute Movie movie, BindingResult result, RedirectAttributes attributes,
			@RequestParam("fileImage") MultipartFile multiPart, HttpServletRequest request) {
		/*
		 * if (!multiPart.isEmpty()) { String nameImage = utileria.saveImage(multiPart,
		 * request); System.out.println("name:   "+nameImage);
		 * movie.setImage(nameImage); }
		 * 
		 * if (result.hasErrors()) { System.out.println("Existieron errores"); return
		 * "movies/formMovie";
		 * 
		 * } System.out.println(movie); serviceDetails.insert(movie.getDetail());
		 * serviceMovies.insert(movie);
		 */
		System.out.println("IdClient: " + movie);

		ResponseEntity<Movie> response = restTemplate.postForEntity("http://localhost:3000/api/peliculas/crear", movie,
				Movie.class);
		System.out.println("Respuesta  " + response.getBody());
		movie = response.getBody();

		attributes.addFlashAttribute("message", "La pelicula fue insertada correctamente");
		return "redirect:/peliculas/";
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

}
