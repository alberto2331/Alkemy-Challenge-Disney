package com.disney.mapper;

import org.springframework.stereotype.Component;

import com.disney.dto.GeneroDto;
import com.disney.entidades.Genero;

@Component
public class GeneroMapper {

	public Genero generoDto2GeneroEntity (GeneroDto dto) {
		Genero genero = new Genero();
		genero.setImagen(dto.getImagen());
		genero.setNombre(dto.getNombre());
		return genero;
	}
	
	public GeneroDto generoEntity2GeneroDto (Genero entity) {
		GeneroDto dto = new GeneroDto();
		
		dto.setId(entity.getId());
		dto.setImagen(entity.getImagen());
		dto.setNombre(entity.getNombre());
		
		return dto;
	}
}
