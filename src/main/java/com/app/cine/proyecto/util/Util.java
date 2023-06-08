package com.app.cine.proyecto.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.fasterxml.jackson.databind.ObjectMapper;


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
	
	public static String arrayStringToString(List<String> array) {
		String result = "";
		
		for(String val:array) {
			result = result.concat(val);
		}
		
		return result;
	}
	
	public static Schema readJsonSchema(String ruta)  {
		try {
			JSONObject object = new JSONObject(new JSONTokener(Util.class.getResourceAsStream(ruta)));
			return SchemaLoader.load(object);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public static <T> Map<String,Object> validarJson(String ruta, T entidad) {
		
		Map<String,Object> response = new LinkedHashMap<>();
		
		List<String> errores = new ArrayList<>();
		
		try {
			Schema schema = readJsonSchema(ruta);
			String json = new ObjectMapper().writeValueAsString(entidad);
			schema.validate(new JSONObject(json));
			
			response.put("estado", "ok");
			return response;

		} catch (ValidationException e) {
			
			if (e.getCausingExceptions().size() > 0) {
				for (ValidationException ee : e.getCausingExceptions()) {
					errores.add(ee.getMessage());
					System.out.println(ee.toString());
				}
			} else {
				System.out.println(e.toString());
				errores.add(e.toString());
			}
			response.put("estado", "error");
			response.put("mensaje", arrayStringToString(errores));
			
			return response;
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			errores.add(e.getMessage());
			
			response.put("estado", "error");
			response.put("mensaje", arrayStringToString(errores));
			
			return response;
		} 

	}
}
