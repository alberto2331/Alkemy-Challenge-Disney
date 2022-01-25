package com.disney.repositorios;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.disney.entidades.Pelicula;

@Repository
public interface PeliculaRepositorio extends JpaRepository<Pelicula, String>{
	
	@Query("select c from Pelicula c where c.titulo like :titulo "
			+ "and c.generoId like :generoId"
			)
	public ArrayList<Pelicula> listarPeliculas(String titulo,String generoId);
}
