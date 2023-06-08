package com.app.cine.proyecto.models.service;

import java.util.List;

import com.app.cine.proyecto.models.entity.Pelicula;

public interface PeliculaService {

	public List<Pelicula> listar();
	
	public Pelicula guardar(Pelicula pelicula);
	
	public Pelicula actualizar(Pelicula pelicula);
	
	public boolean eliminar(Integer id);
}
