package com.hbt.semillero.dto;

import java.io.Serializable;

public class MarcaDTO implements Serializable{
	
	private static final long serialVersionUID= 1L;
	
	private long idMarca;
	private String nombre;
	
	
	public long getIdMarca() {
		return idMarca;
	}
	public void setIdMarca(long idMarca) {
		this.idMarca = idMarca;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String marca) {
		nombre = marca;
	}

}
