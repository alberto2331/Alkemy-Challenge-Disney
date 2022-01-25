package com.disney.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.disney.dto.PeliculaDto;
import com.disney.dto.PersonajeDto;
import com.disney.entidades.Pelicula;
import com.disney.entidades.Personaje;
import com.disney.repositorios.PeliculaRepositorio;
import com.disney.servicios.GeneroServicio;

@Component
public class PeliculaMapper {

	@Autowired
	private GeneroServicio generoServicio;
	
	@Autowired
	private PeliculaRepositorio peliculaRepositorio;
	
	public Pelicula peliculaDto2PeliculaEntity (PeliculaDto dto) {
		Pelicula pelicula = new Pelicula();
		
		pelicula.setCalificacion(dto.getCalificacion());
		pelicula.setFechaCreacion(dto.getFechaCreacion());
		pelicula.setGeneroId(dto.getGeneroId());		
		pelicula.setImagen(dto.getImagen());
		pelicula.setTitulo(dto.getTitulo());
		try {
			pelicula.setGenero(generoServicio.findById(pelicula.getGeneroId()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pelicula;
	}
	
	public PeliculaDto PeliculaEntity2peliculaDto (Pelicula entity) {
		PeliculaDto dto = new PeliculaDto();
		
		dto.setId(entity.getId());
		dto.setCalificacion(entity.getCalificacion());
		dto.setFechaCreacion(entity.getFechaCreacion());
		dto.setGeneroId(entity.getGeneroId());
		dto.setImagen(entity.getImagen());
		dto.setTitulo(entity.getTitulo());
		
		return dto;
	}
	
	public List<PeliculaDto> PeliculaEntityList2peliculaDtoList (List<Pelicula> entities) {
		List<PeliculaDto> dtos = new ArrayList<>();
		for(Pelicula entity: entities) {
			dtos.add(PeliculaEntity2peliculaDto(entity));
		}
		return dtos;
	}
	
	public Pelicula modificarpeliculaDto2PeliculaEntity (String id, PeliculaDto dto) {
		Optional<Pelicula> respuesta = peliculaRepositorio.findById(id);
		if(respuesta.isPresent()) {
			Pelicula pelicula = respuesta.get();
			pelicula.setCalificacion(dto.getCalificacion());
			pelicula.setFechaCreacion(dto.getFechaCreacion());
			pelicula.setGeneroId(dto.getGeneroId());		
			pelicula.setImagen(dto.getImagen());
			pelicula.setTitulo(dto.getTitulo());
			try {
				pelicula.setGenero(generoServicio.findById(pelicula.getGeneroId()));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return pelicula;
		}
		return null;
	}
}
