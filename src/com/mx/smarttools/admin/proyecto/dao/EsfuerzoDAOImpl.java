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
import com.mx.smarttools.admin.proyecto.model.Esfuerzo;

@Component("esfuerzoDAO")
public class EsfuerzoDAOImpl implements EsfuerzoDAO {

	@Autowired
	private MyFileReader fileReader;
	
	@Override
	public Esfuerzo add(Esfuerzo e) throws Exception {
		// TODO Auto-generated method stub
		String row = getRowDeObjectEntity(e);
		fileReader.writeFile(row);
		return e;
	}

	@Override
	public Esfuerzo update(int id, Esfuerzo e) throws Exception {
		// TODO Auto-generated method stub
		fileReader.setPathFile("C:\\TestProyectosDB\\esfuerzos.csv");
		Esfuerzo esfuerzo = getById(id);
		
		Calendar fecha = FechaUtils.getFechaByDate(esfuerzo.getFechaRegistro());
		Calendar fechaIni = FechaUtils.getFechaByDate(esfuerzo.getFechaInicio());
		Calendar fechaFin = FechaUtils.getFechaByDate(esfuerzo.getFechaTermino());
		
		String esfuerzoBefore = esfuerzo.getEsfuerzoId()
				+ Constantes.TOKEN_ROW_SEPARATOR + esfuerzo.getProyectoFk()
				+ Constantes.TOKEN_ROW_SEPARATOR + esfuerzo.getObjetivo()
				+ Constantes.TOKEN_ROW_SEPARATOR + fechaIni.get(Calendar.DAY_OF_MONTH)
					+ Constantes.TOKEN_DATE_SEPARATOR + (fechaIni.get(Calendar.MONTH)+1) 
					+ Constantes.TOKEN_DATE_SEPARATOR + fechaIni.get(Calendar.YEAR)
				+ Constantes.TOKEN_ROW_SEPARATOR + fechaFin.get(Calendar.DAY_OF_MONTH)
					+ Constantes.TOKEN_DATE_SEPARATOR + (fechaFin.get(Calendar.MONTH)+1) 
					+ Constantes.TOKEN_DATE_SEPARATOR + fechaFin.get(Calendar.YEAR)
				+ Constantes.TOKEN_ROW_SEPARATOR + esfuerzo.getConsecutivo()
				+ Constantes.TOKEN_ROW_SEPARATOR + fecha.get(Calendar.DAY_OF_MONTH)
					+ Constantes.TOKEN_DATE_SEPARATOR + (fecha.get(Calendar.MONTH)+1) 
					+ Constantes.TOKEN_DATE_SEPARATOR + fecha.get(Calendar.YEAR)
				+ Constantes.TOKEN_ROW_SEPARATOR + esfuerzo.getUsuarioRegistro();
		
		Calendar fecha2 = FechaUtils.getFechaByDate(esfuerzo.getFechaRegistro());
		Calendar fechaIni2 = FechaUtils.getFechaByDate(esfuerzo.getFechaInicio());
		Calendar fechaFin2 = FechaUtils.getFechaByDate(esfuerzo.getFechaTermino());
		
		String esfuerzoNew = e.getEsfuerzoId()
				+ Constantes.TOKEN_ROW_SEPARATOR + e.getProyectoFk()
				+ Constantes.TOKEN_ROW_SEPARATOR + e.getObjetivo()
				+ Constantes.TOKEN_ROW_SEPARATOR + fechaIni2.get(Calendar.DAY_OF_MONTH)
					+ Constantes.TOKEN_DATE_SEPARATOR + (fechaIni2.get(Calendar.MONTH)+1) 
					+ Constantes.TOKEN_DATE_SEPARATOR + fechaIni2.get(Calendar.YEAR)
				+ Constantes.TOKEN_ROW_SEPARATOR + fechaFin2.get(Calendar.DAY_OF_MONTH)
					+ Constantes.TOKEN_DATE_SEPARATOR + (fechaFin2.get(Calendar.MONTH)+1) 
					+ Constantes.TOKEN_DATE_SEPARATOR + fechaFin2.get(Calendar.YEAR)
				+ Constantes.TOKEN_ROW_SEPARATOR + e.getConsecutivo()
				+ Constantes.TOKEN_ROW_SEPARATOR + fecha2.get(Calendar.DAY_OF_MONTH)
					+ Constantes.TOKEN_DATE_SEPARATOR + (fecha2.get(Calendar.MONTH)+1) 
					+ Constantes.TOKEN_DATE_SEPARATOR + fecha2.get(Calendar.YEAR)
				+ Constantes.TOKEN_ROW_SEPARATOR + e.getUsuarioRegistro();
		
		fileReader.overwriteRow(esfuerzoBefore, esfuerzoNew);
		
		esfuerzo = getById(id);
		return esfuerzo;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Esfuerzo e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Esfuerzo getById(int id) throws IOException {
		// TODO Auto-generated method stub
		List<Esfuerzo> esfuerzoList = getAll();
		Esfuerzo esfuerzo = null;
		
		Iterator<Esfuerzo> iter = esfuerzoList.iterator();
		
		while(iter.hasNext()){
			Esfuerzo esfuerzoAux = iter.next();
			
			if(esfuerzoAux.getEsfuerzoId() == id){
				esfuerzo = esfuerzoAux;
				break;			}
		}
		return esfuerzo;
	}

	@Override
	public List<Esfuerzo> getAll() throws IOException {
		// TODO Auto-generated method stub
		fileReader.setPathFile("C:\\TestProyectosDB\\esfuerzos.csv");
		
		List<String> renglonesList = null;
		List<Esfuerzo> esfuerzosList = null;
		String[] items;
		int numberLine = 0;
		
		renglonesList = fileReader.readFile();
		
		if(renglonesList.size() > 1){
			esfuerzosList = new ArrayList<Esfuerzo>();
			
			for(String renglon: renglonesList){
				items = renglon.split("\\|");
				
				if(items.length == 8) {
					if(numberLine > 0) {
						Esfuerzo esfuerzo = getEsfuerzoDeArray(items);
						
						if(esfuerzo != null){
							esfuerzosList.add(esfuerzo);
						}
					}
					
					numberLine++;
				}
			}
		}
		return esfuerzosList;
	}

	@Override
	public List<Esfuerzo> getByIdEsfuerzo(int id) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getMaxId() throws IOException {
		// TODO Auto-generated method stub
		int index = 0;
		List<Esfuerzo> esfuerzoList = null;
		esfuerzoList = getAll();
		
		if(esfuerzoList != null){
			if(!esfuerzoList.isEmpty()){
				Esfuerzo esfuerzo = esfuerzoList.get(
						esfuerzoList.size() -1);
				index = esfuerzo.getEsfuerzoId();
			}
		}
		return index;
	}
	
	@Override
	public List<Esfuerzo> getByIdProyecto(int idProy) throws IOException {
		// TODO Auto-generated method stub
		List<Esfuerzo> esfuerzoList = getAll();
		
		Iterator<Esfuerzo> iter = esfuerzoList.iterator();
		
		while(iter.hasNext()){
			Esfuerzo aux = iter.next();
			
			if(aux.getProyectoFk() != idProy){
				iter.remove();
			}
		}
		
		return esfuerzoList;
	}
	
	private Esfuerzo getEsfuerzoDeArray(String[] itemsLine){
		Esfuerzo esfuerzo = null;
		
		if(itemsLine[0] != null && itemsLine[1] != null && itemsLine[2] != null 
				&& itemsLine[3] != null && itemsLine[4] != null && itemsLine[5] != null
				&& itemsLine[6] != null && itemsLine[7] != null){
			esfuerzo = new Esfuerzo();
			
			esfuerzo.setEsfuerzoId(Integer.parseInt(
					itemsLine[0]));
			esfuerzo.setProyectoFk(Integer.parseInt(
					itemsLine[1]));
			esfuerzo.setObjetivo(itemsLine[2]);
			esfuerzo.setFechaInicio(
					FechaUtils.getFechaFromParse(itemsLine[3]));
			esfuerzo.setFechaTermino(
					FechaUtils.getFechaFromParse(itemsLine[4]));
			esfuerzo.setConsecutivo(Integer.parseInt(
					itemsLine[5]));
			esfuerzo.setFechaRegistro(
					FechaUtils.getFechaFromParse(itemsLine[6]));
			esfuerzo.setUsuarioRegistro(itemsLine[7]);
		}
		return esfuerzo;
	}
	
	private String getRowDeObjectEntity(Esfuerzo entity){
		String row = null;
		Calendar calFecReg = null, 
				calFecIni = null,
				calFecFin = null; 
		
		if(entity != null){
			
			calFecReg = FechaUtils.getFechaByDate(
					entity.getFechaRegistro());
			calFecIni = FechaUtils.getFechaByDate(
					entity.getFechaInicio());
			calFecFin = FechaUtils.getFechaByDate(
					entity.getFechaTermino());
			
			row = entity.getEsfuerzoId()
					+ Constantes.TOKEN_ROW_SEPARATOR + entity.getProyectoFk()
					+ Constantes.TOKEN_ROW_SEPARATOR + entity.getObjetivo()
					+ Constantes.TOKEN_ROW_SEPARATOR + calFecIni.get(Calendar.DAY_OF_MONTH)
						+ Constantes.TOKEN_DATE_SEPARATOR + (calFecIni.get(Calendar.MONTH)+1) 
						+ Constantes.TOKEN_DATE_SEPARATOR + calFecIni.get(Calendar.YEAR)
					+ Constantes.TOKEN_ROW_SEPARATOR + calFecFin.get(Calendar.DAY_OF_MONTH)
						+ Constantes.TOKEN_DATE_SEPARATOR + (calFecFin.get(Calendar.MONTH)+1) 
						+ Constantes.TOKEN_DATE_SEPARATOR + calFecFin.get(Calendar.YEAR)
					+ Constantes.TOKEN_ROW_SEPARATOR + entity.getConsecutivo()
					+ Constantes.TOKEN_ROW_SEPARATOR + calFecReg.get(Calendar.DAY_OF_MONTH)
						+ Constantes.TOKEN_DATE_SEPARATOR + (calFecReg.get(Calendar.MONTH)+1) 
						+ Constantes.TOKEN_DATE_SEPARATOR + calFecReg.get(Calendar.YEAR)
					+ Constantes.TOKEN_ROW_SEPARATOR + entity.getUsuarioRegistro();
		}
		
		return row;
	}

}
