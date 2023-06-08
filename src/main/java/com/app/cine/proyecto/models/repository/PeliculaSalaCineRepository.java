package com.app.cine.proyecto.models.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.app.cine.proyecto.models.entity.Pelicula;
import com.app.cine.proyecto.models.entity.PeliculaSalaCine;

public interface PeliculaSalaCineRepository extends CrudRepository<PeliculaSalaCine,Integer> {

	@Query("select ps.pelicula from PeliculaSalaCine ps where ps.pelicula.nombre = ?1 and ps.salaCine.idSala = ?2 order by ps.idPeliculaSala")
	public List<Pelicula> buscarPorNombreIdSala(String nombre,Integer idsala);
	
	@Query("select ps.pelicula from PeliculaSalaCine ps where ps.fechaPublicacion = ?1")
	public List<Pelicula> listarPorFecha(Date fecha);
	
}
