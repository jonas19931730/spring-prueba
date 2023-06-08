package com.app.cine.proyecto.models.service;

import java.util.Date;
import java.util.List;

import com.app.cine.proyecto.models.entity.Pelicula;

public interface PeliculaSalaCineService {

	public List<Pelicula> listarPorNombreIdentificadorSala(String nombre,Integer idsala);

	public List<Pelicula> listarPorFecha(Date fecha);
	
	public String salaCine(String nombresala);
}
