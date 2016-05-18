package com.mx.smarttools.admin.pizarron.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.smarttools.admin.proyecto.dao.TareaDAO;
import com.mx.smarttools.admin.proyecto.model.TareasHistoria;

@Service
public class TareaServiceImpl implements TareaService {

	@Autowired
	private TareaDAO tareaDAO;
	
	@Override
	public TareasHistoria findTarea(int tareaId) {
		// TODO Auto-generated method stub
		TareasHistoria tarea = null;
		tarea = tareaDAO.getById(tareaId);
		return tarea;
	}

	@Override
	public void saveTarea(TareasHistoria tarea) throws Exception {
		// TODO Auto-generated method stub
		tareaDAO.add(tarea);
	}

	@Override
	public void updateTarea(TareasHistoria tarea) throws Exception {
		// TODO Auto-generated method stub
		tareaDAO.update(tarea.getTareaId(), tarea);
	}

	@Override
	public void deleteTarea(TareasHistoria tarea) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<TareasHistoria> getTareas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrUpdate(TareasHistoria tarea) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public int getLastId() throws Exception {
		// TODO Auto-generated method stub
		return tareaDAO.getMaxId();
	}

}
