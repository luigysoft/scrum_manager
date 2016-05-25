package com.mx.smarttools.admin.pizarron.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.smarttools.admin.proyecto.dao.EsfuerzoDAO;
import com.mx.smarttools.admin.proyecto.model.Esfuerzo;

@Service
public class EsfuerzoServiceImpl implements EsfuerzoService {
	
	@Autowired
	private EsfuerzoDAO esfuerzoDAO;

	@Override
	public Esfuerzo findEsfuerzo(int esfuerzoId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveEsfuerzo(Esfuerzo esfuerzo) throws Exception {
		// TODO Auto-generated method stub
		esfuerzoDAO.add(esfuerzo);
	}

	@Override
	public void updateEsfuerzo(Esfuerzo esfuerzo) throws Exception {
		// TODO Auto-generated method stub
		esfuerzoDAO.update(esfuerzo.getEsfuerzoId(), esfuerzo);
	}

	@Override
	public void deleteEsfuerzo(Esfuerzo esfuerzo) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Esfuerzo> getEsfuerzos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrUpdate(Esfuerzo esfuerzo) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public int getLastId() throws Exception {
		// TODO Auto-generated method stub
		return esfuerzoDAO.getMaxId();
	}

	@Override
	public List<Esfuerzo> getEsfuerzosByIdProject(int projId) throws Exception {
		// TODO Auto-generated method stub
		return esfuerzoDAO.getByIdProyecto(projId);
	}

}
