package com.mx.smarttools.admin.common;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

public class MyDataModel<T> extends LazyDataModel<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<T> listItems;
	private Map<String,Object> filters;
	private int totalRows;

	public MyDataModel(List<T> listItems, Map<String, Object> filters) {
		super();
		this.listItems = listItems;
		this.filters = filters;
		this.totalRows = 0;
	}

	@Override
	public List<T> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
		// TODO Auto-generated method stub
		List<T> data = new ArrayList<T>();
		
		for (T item: listItems){
			boolean match = true;
			
			if(this.filters != null){
				for (Iterator<String> it = this.filters.keySet().iterator(); it.hasNext();) {
					
					try{
						String filterProperty = it.next();
						Object filterValue = this.filters.get(filterProperty);

//						String fieldValue = String.valueOf(item.getClass().getField(filterProperty).get(item));
						
						Field field = item.getClass().getDeclaredField(filterProperty);
						field.setAccessible(true);
						
						String fieldValue = String.valueOf(field.get(item));
						

						if(filterValue == null || fieldValue.startsWith(filterValue.toString())) {
							match = true;
						}else{
							match = false;
							break;
						}
					}catch (Exception e) {
            			// TODO Auto-generated catch block
            			e.printStackTrace();
            			match = false;
            		}
				}
			}
			
			if(match){
				data.add(item);
			}
		}
		
		//sort
        if(sortField != null) {
        	sortList(sortField);
        }

		//rowCount
        int dataSize = data.size();
        this.setRowCount(dataSize);
 
        //paginate
        if(dataSize > pageSize) {
            try {
                return data.subList(first, first + pageSize);
            }
            catch(IndexOutOfBoundsException e) {
                return data.subList(first, first + (dataSize % pageSize));
            }
        }
        else {
            return data;
        }
	}

	@Override
	public T getRowData(String rowKey) {
		// TODO Auto-generated method stub
		return super.getRowData(rowKey);
	}

	@Override
	public Object getRowKey(T object) {
		// TODO Auto-generated method stub
		return super.getRowKey(object);
	}
	
	public void sortList(String sortField){
		// Listo para sobreescribirse.
	}

	public List<T> getListItems() {
		return listItems;
	}

	public void setListItems(List<T> listItems) {
		this.listItems = listItems;
	}

	public Map<String, Object> getFilters() {
		return filters;
	}

	public void setFilters(Map<String, Object> filters) {
		this.filters = filters;
	}

	public int getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

}
