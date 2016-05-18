package com.mx.smarttools.admin.proyecto.dao;

import java.io.IOException;
import java.util.List;

import com.mx.smarttools.admin.proyecto.model.EstatusTareas;

public interface EstatusTareasDAO {
	public EstatusTareas add(EstatusTareas e) throws Exception;
	public EstatusTareas update(int id, EstatusTareas e) throws Exception;
	public boolean delete(int id);
	public boolean delete(EstatusTareas e);
	public EstatusTareas getById(int id);
	public List<EstatusTareas> getAll() throws IOException;
	public List<EstatusTareas> getByIdTarea(int id) throws IOException;
	public int getMaxEstatusTareasId() throws IOException;
}
