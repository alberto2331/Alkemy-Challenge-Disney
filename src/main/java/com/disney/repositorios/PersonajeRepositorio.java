package com.disney.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.disney.entidades.Personaje;

@Repository
public interface PersonajeRepositorio extends JpaRepository<Personaje, String>{
	
	@Query("select c from Personaje c where c.nombre like :nombre "
			+ "and c.peliculaId like :peliculaId"
			)
	public List<Personaje> listarPersonajeSinEdad(String nombre, String peliculaId);
	
	@Query("select c from Personaje c where c.nombre like :nombre "
			+ "and c.edad like :edad "
			+ "and c.peliculaId like :peliculaId"
			)
	public List<Personaje> listarPersonajeConEdad(String nombre,Integer edad, String peliculaId);

	
}
