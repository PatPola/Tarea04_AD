package com.accesoadatos.hibernate.tarea04;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.ForeignKey;

@Entity
public class Equipo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String numeroSerie;
	private Date fechaAlta;
	private String caracteristicas;
	// Relación muchos a uno con la entidad Aula, por defecto Lazzy mostrará equipos
	// y aulas
	@ManyToOne
	@JoinColumn(name = "aula_id", foreignKey = @ForeignKey(name = "Aula_FK"))
	private Aula aula;

	public Equipo() {
	}

	public Equipo(String numeroSerie, Date fechaAlta, String caracteristicas) {
		this.numeroSerie = numeroSerie;
		this.fechaAlta = fechaAlta;
		this.caracteristicas = caracteristicas;
	}

	// regionGetter setter
	public String getNumeroSerie() {
		return numeroSerie;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNumeroSerie(String numeroSerie) {
		this.numeroSerie = numeroSerie;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public String getCaracteristicas() {
		return caracteristicas;
	}

	public void setCaracteristicas(String caracteristicas) {
		this.caracteristicas = caracteristicas;
	}

	public Aula getAula() {
		return aula;
	}

	public void setAula(Aula aula) {
		this.aula = aula;
	}

}
