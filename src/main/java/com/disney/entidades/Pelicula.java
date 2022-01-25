package com.disney.entidades;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="pelicula")
@SQLDelete(sql = "UPDATE pelicula SET deleted=true WHERE id = ?")
@Where(clause="deleted=false")
public class Pelicula implements Serializable, Comparable<Pelicula>{

	@Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;	
	private String imagen;
	private String titulo;	
	
	@Column(name="fecha_creacion")
	@DateTimeFormat(pattern="yyyy/MM/dd")
	private LocalDate fechaCreacion;
	private String calificacion;
	
	@ManyToOne(fetch = FetchType.EAGER)
	//@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	//Un genero puede tener varias peliculas:
	/*
	 Siendo:
	 *fetch = FetchType.EAGER: 	indica que la inicializacion será temprana. 
	 						   		Cuando pida un dato de tipo "Pelicula" SI O SI VA A VENIR CON SU GENERO.
	 *cascade = CascadeType.ALL:	significa que todas las operaciones que realice sobre "Pelicula"
									que se aplicaran sobre "Genero" tambien.
	 */
	@JoinColumn(name="genero_id", insertable= false, updatable= false)
	/*
	Con el joinColumn indico como voy a joinear la entidad "Pelicula" con el Genero
	* insertable= false, updatable= false --> 	le coloco estos atributos porque este atributo lo estoy usando para
	 											obtener el "Genero" entero
	* 	Este atributo Genero me sirve para cuando estoy haciendo consultas 
	 	me traigo TODA la info del Genero y no solo su id
	 */
	private Genero genero;

	@Column(name ="genero_id", nullable = false)
	/*
	 el atributo "generoId" se usa para cuando estoy creando una Pelicula no paso el genero entero sino que paso su id
	 En SQL no se usa camel case por eso le coloco como name="genero_id"
	 */
	private String generoId;
	
	@Column(name ="deleted")
	private boolean deleted = Boolean.FALSE;

	public Pelicula() {
	}

	public Pelicula(String id, String imagen, String titulo, LocalDate fechaCreacion, String calificacion, Genero genero,
			String generoId, boolean deleted) {
		super();
		this.id = id;
		this.imagen = imagen;
		this.titulo = titulo;
		this.fechaCreacion = fechaCreacion;
		this.calificacion = calificacion;
		this.genero = genero;
		this.generoId = generoId;
		this.deleted=deleted;
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

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public String getGeneroId() {
		return generoId;
	}

	public void setGeneroId(String generoId) {
		this.generoId = generoId;
	}
	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	@Override
	public int compareTo(Pelicula p) {
		if(p.getFechaCreacion().isBefore(fechaCreacion)) {
			return 0;
		}else if(p.getFechaCreacion().isAfter(fechaCreacion)){
			return -1;
		}else {
			return 1;	
		}		
	}	
}
