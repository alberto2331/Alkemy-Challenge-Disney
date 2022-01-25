package com.disney.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.disney.dto.GeneroDto;
import com.disney.dto.PersonajeDto;
import com.disney.servicios.GeneroServicio;

@RestController
@RequestMapping("/genero")
public class GeneroControlador {

	@Autowired
	private GeneroServicio generoServicio;
	
	@PostMapping
	public ResponseEntity<GeneroDto> save(
			@RequestBody GeneroDto genero//en el body viene la info
			) {		
		GeneroDto generoGuardado = generoServicio.save(genero);
		return ResponseEntity.status(HttpStatus.CREATED).body(generoGuardado);
	}
	
}
