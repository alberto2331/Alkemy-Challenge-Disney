package com.disney.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.disney.dto.PersonajeDto;
import com.disney.entidades.Personaje;
import com.disney.repositorios.PersonajeRepositorio;
import com.disney.servicios.PeliculaService;

@Component
public class PersonajeMapper {

	@Autowired
	private PeliculaService peliculaServicio;
	
	@Autowired
	private PersonajeRepositorio personajeRepositorio;
	
	public Personaje personajeDto2PersonajeEntity (PersonajeDto dto) {
		Personaje personaje = new Personaje();
		
		personaje.setEdad(dto.getEdad());
		personaje.setHistoria(dto.getHistoria());
		personaje.setImagen(dto.getImagen());
		personaje.setNombre(dto.getNombre());		
		personaje.setPeliculaId(dto.getPeliculaId());		
		personaje.setPeso(dto.getPeso());
		
		try {
			personaje.setPelicula(peliculaServicio.findById(personaje.getPeliculaId()));			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return personaje;
	}
	
	public PersonajeDto PersonajeEntity2personajeDto (Personaje entity) {
		PersonajeDto dto = new PersonajeDto();
		
		dto.setId(entity.getId());
		dto.setEdad(entity.getEdad());
		dto.setHistoria(entity.getHistoria());
		dto.setImagen(entity.getImagen());
		dto.setNombre(entity.getNombre());
		dto.setPeliculaId(entity.getPeliculaId());
		dto.setPeso(entity.getPeso());
		return dto;
	}
	
	public List<PersonajeDto> PersonajeEntityList2personajeDtoList (List<Personaje> entities) {
		List<PersonajeDto> dtos = new ArrayList<>();
		for(Personaje entity: entities) {
			dtos.add(PersonajeEntity2personajeDto(entity));
		}
		return dtos;
	}
	
	public Personaje modificarpersonajeDto2PersonajeEntity (String id, PersonajeDto dto) {
		Optional<Personaje> respuesta = personajeRepositorio.findById(id);
		if(respuesta.isPresent()) {
			Personaje personaje = respuesta.get();
			personaje.setEdad(dto.getEdad());
			personaje.setHistoria(dto.getHistoria());
			personaje.setImagen(dto.getImagen());
			personaje.setNombre(dto.getNombre());		
			personaje.setPeliculaId(dto.getPeliculaId());		
			personaje.setPeso(dto.getPeso());
			
			try {
				personaje.setPelicula(peliculaServicio.findById(personaje.getPeliculaId()));			
			} catch (Exception e) {
				e.printStackTrace();
			}
			return personaje;
		}
		return null;
	}
}
