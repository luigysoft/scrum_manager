package com.mx.smarttools.admin.pizarron.service;

import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.swing.plaf.basic.BasicComboBoxUI.ItemHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.smarttools.admin.proyecto.dao.HistoriaDAO;
import com.mx.smarttools.admin.proyecto.dao.ProyectoDAO;
import com.mx.smarttools.admin.proyecto.dao.TareaDAO;
import com.mx.smarttools.admin.proyecto.model.HistoriasUsuario;
import com.mx.smarttools.admin.proyecto.model.Proyecto;
import com.mx.smarttools.admin.proyecto.model.TareasHistoria;

@Service
public class ProyectoServiceImpl implements ProyectoService {

	@Autowired
	private ProyectoDAO proyectoDAO;
	@Autowired
	private HistoriaDAO historiaDAO;
	@Autowired
	private TareaDAO tareaDAO;
	
	@Override
	public Proyecto fidProyecto(int proyectoId) {
		// TODO Auto-generated method stub
		Proyecto proyecto = null;
		
		proyecto = proyectoDAO.getById(proyectoId);
		if(proyecto != null){
			proyecto.setHistoriasUsuarios(
					getHistorias(proyecto,"consecutivo"));
		}
		
//		if(proyecto.getHistoriasUsuarios() != null){
//			for(HistoriasUsuario hu: proyecto.getHistoriasUsuarios()){
//				System.out.println("At srvice proy, hu: "+hu.getConsecutivo() +
//						", "+ hu.getNombreHistoria() +", "+ hu.getDescripcionHistoria());
//			}
//		}
		return proyecto;
	}

	@Override
	public void saveProyecto(Proyecto proyecto) throws Exception {
		// TODO Auto-generated method stub
		proyectoDAO.add(proyecto);
	}

	@Override
	public void updateProyecto(Proyecto proyecto) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteProyecto(Proyecto proyecto) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Proyecto> getProyectos() {
		// TODO Auto-generated method stub
		try {
			System.out.println("Antes de consultar desde el DAO");
			List<Proyecto> proyectosList = proyectoDAO.getAll();
			
			for(Proyecto proyecto: proyectosList){
				proyecto.setHistoriasUsuarios(
						getHistorias(proyecto,"consecutivo"));
			}
			return proyectosList;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
//		System.out.println("Estoy llegando hasta el service");
//		return null;
	}

	@Override
	public void saveOrUpdate(Proyecto proyecto) throws Exception {
		// TODO Auto-generated method stub

	}
	
	private List<HistoriasUsuario> getHistorias(Proyecto proyecto, String columnToSort){
		List<HistoriasUsuario> historiasList = null;
		try {
			historiasList = historiaDAO.getByIdProyect(proyecto.getProyectoId());
			
			Collections.sort(historiasList, new HistoriasUsuario.CompConsecutivo());
			
			for (int i = 0; i < historiasList.size(); i++) {
				HistoriasUsuario auxHist = historiasList.get(i);
				auxHist.setTareas(getTareasByHistoria(auxHist));
				historiasList.set(i, auxHist);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return historiasList;		
	}
	
	private List<TareasHistoria> getTareasByHistoria(HistoriasUsuario historia){
		List<TareasHistoria> tareasList = null;
		
		try {
			tareasList = tareaDAO.getByIdHistoria(historia.getHistoriaId());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tareasList;
	}

	@Override
	public int getLastId() throws Exception {
		// TODO Auto-generated method stub
		return proyectoDAO.getMaxId();
	}

}
