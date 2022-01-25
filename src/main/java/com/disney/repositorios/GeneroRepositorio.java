package com.disney.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.disney.entidades.Genero;

@Repository
public interface GeneroRepositorio extends JpaRepository<Genero, String>{

}
