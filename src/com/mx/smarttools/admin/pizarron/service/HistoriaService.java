package com.mx.smarttools.admin.pizarron.service;

import java.util.List;
import java.util.Map;

import com.mx.smarttools.admin.proyecto.model.HistoriasUsuario;

public interface HistoriaService {
	public HistoriasUsuario findHistoria(int historiaId);
	public void saveHistoria(HistoriasUsuario historia) throws Exception;
	public void updateHistoria(HistoriasUsuario historia) throws Exception;
	public void deleteHistoria(HistoriasUsuario historia) throws Exception;
	public List<HistoriasUsuario> getHistorias();
	public void saveOrUpdate(HistoriasUsuario historia) throws Exception;
	public int getLastId() throws Exception;
	public List<HistoriasUsuario> getHistoriasByParameter(Map<String, String> parameter)
		throws Exception;
}
