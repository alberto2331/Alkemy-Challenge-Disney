package com.disney.dto;

import java.time.LocalDate;

import com.disney.entidades.Genero;
import com.disney.view.View;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

public class PeliculaDto {
	
	private String id;
	@JsonView(View.Base.class)
	private String imagen;
	@JsonView(View.Base.class)
	private String titulo;
	@JsonView(View.Base.class)
	private LocalDate fechaCreacion;
	private String calificacion;
	private String generoId;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public LocalDate getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(LocalDate fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public String getCalificacion() {
		return calificacion;
	}
	public void setCalificacion(String calificacion) {
		this.calificacion = calificacion;
	}
	public String getGeneroId() {
		return generoId;
	}
	public void setGeneroId(String generoId) {
		this.generoId = generoId;
	}
	
}
