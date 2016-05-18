package com.mx.smarttools.admin.proyecto.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mx.smarttools.admin.common.Constantes;
import com.mx.smarttools.admin.common.beans.MyFileReader;
import com.mx.smarttools.admin.common.utils.FechaUtils;
import com.mx.smarttools.admin.proyecto.model.HistoriasUsuario;
import com.mx.smarttools.admin.proyecto.model.Proyecto;

@Component("historiaDAO")
public class HistoriaDAOImpl implements HistoriaDAO {

	@Autowired
	private MyFileReader fileReader;
	
	@Override
	public HistoriasUsuario add(HistoriasUsuario t) throws Exception {
		// TODO Auto-generated method stub
		String row = getRowDeObjectEntity(t);
		fileReader.writeFile(row);
		return t;
	}

	@Override
	public HistoriasUsuario update(int id, HistoriasUsuario t) throws Exception {
		// TODO Auto-generated method stub
		fileReader.setPathFile("C:\\TestProyectosDB\\historias.csv");
		HistoriasUsuario historia = getById(id);
		
		Calendar fecha = FechaUtils.getFechaByDate(historia.getFechaRegistro());
		
		String historiaBefore = historia.getHistoriaId() 
				+ "|" + historia.getProyectoFk()
				+ "|" + historia.getNombreHistoria()
				+ "|" + historia.getDescripcionHistoria()
				+ "|" + historia.getConsecutivo()
				+ "|" + historia.getEsfuerzoFk()
				+ "|" +fecha.get(Calendar.DAY_OF_MONTH)+","+(fecha.get(Calendar.MONTH)+1)+","+fecha.get(Calendar.YEAR)
				+ "|" + historia.getUsuarioRegistro();
		
		Calendar fecha2 = FechaUtils.getFechaByDate(t.getFechaRegistro());
		
		String historiaNew = t.getHistoriaId() 
				+ "|" + t.getProyectoFk()
				+ "|" + t.getNombreHistoria()
				+ "|" + t.getDescripcionHistoria()
				+ "|" + t.getConsecutivo()
				+ "|" + t.getEsfuerzoFk()
				+ "|" +fecha.get(Calendar.DAY_OF_MONTH)+","+(fecha.get(Calendar.MONTH)+1)+","+fecha.get(Calendar.YEAR)
				+ "|" + t.getUsuarioRegistro();
		
		fileReader.overwriteRow(historiaBefore,historiaNew);
		
		historia = getById(id);
		return historia;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(HistoriasUsuario t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public HistoriasUsuario getById(int id) throws Exception {
		// TODO Auto-generated method stub
		List<HistoriasUsuario> hisoriasList = getAll();
		HistoriasUsuario hist = null;
		
		Iterator<HistoriasUsuario> iter = hisoriasList.iterator();
		
		while(iter.hasNext()){
			HistoriasUsuario auxHist = iter.next();
			
			if(auxHist.getHistoriaId() == id){
				hist = auxHist;
				break;
			}
		}
		return hist;
	}
	
	@Override
	public List<HistoriasUsuario> getHistoriasByParameter(Map<String,String> parameter) throws IOException {
		List<HistoriasUsuario> hisoriasList = getAll();
		Iterator<HistoriasUsuario> iter = hisoriasList.iterator();
		int idHistoria = 0;
		int idProyecto = 0;
		int idEsfuerzo = 0;
		
		while(iter.hasNext()){
			HistoriasUsuario auxHist = iter.next();
			
			if(parameter.containsKey("idHistoria"))
				idHistoria = Integer.parseInt(parameter.get("idHistoria"));
			if(parameter.containsKey("idProyecto"))
				idProyecto = Integer.parseInt(parameter.get("idProyecto"));
			if(parameter.containsKey("idEsfuerzo"))
				idEsfuerzo = Integer.parseInt(parameter.get("idEsfuerzo"));
			
			if(parameter.containsKey("idHistoria") 
					&& parameter.containsKey("idProyecto") 
					&& parameter.containsKey("idEsfuerzo")){
				
				if(auxHist.getHistoriaId() != idHistoria || auxHist.getProyectoFk() != idProyecto
						|| auxHist.getEsfuerzoFk() != idEsfuerzo){
					iter.remove();
				}
				
			}else{
				if(parameter.containsKey("idHistoria") 
						&& parameter.containsKey("idProyecto")){
					if(auxHist.getHistoriaId() != idHistoria 
							|| auxHist.getProyectoFk() != idProyecto){
						iter.remove();
					}

				}else if(parameter.containsKey("idHistoria") 
						&& parameter.containsKey("idEsfuerzo")){
					if(auxHist.getHistoriaId() != idHistoria 
							|| auxHist.getEsfuerzoFk() != idEsfuerzo){
						iter.remove();
					}

				}else if(parameter.containsKey("idProyecto") 
						&& parameter.containsKey("idEsfuerzo")){
					if(auxHist.getProyectoFk() != idProyecto 
							|| auxHist.getEsfuerzoFk() != idEsfuerzo){
						iter.remove();
					}
				}else{
					if(parameter.containsKey("idHistoria")){
						if(auxHist.getHistoriaId() != idHistoria){
							iter.remove();
						}
					}else if(parameter.containsKey("idProyecto")){
						if(auxHist.getProyectoFk() != idProyecto){
							iter.remove();
						}
					}else if(parameter.containsKey("idEsfuerzo")){
						if(auxHist.getEsfuerzoFk() != idEsfuerzo){
							iter.remove();
						}
					}
				}
			}
		}
		return hisoriasList;
	}

	@Override
	public List<HistoriasUsuario> getAll() throws IOException {
		// TODO Auto-generated method stub
		fileReader.setPathFile("C:\\TestProyectosDB\\historias.csv");
		List<String> renglonesList = null;
		List<HistoriasUsuario> hisoriasList = null;
		String[] items;
		int numberLine = 0;
		
		renglonesList = fileReader.readFile();
		if(renglonesList.size() > 1){
			hisoriasList = new ArrayList<HistoriasUsuario>();
			
			for(String renglon: renglonesList){
				if(!renglon.isEmpty()){
					items = renglon.split("\\|");
//					System.out.println("ITEMS lengt:"+items.length);
					
					if(items.length == 8) {
						if(numberLine > 0) {
//							System.out.println("entra a tratar");
						
							HistoriasUsuario historia = getHistoriaDeArray(items);
							
							if(historia != null){
								hisoriasList.add(historia);
							}
						}
						numberLine++;
					}
				}
			}
		}
		return hisoriasList;
	}
	
	@Override
	public List<HistoriasUsuario> getByIdProyect(int id) throws IOException {
		// TODO Auto-generated method stub
		List<HistoriasUsuario> historiasList =  getAll();
		
		Iterator<HistoriasUsuario> iter = historiasList.iterator();
		while (iter.hasNext()) {
			HistoriasUsuario aux = iter.next(); // must be called before you can call i.remove()
		   
			// Do something
			if(aux.getProyectoFk() != id){
				iter.remove();
			}
		}
		
		return historiasList;
	}

	private HistoriasUsuario getHistoriaDeArray(String[] itemsLine){
		HistoriasUsuario historia = null;
		
		if(itemsLine[0] != null && itemsLine[1] != null && itemsLine[2] != null 
				&& itemsLine[3] != null && itemsLine[4] != null && itemsLine[5] != null
				&& itemsLine[6] != null && itemsLine[7] != null){
			historia = new HistoriasUsuario();
			
			historia.setHistoriaId(Integer.parseInt(
					itemsLine[0]));
			historia.setProyectoFk(Integer.parseInt(
					itemsLine[1]));
			historia.setNombreHistoria(itemsLine[2]);
			historia.setDescripcionHistoria(itemsLine[3]);
			historia.setConsecutivo(Integer.parseInt(
					itemsLine[4]));
			historia.setEsfuerzoFk(Integer.parseInt(
					itemsLine[5]));
			historia.setFechaRegistro(
					FechaUtils.getFechaFromParse(itemsLine[6]));
			historia.setUsuarioRegistro(itemsLine[7]);
			
		}
		
		return historia;
	}

	@Override
	public int getMaxId() throws IOException {
		// TODO Auto-generated method stub
		int index = 0;
		
		List<HistoriasUsuario> historiasList = null;
		historiasList = getAll();
		
		if(historiasList != null){
			if(!historiasList.isEmpty()){
				HistoriasUsuario hist = historiasList.get(
						historiasList.size()-1);
				index = hist.getHistoriaId();
			}
		}
		return index;
	}
	
	private String getRowDeObjectEntity(HistoriasUsuario entity){
		String row = null;
		Calendar calFecReg = null; 
		
		if(entity != null){
			
			calFecReg = FechaUtils.getFechaByDate(
					entity.getFechaRegistro());
			
			row = entity.getHistoriaId()
					+ Constantes.TOKEN_ROW_SEPARATOR + entity.getProyectoFk()
					+ Constantes.TOKEN_ROW_SEPARATOR + entity.getNombreHistoria()
					+ Constantes.TOKEN_ROW_SEPARATOR + entity.getDescripcionHistoria()
					+ Constantes.TOKEN_ROW_SEPARATOR + entity.getConsecutivo()
					+ Constantes.TOKEN_ROW_SEPARATOR + entity.getEsfuerzoFk()
					+ Constantes.TOKEN_ROW_SEPARATOR + calFecReg.get(Calendar.DAY_OF_MONTH)
						+ Constantes.TOKEN_DATE_SEPARATOR + (calFecReg.get(Calendar.MONTH)+1) 
						+ Constantes.TOKEN_DATE_SEPARATOR + calFecReg.get(Calendar.YEAR)
					+ Constantes.TOKEN_ROW_SEPARATOR + entity.getUsuarioRegistro();
		}
		
		return row;
	}

	public MyFileReader getFileReader() {
		return fileReader;
	}

	public void setFileReader(MyFileReader fileReader) {
		this.fileReader = fileReader;
	}

}
