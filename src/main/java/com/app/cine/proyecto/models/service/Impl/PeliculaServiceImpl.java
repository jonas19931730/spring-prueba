package com.app.cine.proyecto.models.service.Impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.cine.proyecto.models.entity.Pelicula;
import com.app.cine.proyecto.models.repository.PeliculaRepository;
import com.app.cine.proyecto.models.service.PeliculaService;

@Service
public class PeliculaServiceImpl implements PeliculaService {

	@Autowired
	PeliculaRepository pelDao;
	
	@Override
	public List<Pelicula> listar() {
		// TODO Auto-generated method stub
		return pelDao.listar();
	}

	@Override
	@Transactional
	public Pelicula guardar(Pelicula pelicula) {
		// TODO Auto-generated method stub
		return pelDao.save(pelicula);
	}

	@Override
	@Transactional
	public Pelicula actualizar(Pelicula pelicula) {
		 Pelicula peliculaPrevia = pelDao.findById(pelicula.getIdPelicula()).orElse(null);
		 
		 if(peliculaPrevia != null) {
			 return guardar(pelicula);
		 }
		 
		return null;
	}

	@Override
	@Transactional
	public boolean eliminar(Integer id) {
		
		Pelicula pelicula = pelDao.findById(id).orElse(null);
		
		if(pelicula == null) {
			return false;
		}
		
		try{
			pelDao.delete(pelicula);
			return true;
		}
		catch(Exception ex) {
			return false;
		}
		
	}

}
