package com.mx.smarttools.admin.common.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FechaUtils {

	public static Date getFecha(int dia, int mes, int anio){
//		System.out.println("dia: "+dia+", mes: "+mes+", anio: "+anio);
		Calendar cld = Calendar.getInstance();
		cld.set(anio,mes-1,dia);
//		System.out.println("calendar date: "+cld.getTime());
		return cld.getTime();
	}
	
	/**
	 * @param parse
	 * @return
	 * Obtiene una fecha tipo Date de un token (String) con formato dd,m,yyyy
	 */
	public static Date getFechaFromParse(String token){
		Date fecha = null;
		String[] fechaArray = token.split(",");
		try{
			if(fechaArray.length == 3){
				fecha = FechaUtils.getFecha(
						Integer.parseInt(fechaArray[0]), 
						Integer.parseInt(fechaArray[1]), 
						Integer.parseInt(fechaArray[2]));
			}
		}catch(NumberFormatException exc){
			System.out.println("Error al convertir fecha algun elemento del token dd,m,yyyy no es numero. ");
		}
		return fecha;
	}
	
	public static Calendar getFechaByDate(Date date){
		Calendar cld = Calendar.getInstance();
		cld.setTime(date);
//		System.out.println("calendar date: "+cld.getTime());
		return cld;
	}
	
	public static String getStringDateAsFormat(Date date,String format){
		DateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(date);
	}
	
	/**
	 * @param token
	 * @return
	 * Obtiene una fecha tipo Date de un String separado por el token "," con formato d,m,yyyy,HH:mm:ss.mmm
	 */
	public static Date getFechaTimeFromParse(String token){
		Date fecha = null;
		String[] fechaArray = token.split(",");
		try{
			if(fechaArray.length == 3){
				fecha = FechaUtils.getFecha(
						Integer.parseInt(fechaArray[0]), 
						Integer.parseInt(fechaArray[1]), 
						Integer.parseInt(fechaArray[2]));
			}else if(fechaArray.length == 4){
				
			}
		}catch(NumberFormatException exc){
			System.out.println("Error al convertir fecha algun elemento del token dd,m,yyyy no es numero. ");
		}
		return fecha;
	}
	
	public static Date getFecha(int dia, int mes, int anio, String time){
		System.out.println("dia: "+dia+", mes: "+mes+", anio: "+anio+", hora: "+time);
		
		Calendar cld = Calendar.getInstance();
		String[] timeParse;
		cld.set(anio,mes-1,dia);
		if(time != null){
			timeParse =  time.split(":");

			cld.set(anio, mes, dia, Integer.parseInt(timeParse[0]), 
					Integer.parseInt(timeParse[1]), Integer.parseInt(timeParse[2]));
			System.out.println("calendar date: "+cld.getTime());
		}
		return cld.getTime();
	}
}
