package com.mx.smarttools.admin.proyecto.dao;

import java.io.IOException;
import java.util.List;

import com.mx.smarttools.admin.proyecto.model.Proyecto;

public interface ProyectoDAO {
	public Proyecto add(Proyecto t) throws Exception;
	public Proyecto update(int id, Proyecto t) throws Exception;
	public boolean delete(int id);
	public boolean delete(Proyecto t);
	public Proyecto getById(int id);
	public List<Proyecto> getAll() throws IOException;
	public int getMaxId() throws IOException;
}
