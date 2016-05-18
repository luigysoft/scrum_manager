package com.mx.smarttools.admin.proyecto.dao;

import java.io.IOException;
import java.util.List;

import com.mx.smarttools.admin.proyecto.model.TareasHistoria;

public interface TareaDAO {
	public TareasHistoria add(TareasHistoria t) throws Exception;
	public TareasHistoria update(int id, TareasHistoria t) throws Exception;
	public boolean delete(int id);
	public boolean delete(TareasHistoria t);
	public TareasHistoria getById(int id);
	public List<TareasHistoria> getAll() throws IOException;
	public List<TareasHistoria> getByIdHistoria(int id) throws IOException;
	public int getMaxId() throws IOException;
}
