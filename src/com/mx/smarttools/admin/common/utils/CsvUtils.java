package com.mx.smarttools.admin.common.utils;

import java.util.List;

public class CsvUtils {

	private String delimitador;
	private List<String> renglonesList = null;
	private String[] items;

	public CsvUtils() {
		super();
		// TODO Auto-generated constructor stub
		delimitador = ",";
	}
	
	public static String[] getArrayStrFromRowLine(String row,String delimitador){
		String[] items = row.split(delimitador);
		return items;
	}

	public String getDelimitador() {
		return delimitador;
	}

	public void setDelimitador(String delimitador) {
		this.delimitador = delimitador;
	}

	public List<String> getRenglonesList() {
		return renglonesList;
	}

	public void setRenglonesList(List<String> renglonesList) {
		this.renglonesList = renglonesList;
	}

	public String[] getItems() {
		return items;
	}

	public void setItems(String[] items) {
		this.items = items;
	}
	
}
