package com.mx.smarttools.admin.proyecto.model;

import java.io.Serializable;
import java.util.Date;

public class TareasHistoria implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int tareaId;
	private int historiaFk;
	private int numeroTarea;
	private String nombreTarea;
	private String descripcionTarea;
	private int consecutivo;
	private String estatus;
	private int colaboradorFk;
	private Date fechaRegistro;
	private String usuarioRegistro;
	
	public int getTareaId() {
		return tareaId;
	}
	public void setTareaId(int tareaId) {
		this.tareaId = tareaId;
	}
	public int getHistoriaFk() {
		return historiaFk;
	}
	public void setHistoriaFk(int historiaFk) {
		this.historiaFk = historiaFk;
	}
	public int getNumeroTarea() {
		return numeroTarea;
	}
	public void setNumeroTarea(int numeroTarea) {
		this.numeroTarea = numeroTarea;
	}
	public String getNombreTarea() {
		return nombreTarea;
	}
	public void setNombreTarea(String nombreTarea) {
		this.nombreTarea = nombreTarea;
	}
	public String getDescripcionTarea() {
		return descripcionTarea;
	}
	public void setDescripcionTarea(String descripcionTarea) {
		this.descripcionTarea = descripcionTarea;
	}
	public int getConsecutivo() {
		return consecutivo;
	}
	public void setConsecutivo(int consecutivo) {
		this.consecutivo = consecutivo;
	}
	public String getEstatus() {
		return estatus;
	}
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	public int getColaboradorFk() {
		return colaboradorFk;
	}
	public void setColaboradorFk(int colaboradorFk) {
		this.colaboradorFk = colaboradorFk;
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
