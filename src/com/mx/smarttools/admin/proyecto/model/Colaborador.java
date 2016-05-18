package com.mx.smarttools.admin.proyecto.model;

import java.io.Serializable;
import java.util.Date;

public class Colaborador implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int colaboradorId;
	private String nombreColaborador;
	private String nombreCorto;
	private Date fechaRegistro;
	private String usuarioRegistro;
	
	public int getColaboradorId() {
		return colaboradorId;
	}
	public void setColaboradorId(int colaboradorId) {
		this.colaboradorId = colaboradorId;
	}
	public String getNombreColaborador() {
		return nombreColaborador;
	}
	public void setNombreColaborador(String nombreColaborador) {
		this.nombreColaborador = nombreColaborador;
	}
	public String getNombreCorto() {
		return nombreCorto;
	}
	public void setNombreCorto(String nombreCorto) {
		this.nombreCorto = nombreCorto;
	}
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	public String getUsuarioRegistro() {
		return usuarioRegistro;
	}
	public void setUsuarioRegistro(String usuarioRegistro) {
		this.usuarioRegistro = usuarioRegistro;
	}

}
