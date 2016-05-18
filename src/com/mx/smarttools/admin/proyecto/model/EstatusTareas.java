package com.mx.smarttools.admin.proyecto.model;

import java.io.Serializable;
import java.util.Date;

public class EstatusTareas implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 691897603089391322L;

	private int estatusTareasId;
	private int tareaFk;
	private String estatus;
	private Date fechaRegistro;
	private String usuarioRegistro;
	
	public int getEstatusTareasId() {
		return estatusTareasId;
	}
	public void setEstatusTareasId(int estatusTareasId) {
		this.estatusTareasId = estatusTareasId;
	}
	public int getTareaFk() {
		return tareaFk;
	}
	public void setTareaFk(int tareaFk) {
		this.tareaFk = tareaFk;
	}
	public String getEstatus() {
		return estatus;
	}
	public void setEstatus(String estatus) {
		this.estatus = estatus;
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
