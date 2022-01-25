package com.disney.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.disney.entidades.Foto;

@Repository
public interface FotoRepositorio extends JpaRepository<Foto, String>{

}
