package com.mx.smarttools.admin.pizarron.service;

import java.util.List;
import com.mx.smarttools.admin.proyecto.model.Esfuerzo;

public interface EsfuerzoService {
	public Esfuerzo findEsfuerzo(int esfuerzoId);
	public void saveEsfuerzo(Esfuerzo esfuerzo) throws Exception;
	public void updateEsfuerzo(Esfuerzo esfuerzo) throws Exception;
	public void deleteEsfuerzo(Esfuerzo esfuerzo) throws Exception;
	public List<Esfuerzo> getEsfuerzos();
	public void saveOrUpdate(Esfuerzo esfuerzo) throws Exception;
	public int getLastId() throws Exception;
	public List<Esfuerzo> getEsfuerzosByIdProject(int projId) throws Exception;
}
