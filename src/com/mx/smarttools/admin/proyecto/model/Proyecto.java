package com.mx.smarttools.admin.proyecto.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.mx.smarttools.admin.common.utils.FechaUtils;

public class Proyecto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int proyectoId;
	private String nombreProyecto;
	private String objetivo;
	private Date fechaInicio;
	private Date fechaTermino;
	private List<HistoriasUsuario> historiasUsuarios;
	private Date fechaRegistro;
	private String usuarioRegistro;
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Id: "+ proyectoId 
				+ " Nombre: " + nombreProyecto
				+ " Objetivo: " + nombreProyecto
				+ " Fecha inicio: " + FechaUtils.getStringDateAsFormat(fechaInicio, "dd/MM/yyyy")
				+ " Fecha termino: " + FechaUtils.getStringDateAsFormat(fechaTermino, "dd/MM/yyyy")
				+ " Fecha registro: " + FechaUtils.getStringDateAsFormat(fechaRegistro, "dd/MM/yyyy")
				+ " Usuario registro: " + usuarioRegistro
				;
	}
	public int getProyectoId() {
		return proyectoId;
	}
	public void setProyectoId(int proyectoId) {
		this.proyectoId = proyectoId;
	}
	public String getNombreProyecto() {
		return nombreProyecto;
	}
	public void setNombreProyecto(String nombreProyecto) {
		this.nombreProyecto = nombreProyecto;
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
	public List<HistoriasUsuario> getHistoriasUsuarios() {
		return historiasUsuarios;
	}
	public void setHistoriasUsuarios(List<HistoriasUsuario> historiasUsuarios) {
		this.historiasUsuarios = historiasUsuarios;
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
