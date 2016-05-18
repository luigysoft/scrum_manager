package com.mx.smarttools.admin.pizarron.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.smarttools.admin.proyecto.dao.EstatusTareasDAO;
import com.mx.smarttools.admin.proyecto.model.EstatusTareas;

@Service
public class EstatusTareaServiceImpl implements EstatusTareaService {

	@Autowired
	private EstatusTareasDAO estatusTareaDAO;
	
	@Override
	public EstatusTareas findTarea(int estatusTareaId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveEstatusTarea(EstatusTareas estatusTarea) throws Exception {
		// TODO Auto-generated method stub
		estatusTareaDAO.add(estatusTarea);
	}

	@Override
	public void updateEstatusTarea(EstatusTareas estatusTarea) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteEstatusTarea(EstatusTareas estatusTarea) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<EstatusTareas> getEstatusTareas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrUpdate(EstatusTareas estatusTarea) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public int getLastId() throws Exception {
		// TODO Auto-generated method stub
		return estatusTareaDAO.getMaxEstatusTareasId();
	}

}
