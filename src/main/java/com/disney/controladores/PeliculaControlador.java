package com.disney.controladores;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.disney.dto.PeliculaDto;
import com.disney.dto.PersonajeDto;
import com.disney.entidades.Genero;
import com.disney.entidades.Pelicula;
import com.disney.entidades.Personaje;
import com.disney.servicios.GeneroServicio;
import com.disney.servicios.PeliculaService;
import com.disney.view.View;
import com.fasterxml.jackson.annotation.JsonView;

@Controller
@RequestMapping("/movies")
public class PeliculaControlador {
	
	@Autowired
	private GeneroServicio generoServicio;
	
	@Autowired
	private PeliculaService peliculaServicio;
	
	@PostMapping
	public ResponseEntity<PeliculaDto> save(
			@RequestBody PeliculaDto pelicula//en el body viene la info
			) {		
		PeliculaDto peliculaGuardada = peliculaServicio.save(pelicula);
		return ResponseEntity.status(HttpStatus.CREATED).body(peliculaGuardada);
	}
	
	@GetMapping
	@JsonView(View.Base.class)
	public ResponseEntity<List<PeliculaDto>> getAll(){
		List<PeliculaDto> peliculas = peliculaServicio.listarPeliculas(); 
		return ResponseEntity.ok().body(peliculas);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id){		
		peliculaServicio.borrarPelicula(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<PeliculaDto> modificar(@PathVariable String id, @RequestBody PeliculaDto pelicula){
		PeliculaDto result = peliculaServicio.modificar(id, pelicula);				
		return ResponseEntity.ok().body(result);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Pelicula> getPeliculaById(@PathVariable String id){
		Pelicula pelicula = peliculaServicio.getPeliculaById(id); 
		return ResponseEntity.ok(pelicula);
	}
	
	@GetMapping("/filter")
	public ResponseEntity<ArrayList<Pelicula>> getDetailsByFilters(
			@RequestParam(required = false, defaultValue = "") String titulo,
			@RequestParam(required = false, defaultValue = "") String generoId,
			@RequestParam(required = false, defaultValue = "ASC") String orden
			){
		ArrayList<Pelicula> listaPelicula = peliculaServicio.listaPeliculasFiltradas(titulo, generoId, orden);
		return ResponseEntity.ok(listaPelicula);
	}
}
