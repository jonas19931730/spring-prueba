package com.app.cine.proyecto.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "pelicula_salacine")
public class PeliculaSalaCine implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pelicula_sala")
	private Integer idPeliculaSala;

	@ManyToOne
	@JoinColumn(name = "id_sala_cine")
	private SalaCine salaCine;

	@ManyToOne
	@JoinColumn(name = "id_pelicula")
	private Pelicula pelicula;

	@Column(name = "fecha_publicacion")
	@Temporal(TemporalType.DATE)
	private Date fechaPublicacion;

	@Column(name = "fecha_fin")
	@Temporal(TemporalType.DATE)
	private Date fechaFin;

	public Integer getIdPeliculaSala() {
		return idPeliculaSala;
	}

	public void setIdPeliculaSala(Integer idPeliculaSala) {
		this.idPeliculaSala = idPeliculaSala;
	}

	public SalaCine getSalaCine() {
		return salaCine;
	}

	public void setSalaCine(SalaCine salaCine) {
		this.salaCine = salaCine;
	}

	public Pelicula getPelicula() {
		return pelicula;
	}

	public void setPelicula(Pelicula pelicula) {
		this.pelicula = pelicula;
	}

	public Date getFechaPublicacion() {
		return fechaPublicacion;
	}

	public void setFechaPublicacion(Date fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

}
