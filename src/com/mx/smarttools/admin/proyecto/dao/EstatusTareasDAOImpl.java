package com.mx.smarttools.admin.proyecto.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mx.smarttools.admin.common.beans.MyFileReader;
import com.mx.smarttools.admin.common.utils.FechaUtils;
import com.mx.smarttools.admin.proyecto.model.EstatusTareas;

@Component("estausTareaDAO")
public class EstatusTareasDAOImpl implements EstatusTareasDAO {

	@Autowired
	private MyFileReader fileReader;
	
	@Override
	public EstatusTareas add(EstatusTareas e) throws Exception {
		// TODO Auto-generated method stub
		String row = getRowDeObjectEntity(e);
		fileReader.writeFile(row);
		return e;
	}

	@Override
	public EstatusTareas update(int id, EstatusTareas e) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(EstatusTareas e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public EstatusTareas getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EstatusTareas> getAll() throws IOException {
		// TODO Auto-generated method stub
		fileReader.setPathFile("C:\\TestProyectosDB\\estatusTareas.csv");
		List<String> renglonesList = null;
		List<EstatusTareas> estatusTareasList = null;
		String[] items;
		int numberLine = 0;
		
		renglonesList = fileReader.readFile();
		if(renglonesList.size() > 1){
			estatusTareasList = new ArrayList<EstatusTareas>();
			
			for(String renglon: renglonesList){
				if(!renglon.isEmpty()){
					items = renglon.split("\\|");
//					System.out.println("ITEMS lengt:"+items.length);
					
					if(items.length == 5) {
						
						if(numberLine > 0) {
//							System.out.println("entra a tratar");
							EstatusTareas estatusTareas = getEntityDeArray(items);

							if(estatusTareas != null)
								estatusTareasList.add(estatusTareas);
						}
						numberLine++;
					}
				}
			}
		}
		renglonesList = fileReader.readFile();
		return estatusTareasList;
	}

	@Override
	public List<EstatusTareas> getByIdTarea(int id) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int getMaxEstatusTareasId() throws IOException {
		// TODO Auto-generated method stub
		int index = 0;
		
		List<EstatusTareas> estatusTareasList = null;
		estatusTareasList = getAll();
		
		if(estatusTareasList != null){
			if(!estatusTareasList.isEmpty()){
				EstatusTareas estatusTareaLast = estatusTareasList.get(
						estatusTareasList.size() -1);
				index = estatusTareaLast.getEstatusTareasId();
			}		
		}
			
		return index;
	}

	private String getRowDeObjectEntity(EstatusTareas entity){
		String row = null;
		Calendar calFecReg = null; 
		
		if(entity != null){
			calFecReg = FechaUtils.getFechaByDate(
					entity.getFechaRegistro());
			
			row = entity.getEstatusTareasId()
					+ "|" + entity.getTareaFk()
					+ "|" + entity.getEstatus()
					+ "|" + calFecReg.get(Calendar.DAY_OF_MONTH) 
						+ "," + (calFecReg.get(Calendar.MONTH)+1) 
						+ "," + calFecReg.get(Calendar.YEAR)
						+ "," + calFecReg.get(Calendar.HOUR_OF_DAY) + ":" + calFecReg.get(Calendar.MINUTE) + ":" + calFecReg.get(Calendar.SECOND)
					+ "|" + entity.getUsuarioRegistro();
		}
		
		return row;
	}
	
	private EstatusTareas getEntityDeArray(String[] itemsLine){
		EstatusTareas estatusTarea = null;
		
		if(itemsLine[0] != null && itemsLine[1] != null && itemsLine[2] != null 
				&& itemsLine[3] != null && itemsLine[4] != null){
			estatusTarea = new EstatusTareas();

			estatusTarea.setEstatusTareasId(Integer.parseInt(
					itemsLine[0]));
			estatusTarea.setTareaFk(Integer.parseInt(itemsLine[1]));
			estatusTarea.setEstatus(itemsLine[2]);
			estatusTarea.setFechaRegistro(
					FechaUtils.getFechaTimeFromParse(itemsLine[3]));
			estatusTarea.setUsuarioRegistro(itemsLine[4]);

		}
		return estatusTarea;
	}

	public MyFileReader getFileReader() {
		return fileReader;
	}

	public void setFileReader(MyFileReader fileReader) {
		this.fileReader = fileReader;
	}
}
