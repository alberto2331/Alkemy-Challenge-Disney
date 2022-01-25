package com.disney.servicios;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.disney.dto.PeliculaDto;
import com.disney.dto.PersonajeDto;
import com.disney.entidades.Foto;
import com.disney.entidades.Genero;
import com.disney.entidades.OrdenPelicula;
import com.disney.entidades.Pelicula;
import com.disney.entidades.Personaje;
import com.disney.mapper.PeliculaMapper;
import com.disney.repositorios.PeliculaRepositorio;

@Service
public class PeliculaService {

	@Autowired
	private PeliculaRepositorio peliculaRepositorio;
	
	@Autowired
	private FotoServicio fotoServicio;
	
	@Autowired
	private GeneroServicio generoServicio;
	
	@Autowired
	private PeliculaMapper peliculaMapper;
	@Transactional
	public PeliculaDto save(PeliculaDto dto) {
		Pelicula entity = peliculaMapper.peliculaDto2PeliculaEntity(dto);
		Pelicula entitySave =peliculaRepositorio.save(entity);
		PeliculaDto result = peliculaMapper.PeliculaEntity2peliculaDto(entitySave);		
		return result;
	}

	public Pelicula findById(String id) throws Exception {
		Optional<Pelicula> respuesta = peliculaRepositorio.findById(id);
		if(respuesta.isPresent()) {
			Pelicula pelicula = respuesta.get();
			return pelicula;
		}else {
			throw new Exception("La peliculaId recibida no existe");
		}
	}
	
	public List<PeliculaDto> listarPeliculas(){
		List<Pelicula> entities = peliculaRepositorio.findAll();
		List<PeliculaDto> result = peliculaMapper.PeliculaEntityList2peliculaDtoList(entities);		
		return result;
	}
	
	public void borrarPelicula(String id) {
		peliculaRepositorio.deleteById(id);
	}
	
	@Transactional
	public PeliculaDto modificar(String id, PeliculaDto dto) {
		Pelicula entity = peliculaMapper.modificarpeliculaDto2PeliculaEntity (id,dto);
		Pelicula entitySave = peliculaRepositorio.save(entity);
		PeliculaDto result = peliculaMapper.PeliculaEntity2peliculaDto(entitySave);		
		return result;
	}
	
	public Pelicula getPeliculaById(String id) {
		Optional<Pelicula> resultado = peliculaRepositorio.findById(id);
		if(resultado.isPresent()) {
			Pelicula pelicula = resultado.get();			
			return pelicula;
		}else {
			return null;	
		}
	}
	
	public ArrayList<Pelicula> listaPeliculasFiltradas (String titulo, String generoId, String orden){		
		System.out.println("Nombre en el servicio: "+titulo +" El genero es: "+generoId );		
		ArrayList<Pelicula> peliculasFiltradas = peliculaRepositorio.listarPeliculas("%"+titulo+"%" , "%"+generoId+"%");				
		if(orden.equals("ASC")) {
			System.out.println("Estamos en ASC");
			Collections.sort(peliculasFiltradas);
			return peliculasFiltradas;
		}else if(orden.equals("DESC")){
			System.out.println("Estamos en DES");
			Collections.sort(peliculasFiltradas, new OrdenPelicula());
			return peliculasFiltradas;
		}
		return null;
	}
}
