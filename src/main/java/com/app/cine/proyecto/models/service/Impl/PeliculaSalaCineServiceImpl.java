package com.app.cine.proyecto.models.service.Impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.cine.proyecto.models.entity.Pelicula;
import com.app.cine.proyecto.models.repository.PeliculaSalaCineRepository;
import com.app.cine.proyecto.models.repository.SalaCineRepository;
import com.app.cine.proyecto.models.service.PeliculaSalaCineService;

@Service
public class PeliculaSalaCineServiceImpl implements PeliculaSalaCineService {

	@Autowired
	PeliculaSalaCineRepository dao;

	@Autowired
	SalaCineRepository salaDao;
	
	@Override
	public List<Pelicula> listarPorNombreIdentificadorSala(String nombre, Integer idsala) {
		// TODO Auto-generated method stub
		return dao.buscarPorNombreIdSala(nombre, idsala);
	}

	@Override
	public List<Pelicula> listarPorFecha(Date fecha) {
		// TODO Auto-generated method stub
		return dao.listarPorFecha(fecha);
	}

	@Override
	public String salaCine(String nombresala) {
		// TODO Auto-generated method stub
		
		String estado = salaDao.estadoSala(nombresala);
		
		if(estado == null) {
			return "Sin peliculas";
		}
		
		return estado;
	}
	
}
