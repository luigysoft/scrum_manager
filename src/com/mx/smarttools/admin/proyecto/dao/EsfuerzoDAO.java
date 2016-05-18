package com.mx.smarttools.admin.proyecto.dao;

import java.io.IOException;
import java.util.List;

import com.mx.smarttools.admin.proyecto.model.Esfuerzo;

public interface EsfuerzoDAO {
	public Esfuerzo add(Esfuerzo e) throws Exception;
	public Esfuerzo update(int id, Esfuerzo e) throws Exception;
	public boolean delete(int id);
	public boolean delete(Esfuerzo e);
	public Esfuerzo getById(int id);
	public List<Esfuerzo> getAll() throws IOException;
	public List<Esfuerzo> getByIdEsfuerzo(int id) throws IOException;
	public int getMaxId() throws IOException;
	public List<Esfuerzo> getByIdProyecto(int idProy) throws IOException;
}
