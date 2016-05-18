package com.mx.smarttools.admin.pizarron.mbean;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.mx.smarttools.admin.proyecto.model.HistoriasUsuario;

@FacesConverter("historiaConverter")
public class HistoriasConverter implements Converter {

	private List<HistoriasUsuario> historiasRows;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		// TODO Auto-generated method stub
		if (value.isEmpty()) {
            return null;
        }
		
		EdicionMB data = context.getApplication().evaluateExpressionGet(context, "#{edicionMB}", EdicionMB.class);
		
		for(HistoriasUsuario historia: data.getHistoriasRows()){
			String nombreHst = historia.getNombreHistoria();
			
			if(nombreHst.equals(value)){
				return historia;
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		// TODO Auto-generated method stub
		if (value == null) {
            return "";
        }
		
		HistoriasUsuario historia = (HistoriasUsuario) value;
		
		String output = historia.getNombreHistoria();
		
		return output;
	}

	public List<HistoriasUsuario> getHistoriasRows() {
		return historiasRows;
	}

	public void setHistoriasRows(List<HistoriasUsuario> historiasRows) {
		this.historiasRows = historiasRows;
	}

}
