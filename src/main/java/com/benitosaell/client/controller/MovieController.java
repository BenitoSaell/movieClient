package com.benitosaell.client.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
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
import com.benitosaell.client.util.Util;
import static com.benitosaell.client.constant.Urls.URL_MOVIES;
@Controller
@RequestMapping("/peliculas")
public class MovieController {
	//Variable para las peticiones a la api.
	RestTemplate restTemplate = new RestTemplate();
	
	/*
	 * Interfaz para la lista de peliculas registradas en la base de datos.
	 * 
	 * @param model: Cargar lista de peliculas para la vista.
	 * @param sessionMain: Restringir el acceso.
	 * @return : Vista de la lista de peliculas.
	*/
	@GetMapping("/")
	private String getMovies(Model model, HttpSession sessionMain ) {
		if(sessionMain.getAttribute("userAdmin")==null) {
			return "redirect:/";
		}
		String token = (String) sessionMain.getAttribute("userToken");
		HttpHeaders headers = new HttpHeaders();
		
		headers.add("Authorization",token);
        HttpEntity<String> entity = new HttpEntity<String>(headers);
		ResponseEntity<Movie[]> response = restTemplate.exchange(URL_MOVIES+"/peliculas",
				HttpMethod.GET,entity,Movie[].class);
		System.out.println("Movies: "+ response);
		List<Movie> movies = new LinkedList<>();
		for (Movie movie : response.getBody()) {
			movies.add(movie);
		}

		model.addAttribute("movies", movies);
		return "movies/ListMovies";
	}

	/*
	 * Carga el formulario para crear una nueva pelicula.
	 * 
	 * @param movie: Enlace con la información de la pelicula.
	 * @param sessionMain: Restringir el acceso.
	 * @return : Vista del formulario.
	*/
	@GetMapping("/nueva")
	private String newMovie(@ModelAttribute Movie movie, HttpSession sessionMain) {
		if(sessionMain.getAttribute("userAdmin")==null) {
			return "redirect:/";
		}
		return "movies/Movie";
	}

	/*
	 * Carga el formulario editar pelicula.
	 * 
	 * @param id: De la pelicula a editar.
	 * @param model: Información del la pelicula a editar.
	 * @param sessionMain: Restringir el acceso.
	 * @return : Vista del formulario.
	*/
	@GetMapping("/editar/{id}")
	public String edit(@PathVariable("id") int idMovie, Model model, HttpSession sessionMain) {
		if(sessionMain.getAttribute("userAdmin")==null) {
			return "redirect:/";
		}
		String token = (String) sessionMain.getAttribute("userToken");
		HttpHeaders headers = new HttpHeaders();
		
		headers.add("Authorization",token);
        HttpEntity<String> entity = new HttpEntity<String>(headers);
		Movie movie = new Movie();
		
		ResponseEntity<Movie> response = restTemplate.exchange(URL_MOVIES+"/ver/" + idMovie,HttpMethod.GET
				,entity,Movie.class);
		movie = response.getBody();

		model.addAttribute("movie", movie);
		return "movies/Movie";
	}

	/*
	 * Eliminar pelicula.
	 * 
	 * @param id: Referencia de la pelicula.
	 * @param attribute: Para crear mensajes.
	 * @param sessionMain: Restringir el acceso.
	 * @return : Vista del formulario.
	*/
	@GetMapping("/eliminar/{id}")
	public String delete(@PathVariable("id") int idMovie, HttpSession sessionMain, RedirectAttributes attribute) {
		if(sessionMain.getAttribute("userAdmin")==null) {
			return "redirect:/";
		}
		String token = (String) sessionMain.getAttribute("userToken");
		HttpHeaders headers = new HttpHeaders();
		
		headers.add("Authorization",token);
        HttpEntity<String> entity = new HttpEntity<String>(headers);
		ResponseEntity<String> response = restTemplate.exchange(URL_MOVIES+"/eliminar/"+idMovie,HttpMethod.GET,entity,String.class);
		
		attribute.addFlashAttribute("message", "La pelicula fue eliminada");
		return "redirect:/peliculas/";
	}

	/*
	 * Guardar información de la pelicula.
	 * 
	 * @param movie: Referencia de la pelicula.
	 * @param result: Verificar errores.
	 * @param attribute: Para crear mensajes.
	 * @param multiPart: Imagen.
	 * @param request.
	 * @return : Vista del formulario.
	*/
	@PostMapping(value = "/guardar")
	public String save(@ModelAttribute Movie movie, BindingResult result, RedirectAttributes attributes,
			@RequestParam("fileImage") MultipartFile multiPart, HttpServletRequest request,HttpSession sessionMain) {
		try {
			if (!multiPart.isEmpty()) {
				String nameImage = Util.saveImage(multiPart, request);
				System.out.println("name:   " + nameImage);
				movie.setPoster(nameImage);
			}
			if (result.hasErrors()) {
				return "movies/Movie";
			}
			String token = (String) sessionMain.getAttribute("userToken");
			HttpHeaders headers = new HttpHeaders();
			
			headers.add("Authorization",token);
	        HttpEntity<Movie> entity = new HttpEntity<Movie>(movie,headers);
			restTemplate.postForEntity(URL_MOVIES+"/crear",
			entity,String.class);

			attributes.addFlashAttribute("message", "La pelicula fue guardada correctamente");
			return "redirect:/peliculas/";
			
		}catch(Exception err) {
			System.out.println("Error");
			return "movies/Movie";
		}
		
	}

	/*
	 * Formato a la fecha.
	*/
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

}
