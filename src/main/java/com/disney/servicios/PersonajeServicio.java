package com.disney.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.disney.dto.PersonajeDto;
import com.disney.entidades.Foto;
import com.disney.entidades.Personaje;
import com.disney.mapper.PersonajeMapper;
import com.disney.repositorios.PersonajeRepositorio;

@Service
public class PersonajeServicio {

	@Autowired
	private FotoServicio fotoServicio;
	
	@Autowired
	private PeliculaService peliculaServicio;
	
	@Autowired
	private PersonajeRepositorio personajeRepositorio;
	
	@Autowired
	private PersonajeMapper personajeMapper; 
	
	@Transactional
	public PersonajeDto save(PersonajeDto dto) {
		Personaje entity = personajeMapper.personajeDto2PersonajeEntity(dto);
		Personaje entitySave =personajeRepositorio.save(entity);
		PersonajeDto result = personajeMapper.PersonajeEntity2personajeDto(entitySave);		
		return result;
	}
	
	public List<PersonajeDto> listarPersonajes(){
		List<Personaje> entities = personajeRepositorio.findAll();
		List<PersonajeDto> result = personajeMapper.PersonajeEntityList2personajeDtoList(entities);		
		return result;
	}
	
	public void borrarPersonaje(String id) {
		personajeRepositorio.deleteById(id);
	}
	
	@Transactional
	public PersonajeDto modificar(String id, PersonajeDto dto) {
		Personaje entity = personajeMapper.modificarpersonajeDto2PersonajeEntity (id,dto);
		Personaje entitySave =personajeRepositorio.save(entity);
		PersonajeDto result = personajeMapper.PersonajeEntity2personajeDto(entitySave);		
		return result;
	}
	
	public Personaje getPersonajeById(String id) {
		Optional<Personaje> resultado = personajeRepositorio.findById(id);
		if(resultado.isPresent()) {
			Personaje personaje = resultado.get();
			return personaje;
		}else {
			return null;	
		}
	}
	
	public List<Personaje> listaPersonajeFiltrados (String nombre, Integer edad, String peliculaId){		
		System.out.println("Nombre en el servicio: "+nombre +" La edad es: "+ edad);
		if(edad==-1) {
			List<Personaje> personajesFiltrados = personajeRepositorio.listarPersonajeSinEdad("%"+nombre+"%" , "%"+peliculaId+"%");
			return personajesFiltrados;
		}else {
			List<Personaje> personajesFiltrados = personajeRepositorio.listarPersonajeConEdad("%"+nombre+"%" , edad, "%"+peliculaId+"%");
			return personajesFiltrados;
		}
	}
}
