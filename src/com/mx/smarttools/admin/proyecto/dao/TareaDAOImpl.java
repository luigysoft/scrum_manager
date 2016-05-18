package com.mx.smarttools.admin.proyecto.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mx.smarttools.admin.common.Constantes;
import com.mx.smarttools.admin.common.beans.MyFileReader;
import com.mx.smarttools.admin.common.utils.FechaUtils;
import com.mx.smarttools.admin.proyecto.model.HistoriasUsuario;
import com.mx.smarttools.admin.proyecto.model.TareasHistoria;

@Component("tareaDAO")
public class TareaDAOImpl implements TareaDAO {

	@Autowired
	private MyFileReader fileReader;
	
	@Override
	public TareasHistoria add(TareasHistoria t) throws Exception {
		// TODO Auto-generated method stub
		String row = getRowDeObjectEntity(t);
		fileReader.writeFile(row);
		return t;
	}

	@Override
	public TareasHistoria update(int id, TareasHistoria t) throws Exception {
		// TODO Auto-generated method stub
		fileReader.setPathFile("C:\\TestProyectosDB\\tareas.csv");
		TareasHistoria tarea = getById(id);
		Calendar fecha = FechaUtils.getFechaByDate(tarea.getFechaRegistro());
		
		String tareaBefore = tarea.getTareaId()+"|"+tarea.getHistoriaFk()+
				"|"+tarea.getNumeroTarea()+"|"+tarea.getNombreTarea()+
				"|"+tarea.getDescripcionTarea()+"|"+tarea.getConsecutivo()+
				"|"+tarea.getEstatus()+"|"+tarea.getColaboradorFk()+
				"|"+fecha.get(Calendar.DAY_OF_MONTH)+","+(fecha.get(Calendar.MONTH)+1)+","+fecha.get(Calendar.YEAR)+
				"|"+tarea.getUsuarioRegistro();
		
		Calendar fecha2 = FechaUtils.getFechaByDate(tarea.getFechaRegistro());
		
		String tareaNew = t.getTareaId()+"|"+t.getHistoriaFk()+
				"|"+t.getNumeroTarea()+"|"+t.getNombreTarea()+
				"|"+t.getDescripcionTarea()+"|"+t.getConsecutivo()+
				"|"+t.getEstatus()+"|"+t.getColaboradorFk()+
				"|"+fecha2.get(Calendar.DAY_OF_MONTH)+","+(fecha2.get(Calendar.MONTH)+1)+","+fecha2.get(Calendar.YEAR)+
				"|"+t.getUsuarioRegistro();
		
		
		fileReader.overwriteRow(tareaBefore, tareaNew);
		
		tarea = getById(id);
		return tarea;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(TareasHistoria t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public TareasHistoria getById(int id) {
		// TODO Auto-generated method stub
		TareasHistoria tarea = null;
		
		try {
			List<TareasHistoria> tareasList = getAll();
			
			Iterator<TareasHistoria> iter = tareasList.iterator();
			
			while(iter.hasNext()){
				TareasHistoria auxTarea = iter.next();
				
				if(auxTarea.getTareaId() == id){
					tarea = auxTarea;
					break;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tarea;
	}

	@Override
	public List<TareasHistoria> getAll() throws IOException {
		// TODO Auto-generated method stub
		fileReader.setPathFile("C:\\TestProyectosDB\\tareas.csv");
		List<String> renglonesList = null;
		List<TareasHistoria> tareasList = null;
		String[] items;
		int numberLine = 0;
				
		renglonesList = fileReader.readFile();
		if(renglonesList.size() > 1){
			tareasList = new ArrayList<TareasHistoria>();
			
			for(String renglon: renglonesList){
				if(!renglon.isEmpty()){
					items = renglon.split("\\|");
//					System.out.println("ITEMS lengt:"+items.length);
					
					if(items.length == 10) {
						if(numberLine > 0) {
//							System.out.println("entra a tratar");
							TareasHistoria tarea = getTareaDeArray(items);
							
							if(tarea != null)
								tareasList.add(tarea);
							
						}
						numberLine++;
					}
				}
			}
		}
		return tareasList;
	}

	@Override
	public List<TareasHistoria> getByIdHistoria(int id) throws IOException {
		// TODO Auto-generated method stub
		List<TareasHistoria> tareasList = getAll();
		
		Iterator<TareasHistoria > iter = tareasList.iterator();
		
		while (iter.hasNext()) {
			TareasHistoria aux = iter.next();
			
			if(aux.getHistoriaFk() != id){
				iter.remove();
			}
		}
		return tareasList;
	}
	
	@Override
	public int getMaxId() throws IOException {
		// TODO Auto-generated method stub
		int index = 0;
		List<TareasHistoria> tareasList = null;
		
		tareasList = getAll();
		
		if(tareasList != null){
			if(!tareasList.isEmpty()){
				TareasHistoria tarea = tareasList.get(
						tareasList.size() -1);
				index = tarea.getTareaId();
			}
		}
		return index;
	}

	private TareasHistoria getTareaDeArray(String[] itemsLine){
		TareasHistoria tarea = null;
		
		if(itemsLine[0] != null && itemsLine[1] != null && itemsLine[2] != null 
				&& itemsLine[3] != null && itemsLine[4] != null && itemsLine[5] != null
				&& itemsLine[6] != null && itemsLine[7] != null && itemsLine[8] != null
				&& itemsLine[9] != null){
			
			tarea = new TareasHistoria();
			tarea.setTareaId(Integer.parseInt(
					itemsLine[0]));
			tarea.setHistoriaFk(Integer.parseInt(
					itemsLine[1]));
			tarea.setNumeroTarea(Integer.parseInt(
					itemsLine[2]));
			tarea.setNombreTarea(itemsLine[3]);
			tarea.setDescripcionTarea(itemsLine[4]);
			tarea.setConsecutivo(Integer.parseInt(
					itemsLine[5]));
			tarea.setEstatus(itemsLine[6]);
			tarea.setColaboradorFk(Integer.parseInt(
					itemsLine[7]));
			tarea.setFechaRegistro(
					FechaUtils.getFechaFromParse(itemsLine[8]));
			tarea.setUsuarioRegistro(itemsLine[9]);
		}
		return tarea;
	}
	
	private String getRowDeObjectEntity(TareasHistoria entity){
		String row = null;
		Calendar calFecReg = null; 
		
		if(entity != null){
			
			calFecReg = FechaUtils.getFechaByDate(
					entity.getFechaRegistro());
			
			row = entity.getTareaId()
					+ Constantes.TOKEN_ROW_SEPARATOR + entity.getHistoriaFk()
					+ Constantes.TOKEN_ROW_SEPARATOR + entity.getNumeroTarea()
					+ Constantes.TOKEN_ROW_SEPARATOR + entity.getNombreTarea()
					+ Constantes.TOKEN_ROW_SEPARATOR + entity.getDescripcionTarea()
					+ Constantes.TOKEN_ROW_SEPARATOR + entity.getConsecutivo()
					+ Constantes.TOKEN_ROW_SEPARATOR + entity.getEstatus()
					+ Constantes.TOKEN_ROW_SEPARATOR + entity.getColaboradorFk()
					+ Constantes.TOKEN_ROW_SEPARATOR + calFecReg.get(Calendar.DAY_OF_MONTH)
						+ Constantes.TOKEN_DATE_SEPARATOR + (calFecReg.get(Calendar.MONTH)+1) 
						+ Constantes.TOKEN_DATE_SEPARATOR + calFecReg.get(Calendar.YEAR)
					+ Constantes.TOKEN_ROW_SEPARATOR + entity.getUsuarioRegistro();
		}
		
		return row;
	}

}
