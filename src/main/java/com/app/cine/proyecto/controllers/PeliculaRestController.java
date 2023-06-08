package com.app.cine.proyecto.controllers;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.cine.proyecto.models.entity.Pelicula;
import com.app.cine.proyecto.models.service.PeliculaService;
import com.app.cine.proyecto.util.Util;


@RestController
@RequestMapping("/pelicula")
public class PeliculaRestController {

	@Autowired
	PeliculaService peliculaService;
	
	@GetMapping("/listar")
	public ResponseEntity<?> listar(){
		
		List<Pelicula> result = null;
		
		Map<String,Object> response = new LinkedHashMap<>();
		
		try {
			result = peliculaService.listar();
		}
		catch(Exception ex){
			response.put("estado", "ok");
			response.put("mensaje", "Excepciòn "+ex.getMessage());
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<List<Pelicula>>(result,HttpStatus.OK);
	}
	
	@PostMapping("/registrar")
	public ResponseEntity<?> registrar(@RequestBody Pelicula pelicula){
		
		Pelicula peliculaSave = null;
		
		Map<String,Object> response = new LinkedHashMap<>();
		
		
		
		try{
			
			Map<String,Object> validacion = Util.validarJson("/model/schema.json", pelicula);
			
			if(validacion.get("estado").equals("error")) {
				return new ResponseEntity<Map<String,Object>>(validacion,HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			peliculaSave = peliculaService.guardar(pelicula);
			
			if(peliculaSave == null) {
				response.put("estado","error");
				response.put("mensaje","Ocurrio un error al crear la pelicula");
				return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
			}
		}
		catch(Exception ex) {
			response.put("estado", "error");
			response.put("mensaje", "Excepcion "+ex.getMessage());
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("estado","ok");
		response.put("mensaje", "Se registro correctamente!");
		response.put("pelicula",peliculaSave);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
		
		
	}
	
	@PostMapping("/actualizar")
	public ResponseEntity<?> actualizar(@RequestBody Pelicula pelicula){
		
		Pelicula peliculaAct = null;
		
		Map<String,Object> response = new LinkedHashMap<>();
		
		try{
			
			Map<String,Object> validacion = Util.validarJson("/model/schema.json", pelicula);
			
			if(validacion.get("estado").equals("error")) {
				return new ResponseEntity<Map<String,Object>>(validacion,HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			peliculaAct = peliculaService.guardar(pelicula);
			
			if(peliculaAct == null) {
				response.put("estado","error");
				response.put("mensaje","Ocurrio un error al actualizar la pelicula");
				return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
			}
		}
		catch(Exception ex) {
			response.put("estado", "error");
			response.put("mensaje", "Excepcion "+ex.getMessage());
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("estado","ok");
		response.put("mensaje", "Pelicula actualizada correctamente!");
		response.put("pelicula", peliculaAct);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
		
		
	}
	
	@PostMapping("/eliminar/{id}")
	public ResponseEntity<?> eliminar(@PathVariable Integer id){
		
		
		Map<String,Object> response = new LinkedHashMap<>();
		
		try{
			if(peliculaService.eliminar(id) == false) {
				response.put("estado", "error");
				response.put("mensaje", "No existe id ingresado");
				
				return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
			}
			response.put("estado", "ok");
			response.put("mensaje", "Pelicula eliminada con exito");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
		}
		catch(Exception ex) {
			response.put("estado", "error");
			response.put("mensaje", "Excepcion "+ex.getMessage());
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
	
}
