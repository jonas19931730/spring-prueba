package com.app.cine.proyecto.controllers;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.cine.proyecto.models.entity.Pelicula;
import com.app.cine.proyecto.models.service.PeliculaSalaCineService;
import com.app.cine.proyecto.util.Util;

@RestController
@RequestMapping("/registros")
public class PeliculaSalaCineRestController {

	@Autowired
	PeliculaSalaCineService pelService;
	
	@GetMapping("/listarPorFecha/{fecha}")
	public ResponseEntity<?> listarPorFecha(@PathVariable String fecha){
		
		List<Pelicula> result = null;
		
		Map<String,Object> response = new LinkedHashMap<>();
		
		try {
			
			result = pelService.listarPorFecha(Util.stringToDate(fecha, "yyyy-MM-dd"));
			
			if(result.isEmpty()) {
				response.put("estado","warning");
				response.put("mensaje", "No hay datos");
				return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
			}
		}
		catch(Exception ex){
			response.put("estado", "ok");
			response.put("mensaje", "Excepciòn "+ex.getMessage());
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("estado", "ok");
		response.put("cantidad", result.size());
		response.put("data", result);
		
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
	
	@GetMapping("/listarPorNombre/{nombre}/{idsala}")
	public ResponseEntity<?> listarPorNombreIdSala(@PathVariable String nombre,@PathVariable Integer idsala){
		
		List<Pelicula> result = null;
		
		Map<String,Object> response = new LinkedHashMap<>();
		
		try {
			result = pelService.listarPorNombreIdentificadorSala(nombre, idsala);
			
			if(result.isEmpty()) {
				response.put("estado","warning");
				response.put("mensaje", "No hay datos");
				return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
			}
		}
		catch(Exception ex){
			response.put("estado", "ok");
			response.put("mensaje", "Excepciòn "+ex.getMessage());
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("estado", "ok");
		response.put("cantidad", result.size());
		response.put("data", result);
		
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
	
	@GetMapping("/estadoSala/{nombre}")
	public ResponseEntity<?> estadoSala(@PathVariable String nombre){
		
		String result = null;
		
		Map<String,Object> response = new LinkedHashMap<>();
		
		try {
			result = pelService.salaCine(nombre);
			
			if(result.isEmpty()) {
				response.put("estado","warning");
				response.put("mensaje", "No hay datos");
				return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
			}
		}
		catch(Exception ex){
			response.put("estado", "ok");
			response.put("mensaje", "Excepciòn "+ex.getMessage());
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("estado", "ok");
		response.put("data", result);
		
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
}
