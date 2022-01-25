package com.disney.dto;

import com.disney.entidades.Foto;
import com.disney.entidades.Pelicula;
import com.disney.view.View;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

public class PersonajeDto {
	
	private String id; //cuando estamos creando el presonaje no necesitamos el id, eso se genera automaticamente
	@JsonView(View.Base.class)//Esto es para seleccionar que atributos del Dto quiero enviar al servidor cuando haya una peticion Get
	private String imagen;
	@JsonView(View.Base.class)
	private String nombre;
	private Integer edad;
	private Double peso;
	private String historia;
	private String peliculaId;
	
	
	public String getPeliculaId() {
		return peliculaId;
	}
	public void setPeliculaId(String peliculaId) {
		this.peliculaId = peliculaId;
	}
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
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getEdad() {
		return edad;
	}
	public void setEdad(Integer edad) {
		this.edad = edad;
	}
	public Double getPeso() {
		return peso;
	}
	public void setPeso(Double peso) {
		this.peso = peso;
	}
	public String getHistoria() {
		return historia;
	}
	public void setHistoria(String historia) {
		this.historia = historia;
	}
	
}
