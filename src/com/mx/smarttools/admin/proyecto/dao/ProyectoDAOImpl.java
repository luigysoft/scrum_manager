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
import com.mx.smarttools.admin.proyecto.model.EstatusTareas;
import com.mx.smarttools.admin.proyecto.model.Proyecto;

@Component("proyectoDAO")
public class ProyectoDAOImpl implements ProyectoDAO {

	@Autowired
	private MyFileReader fileReader;

	@Override
	public Proyecto add(Proyecto t) throws Exception {
		// TODO Auto-generated method stub
		String row = getRowDeObjectEntity(t);
		fileReader.writeFile(row);
		return t;
	}

	@Override
	public Proyecto update(int id, Proyecto t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Proyecto t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Proyecto getById(int id) {
		// TODO Auto-generated method stub
		Proyecto proyecto = null;
		 
		try {
			List<Proyecto> proyectosList = getAll();
			Iterator<Proyecto> iter = proyectosList.iterator();
			
			while(iter.hasNext()){
				Proyecto aux = iter.next();
				if(aux.getProyectoId() == id){
					proyecto = aux;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return proyecto;
	}

	@Override
	public List<Proyecto> getAll() throws IOException {
		// TODO Auto-generated method stub
		fileReader.setPathFile("C:\\TestProyectosDB\\proyecto.csv");
		List<String> renglonesList = null;
		List<Proyecto> proyectosList = null;
		String[] items;
		int numberLine = 0;
		
		renglonesList = fileReader.readFile();
		if(renglonesList.size() > 1){
			proyectosList = new ArrayList<Proyecto>();
			
			for(String renglon: renglonesList){
				if(!renglon.isEmpty()){
					items = renglon.split("\\|");
//					System.out.println("ITEMS lengt:"+items.length);
					
					if(items.length == 7) {
						
						if(numberLine > 0) {
//							System.out.println("entra a tratar");
							Proyecto proyecto = getProyectoDeArray(items);

							if(proyecto != null)
								proyectosList.add(proyecto);
						}
						numberLine++;
					}
				}
			}
		}
		renglonesList = fileReader.readFile();
		return proyectosList;
	}
		
	private Proyecto getProyectoDeArray(String[] itemsLine){
		Proyecto proyecto = null;

		if(itemsLine[0] != null && itemsLine[1] != null && itemsLine[2] != null 
				&& itemsLine[3] != null && itemsLine[4] != null && itemsLine[5] != null
				&& itemsLine[6] != null){
			proyecto = new Proyecto();

			proyecto.setProyectoId(Integer.parseInt(
					itemsLine[0]));
			proyecto.setNombreProyecto(itemsLine[1]);
			proyecto.setObjetivo(itemsLine[2]);
			/*compra.setMonto(
						new BigDecimal(itemsLine[3]));*/
			proyecto.setFechaInicio(
					FechaUtils.getFechaFromParse(itemsLine[3]));
			proyecto.setFechaTermino(
					FechaUtils.getFechaFromParse(itemsLine[4]));
			proyecto.setFechaRegistro(
					FechaUtils.getFechaFromParse(itemsLine[5]));
			proyecto.setUsuarioRegistro(itemsLine[6]);

		}

		return proyecto;
	}

	@Override
	public int getMaxId() throws IOException {
		// TODO Auto-generated method stub
		int index = 0;
		
		List<Proyecto> proyectosList = null;
		proyectosList = getAll();
		
		if(proyectosList != null ){
			if(!proyectosList.isEmpty()){
				Proyecto proy = proyectosList.get(
						proyectosList.size() -1);
				index = proy.getProyectoId();
			}
		}
		return index;
	}
	
	private String getRowDeObjectEntity(Proyecto entity){
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
			
			row = entity.getProyectoId()
					+ Constantes.TOKEN_ROW_SEPARATOR + entity.getNombreProyecto()
					+ Constantes.TOKEN_ROW_SEPARATOR + entity.getObjetivo()
					+ Constantes.TOKEN_ROW_SEPARATOR + calFecIni.get(Calendar.DAY_OF_MONTH)
						+ Constantes.TOKEN_DATE_SEPARATOR + (calFecIni.get(Calendar.MONTH)+1) 
						+ Constantes.TOKEN_DATE_SEPARATOR + calFecIni.get(Calendar.YEAR)
					+ Constantes.TOKEN_ROW_SEPARATOR + calFecFin.get(Calendar.DAY_OF_MONTH)
						+ Constantes.TOKEN_DATE_SEPARATOR + (calFecFin.get(Calendar.MONTH)+1) 
						+ Constantes.TOKEN_DATE_SEPARATOR + calFecFin.get(Calendar.YEAR)
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
