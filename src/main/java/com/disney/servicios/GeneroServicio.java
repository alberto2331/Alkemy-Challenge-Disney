package com.disney.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.disney.dto.GeneroDto;
import com.disney.dto.PersonajeDto;
import com.disney.entidades.Foto;
import com.disney.entidades.Genero;
import com.disney.entidades.Personaje;
import com.disney.mapper.GeneroMapper;
import com.disney.repositorios.GeneroRepositorio;

@Service
public class GeneroServicio {
	
	@Autowired
	private GeneroRepositorio generoRepositorio;
	
	@Autowired
	private GeneroMapper generoMapper;
	
	@Transactional
	public GeneroDto save(GeneroDto dto) {
		Genero entity = generoMapper.generoDto2GeneroEntity(dto);
		Genero entitySave =generoRepositorio.save(entity);
		GeneroDto result = generoMapper.generoEntity2GeneroDto(entitySave);		
		return result;
	}
	
	public Genero findById(String id) throws Exception {
		Optional<Genero> respuesta = generoRepositorio.findById(id);
		if(respuesta.isPresent()) {
			Genero genero = respuesta.get();
			return genero;
		}else {
			throw new Exception("El generoId recibido no existe");
		}
	}
}
