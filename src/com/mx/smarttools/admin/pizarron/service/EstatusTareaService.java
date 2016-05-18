package com.mx.smarttools.admin.pizarron.service;

import java.util.List;

import com.mx.smarttools.admin.proyecto.model.EstatusTareas;

public interface EstatusTareaService {
	public EstatusTareas findTarea(int estatusTareaId);
	public void saveEstatusTarea(EstatusTareas estatusTarea) throws Exception;
	public void updateEstatusTarea(EstatusTareas estatusTarea) throws Exception;
	public void deleteEstatusTarea(EstatusTareas estatusTarea) throws Exception;
	public List<EstatusTareas> getEstatusTareas();
	public void saveOrUpdate(EstatusTareas estatusTarea) throws Exception;
	public int getLastId() throws Exception;
}
