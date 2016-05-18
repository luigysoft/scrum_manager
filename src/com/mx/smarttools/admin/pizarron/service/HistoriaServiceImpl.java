package com.mx.smarttools.admin.pizarron.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.smarttools.admin.proyecto.dao.HistoriaDAO;
import com.mx.smarttools.admin.proyecto.model.HistoriasUsuario;

@Service
public class HistoriaServiceImpl implements HistoriaService {

	@Autowired
	private HistoriaDAO historiaDAO;
	
	@Override
	public HistoriasUsuario findHistoria(int historiaId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveHistoria(HistoriasUsuario historia) throws Exception {
		// TODO Auto-generated method stub
		historiaDAO.add(historia);
	}

	@Override
	public void updateHistoria(HistoriasUsuario historia) throws Exception {
		// TODO Auto-generated method stub
		historiaDAO.update(historia.getHistoriaId(), historia);
	}

	@Override
	public void deleteHistoria(HistoriasUsuario historia) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<HistoriasUsuario> getHistorias() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrUpdate(HistoriasUsuario historia) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public int getLastId() throws Exception {
		// TODO Auto-generated method stub
		return historiaDAO.getMaxId();
	}

	@Override
	public List<HistoriasUsuario> getHistoriasByParameter(Map<String, String> parameter) throws Exception {
		// TODO Auto-generated method stub
		return historiaDAO.getHistoriasByParameter(parameter);
	}

}
