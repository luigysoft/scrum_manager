package com.mx.smarttools.admin.pizarron.mbean.lazy;

import java.util.List;
import java.util.Map;

import com.mx.smarttools.admin.common.MyDataModel;
import com.mx.smarttools.admin.proyecto.model.Proyecto;

public class ProyectoDataModel extends MyDataModel<Proyecto> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProyectoDataModel(List<Proyecto> listItems, Map<String, Object> filters) {
		super(listItems, filters);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Proyecto getRowData(String rowKey) {
		// TODO Auto-generated method stub
		for(Proyecto proy : getListItems()) {
            if(proy.getProyectoId() == Integer.parseInt(rowKey))
                return proy;
        }
 
        return null;
	}

	@Override
	public Object getRowKey(Proyecto object) {
		// TODO Auto-generated method stub
		return Integer.valueOf(object.getProyectoId());
	}

}
