package com.mx.smarttools.admin.common.beans;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class TestFileReader {

	@Autowired
	MyFileReader fileReader;
	
		public static void main(String[] args) {
		
		final ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		final TestFileReader testFileReader = context.getBean(TestFileReader.class);
		testFileReader.listaArchivo();
		
//		String linea = "7|1|Motos Huasen|1680|28,2,2016";
//	    testFileReader.agregaLinea(linea);
		
		String nombreArchivo = "C:\\TestMotoDB\\proveedor.csv";
	    testFileReader.listaArchivo(nombreArchivo);
	    
	    
	}
	
	public void listaArchivo(){
		List<String> rows;
		
		try {
			rows = fileReader.readFile();
			
			if(!rows.isEmpty()){
		    	for(String linea: rows){
		    		System.out.println(linea);
		    	}
		    		
		    }else
		    	System.out.println("No tiene lineas el archivo");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void agregaLinea(String linea){
		try {
			fileReader.writeFile(linea);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void listaArchivo(String nombreArchivo){
		List<String> rows;
		
		try {
			fileReader.setPathFile(nombreArchivo);
			rows = fileReader.readFile();
			
			if(!rows.isEmpty()){
		    	for(String linea: rows){
		    		System.out.println(linea);
		    	}
		    		
		    }else
		    	System.out.println("No tiene lineas el archivo");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public MyFileReader getFileReader() {
		return fileReader;
	}

	public void setFileReader(MyFileReader fileReader) {
		this.fileReader = fileReader;
	}
}
