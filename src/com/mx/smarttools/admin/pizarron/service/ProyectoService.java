package com.mx.smarttools.admin.pizarron.service;

import java.util.List;

import com.mx.smarttools.admin.proyecto.model.Proyecto;

public interface ProyectoService {

	public Proyecto fidProyecto(int proyectoId);
	public void saveProyecto(Proyecto proyecto) throws Exception;
	public void updateProyecto(Proyecto proyecto) throws Exception;
	public void deleteProyecto(Proyecto proyecto) throws Exception;
	public List<Proyecto> getProyectos();
	public void saveOrUpdate(Proyecto proyecto) throws Exception;
	public int getLastId() throws Exception;
}
