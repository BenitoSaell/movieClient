package com.benitosaell.client.controller;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
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
import com.benitosaell.client.model.UserLogin;
import static com.benitosaell.client.constant.Urls.URL_PUBLIC;
import static com.benitosaell.client.constant.Urls.URL_LOGIN;
@Controller
public class HomeController {
	RestTemplate restTemplate = new RestTemplate();
	
	/*
	 * Carga la página principal.
	 * 
	 * @param model: Para cargar la lista de peliculas al la página,
	 * @return : La dirección de vista de inicio. 
	*/
	@GetMapping("/")
	private String getHome(Model model) {
		List<Movie> movies = new LinkedList<>();
		ResponseEntity<Movie[]> response = restTemplate.getForEntity(URL_PUBLIC+"/peliculas",
				Movie[].class);
		for (Movie movie : response.getBody()) {
			movies.add(movie);

		}
		model.addAttribute("movies", movies);
		return "Home";
	}

	/*
	 * Cargar la página donde se muestra la información de una pelicula
	 * 
	 * @param model: Para cargar la pelicula selecciona y sus comentarios
	 * @param id: De la pelicula para obtener su información y sus comentarios.
	 * @return: La vista para la información de la pelicula. 
	*/
	@GetMapping("/pelicula/{id}")
	private String getDetail(@PathVariable("id") int id, Model model) {
		Movie movie = new Movie();
		ResponseEntity<Movie> response = restTemplate.getForEntity(URL_PUBLIC+"/ver/" + id,
				Movie.class);
		System.out.println("Respuesta  " + response.getBody());
		movie = response.getBody();

		ResponseEntity<Comment[]> commetsResponse = restTemplate
				.getForEntity(URL_PUBLIC+"/comentarios/" + id, Comment[].class);
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

	/*
	 * Cargar la vista de autentificación.
	 * 
	 * @param sessionMain: para restringir el acceso a la página cuando un usuario este autentificado.
	 * @return: La vista de registro. 
	*/
	@GetMapping("/entrar")
	private String login(HttpSession sessionMain) {
		if(sessionMain.getAttribute("userAdmin")!=null) {
			return "redirect:/admin/index";
		}
		return "Login";
	}
	
	/*
	 * Envia la información a la api para verificar que exista el usuario.
	 * 
	 * @param user: usuario y contraseña para  autentificarse.
	 * @param sessionMain: para restringir el acceso a la página cuando un usuario este autentificado.
	 * @param attributes: Añadir mensajes.
	 * @return: La dirección de la vista en una cadena o redirecciona a la vista de bienvenida. 
	*/
	@PostMapping("/login")
	private String login2(@Valid UserLogin user, HttpSession sessionMain,RedirectAttributes attributes) {
		try {
			UserLogin userLogin = user;
			ResponseEntity<ResponseEntity> response = restTemplate.postForEntity(URL_LOGIN,
					userLogin, ResponseEntity.class);
			HttpHeaders header = response.getHeaders();
			if(header.get("Authorization")!=null) {
				sessionMain.setAttribute("userToken", header.get("Authorization").get(0));
				return "redirect:/admin/index";
			}
			attributes.addFlashAttribute("message","Acceso denegado");
			return "/Login";
		} catch (Exception ex) {
			System.out.println("Error: " +ex);
			attributes.addFlashAttribute("message","Acceso denegado");
			return "/Login";
		}
	}
	
	/*
	 * Cargar la vista de registro.
	 * 
	 * @param sessionMain: Restringir el acceso a la página cuando un usuario este autentificado.
	 * @return: La vista de registro. 
	*/
	@GetMapping("/registro")
	private String registry(HttpSession sessionMain) {
		if(sessionMain.getAttribute("userAdmin")!=null) {
			return "redirect:/admin/index";
		}
		return "Registry";
	}
	
	/*
	 * Envia la información a la api para registrar en la base de datos un nuevo usuario.
	 * 
	 * @param user: La información del usuario para registrarla.
	 * @param bindingResult: Guarda los errores.
	 * @param attributes: Guardar los mensajes a mostrar.
	 * @return: La vista de registro. 
	*/

	@PostMapping("/registrar")
	private String insertUser(@Valid User user, BindingResult bindingResult, RedirectAttributes attributes) {
		if (bindingResult.hasErrors()) {
			return "Registry";
		}
		restTemplate.postForEntity(URL_PUBLIC+"/crear", user,
				User.class);
		attributes.addFlashAttribute("message", "Usuario registrado");
		return "redirect:/entrar";
	}

	

}
