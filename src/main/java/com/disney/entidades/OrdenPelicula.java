package com.disney.entidades;

import java.util.Comparator;

public class OrdenPelicula implements Comparator<Pelicula> {

	@Override
	public int compare(Pelicula p1, Pelicula p2) {
		if(p1.getFechaCreacion().isBefore(p2.getFechaCreacion())) {
			return 0;
		}else if(p1.getFechaCreacion().isAfter(p2.getFechaCreacion())){
			return -1;
		}else {
			return 1;	
		}		
	}

}
