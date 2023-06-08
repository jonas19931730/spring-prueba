package com.app.cine.proyecto.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

	public static Date stringToDate(String date,String pattern) {
		
		Date dateResult = null;
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            dateResult = sdf.parse(date);
            System.out.println(date);
        } catch (ParseException e) {
            System.out.println("Error al convertir la cadena a fecha: " + e.getMessage());
            return null;
        }
        
        return dateResult;
	}
}
