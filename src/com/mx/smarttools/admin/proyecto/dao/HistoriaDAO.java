package com.mx.smarttools.admin.proyecto.dao;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.mx.smarttools.admin.proyecto.model.HistoriasUsuario;

public interface HistoriaDAO {
	public HistoriasUsuario add(HistoriasUsuario t) throws Exception;
	public HistoriasUsuario update(int id, HistoriasUsuario t) throws Exception;
	public boolean delete(int id);
	public boolean delete(HistoriasUsuario t);
	public HistoriasUsuario getById(int id) throws Exception;
	public List<HistoriasUsuario> getAll() throws IOException;
	public List<HistoriasUsuario> getByIdProyect(int id) throws IOException;
	public int getMaxId() throws IOException;
	public List<HistoriasUsuario> getHistoriasByParameter(Map<String, String> parameter) throws IOException;
}
