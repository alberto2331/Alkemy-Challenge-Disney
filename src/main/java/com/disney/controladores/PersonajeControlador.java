package com.disney.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.disney.entidades.Pelicula;
import com.disney.entidades.Personaje;
import com.disney.dto.PersonajeDto;
import com.disney.servicios.PeliculaService;
import com.disney.servicios.PersonajeServicio;
import com.disney.view.View;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping("/characters")

public class PersonajeControlador {

	@Autowired
	private PeliculaService peliculaServicio;
	
	@Autowired
	private PersonajeServicio personajeServicio;
	
	@PostMapping
	public ResponseEntity<PersonajeDto> save(
			@RequestBody PersonajeDto personaje//en el body viene la info
			) {		
		PersonajeDto personajeGuardado = personajeServicio.save(personaje);
		return ResponseEntity.status(HttpStatus.CREATED).body(personajeGuardado);
	}
	
	@GetMapping
	@JsonView(View.Base.class)
	public ResponseEntity<List<PersonajeDto>> getAll(){
		List<PersonajeDto> personajes = personajeServicio.listarPersonajes(); 
		return ResponseEntity.ok().body(personajes);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id){
		personajeServicio.borrarPersonaje(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<PersonajeDto> modificar(@PathVariable String id, @RequestBody PersonajeDto personaje){
		PersonajeDto result = personajeServicio.modificar(id, personaje);				
		return ResponseEntity.ok().body(result);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Personaje> getPersonajeById(@PathVariable String id){
		Personaje personaje = personajeServicio.getPersonajeById(id); 
		return ResponseEntity.ok(personaje);
	}
	
	@GetMapping("/filter")
	public ResponseEntity<List<Personaje>> getDetailsByFilters(
			@RequestParam(required = false, defaultValue = "") String nombre,
			@RequestParam(required = false, defaultValue = "-1") Integer edad,
			@RequestParam(required = false, defaultValue = "") String peliculaId
			){
		List<Personaje> listaPersonaje = personajeServicio.listaPersonajeFiltrados(nombre, edad, peliculaId);
		return ResponseEntity.ok(listaPersonaje);
	}
}
