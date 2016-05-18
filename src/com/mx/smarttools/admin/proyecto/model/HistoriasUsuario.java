package com.mx.smarttools.admin.proyecto.model;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class HistoriasUsuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int historiaId;
	private int proyectoFk;
	private String nombreHistoria;
	private String descripcionHistoria;
	private int consecutivo;
	private int esfuerzoFk;
	private List<TareasHistoria> tareas;
	private Date fechaRegistro;
	private String usuarioRegistro;
	
	// Comparator
    public static class CompId implements Comparator<HistoriasUsuario> {
       @Override
		public int compare(HistoriasUsuario arg0, HistoriasUsuario arg1) {
			// TODO Auto-generated method stub
			return arg0.historiaId - arg1.historiaId;
		}
    }

    public static class CompConsecutivo implements Comparator<HistoriasUsuario> {
        @Override
        public int compare(HistoriasUsuario arg0, HistoriasUsuario arg1) {
            return arg0.consecutivo - arg1.consecutivo;
        }
    }
    
	public int getHistoriaId() {
		return historiaId;
	}
	public void setHistoriaId(int historiaId) {
		this.historiaId = historiaId;
	}
	public int getProyectoFk() {
		return proyectoFk;
	}
	public void setProyectoFk(int proyectoFk) {
		this.proyectoFk = proyectoFk;
	}
	public String getNombreHistoria() {
		return nombreHistoria;
	}
	public void setNombreHistoria(String nombreHistoria) {
		this.nombreHistoria = nombreHistoria;
	}
	public String getDescripcionHistoria() {
		return descripcionHistoria;
	}
	public void setDescripcionHistoria(String descripcionHistoria) {
		this.descripcionHistoria = descripcionHistoria;
	}
	public int getConsecutivo() {
		return consecutivo;
	}
	public void setConsecutivo(int consecutivo) {
		this.consecutivo = consecutivo;
	}
	public int getEsfuerzoFk() {
		return esfuerzoFk;
	}
	public void setEsfuerzoFk(int esfuerzoFk) {
		this.esfuerzoFk = esfuerzoFk;
	}
	public List<TareasHistoria> getTareas() {
		return tareas;
	}
	public void setTareas(List<TareasHistoria> tareas) {
		this.tareas = tareas;
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
