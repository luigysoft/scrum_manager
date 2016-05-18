package com.mx.smarttools.admin.proyecto.model;

import java.io.Serializable;
import java.util.Date;

public class Esfuerzo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int esfuerzoId;
	private int proyectoFk;
	private String objetivo;
	private Date fechaInicio;
	private Date fechaTermino;
	private int consecutivo;
	private Date fechaRegistro;
	private String usuarioRegistro;
	
	public int getEsfuerzoId() {
		return esfuerzoId;
	}
	public void setEsfuerzoId(int esfuerzoId) {
		this.esfuerzoId = esfuerzoId;
	}
	public int getProyectoFk() {
		return proyectoFk;
	}
	public void setProyectoFk(int proyectoFk) {
		this.proyectoFk = proyectoFk;
	}
	public String getObjetivo() {
		return objetivo;
	}
	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaTermino() {
		return fechaTermino;
	}
	public void setFechaTermino(Date fechaTermino) {
		this.fechaTermino = fechaTermino;
	}
	public int getConsecutivo() {
		return consecutivo;
	}
	public void setConsecutivo(int consecutivo) {
		this.consecutivo = consecutivo;
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
