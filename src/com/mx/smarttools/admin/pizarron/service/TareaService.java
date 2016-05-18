package com.mx.smarttools.admin.pizarron.service;

import java.util.List;

import com.mx.smarttools.admin.proyecto.model.TareasHistoria;

public interface TareaService {
	public TareasHistoria findTarea(int tareaId);
	public void saveTarea(TareasHistoria tarea) throws Exception;
	public void updateTarea(TareasHistoria tarea) throws Exception;
	public void deleteTarea(TareasHistoria tarea) throws Exception;
	public List<TareasHistoria> getTareas();
	public void saveOrUpdate(TareasHistoria tarea) throws Exception;
	public int getLastId() throws Exception;
}
