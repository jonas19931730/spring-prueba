package com.app.cine.proyecto.models.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.app.cine.proyecto.models.entity.SalaCine;

public interface SalaCineRepository extends CrudRepository<SalaCine,Integer> {

	@Query(value="SELECT "
			+ "	CASE "
			+ "	 WHEN COUNT(ps.id_pelicula) < 3 then 'Sala casi Vacia'"
			+ "	 WHEN COUNT(ps.id_pelicula) BETWEEN 3 and 5 then 'Sala casi Llena'"
			+ "	 WHEN COUNT(ps.id_pelicula) > 5 THEN 'Sala llena'"
			+ "	END AS resultado"
			+ " FROM pelicula_salacine ps"
			+ " inner join sala_cine sc on ps.id_sala_cine = sc.is_sala"
			+ " where sc.nombre = ?1",nativeQuery = true)
	public String estadoSala(String nombre);
	
}
