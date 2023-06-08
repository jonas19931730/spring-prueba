package com.app.cine.proyecto.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.app.cine.proyecto.models.entity.Pelicula;

public interface PeliculaRepository extends CrudRepository<Pelicula,Integer> {

	@Query("select p from Pelicula p order by p.idPelicula")
	public List<Pelicula> listar();
}
