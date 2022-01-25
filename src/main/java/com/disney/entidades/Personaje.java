package com.disney.entidades;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;


@Entity
@Table(name="personaje")
@SQLDelete(sql = "UPDATE personaje SET deleted=true WHERE id = ?")
@Where(clause="deleted=false")
public class Personaje implements Serializable {
	
	@Id
    @GeneratedValue(generator ="uuid")
	@GenericGenerator(name="uuid", strategy = "uuid2")
	private String id;	
	private String imagen;
	private String nombre;
	private Integer edad;
	private Double peso;
	private String historia;
	
	@ManyToOne(fetch = FetchType.EAGER)//Para implementar el soft-delete hay que sacar el atributo "cascade"
	@JoinColumn(name="pelicula_id", insertable= false, updatable= false)
	private Pelicula pelicula;	
	
	@Column(name ="pelicula_id", nullable = false)
	private String peliculaId;
	
	@Column(name ="deleted")
	private boolean deleted = Boolean.FALSE;//Esto es para implementar el soft-delete
	
	public Personaje() {
	}
	
	public Personaje(String id, String imagen, String nombre, Integer edad, Double peso, String historia, Pelicula pelicula, String peliculaId, boolean deleted
			) {
		super();
		this.id = id;
		this.imagen = imagen;
		this.nombre = nombre;
		this.edad = edad;
		this.peso = peso;
		this.historia = historia;
		this.pelicula = pelicula;
		this.peliculaId = peliculaId;
		this.deleted=deleted;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPeliculaId() {
		return peliculaId;
	}

	public void setPeliculaId(String peliculaId) {
		this.peliculaId = peliculaId;
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

	public Pelicula getPelicula() {
		return pelicula;
	}

	public void setPelicula(Pelicula pelicula) {
		this.pelicula = pelicula;
	}

	public boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

}
