package com.mx.smarttools.admin.common.utils;

import java.util.Date;

public class TestFechaUtils {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String examToken = "12,2,2016";
		System.out.println("Empieza test fecha token: "+examToken);
		Date examDate = null;
		examDate = FechaUtils.getFechaFromParse(examToken);
		System.out.println("El resultado del date fecha es: "+examDate);
	}
	

}
