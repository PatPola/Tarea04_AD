package com.accesoadatos.hibernate.tarea04;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Aula {
	// Genera automáticamente IDs únicos, se delega en la bd
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nombre;
	/**
	 * la opción CascadeType.ALL incluye la operación de eliminación
	 * (CascadeType.REMOVE). Por lo tanto, si se elimina una instancia de Aula,
	 * todas las instancias de Equipo asociadas también se eliminarán.
	 * 
	 * orphanRemoval = true , indica que si un equipo ya no está asociado
	 * con ninguna aula después de que se realice una operación de actualización en
	 * la entidad Aula, el equipo se eliminará automáticamente de la base de datos.
	 */
	// Relación uno a muchos con la entidad Equipo
	//mappedBy = "aula" indica que la relación es bidireccional y que el campo aula en la clase Equipo mapea esta relación.
	@OneToMany(mappedBy = "aula", cascade = CascadeType.ALL, orphanRemoval = true) // mappedBy indica el nombre del
																		// campo en la entidad Equipo que
																					// mapea esta relación
	//Aula y Equipo es bidireccional, ya que ambas clases tienen referencias a la otra.
	private List<Equipo> equipos = new ArrayList<>();

	public Aula() {
	}

	public Aula(String nombre) {
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Equipo> getEquipos() {
		return equipos;
	}

	public void setEquipos(List<Equipo> equipos) {
		this.equipos = equipos;
	}

//
	public void insertarAula(Equipo equipo) {
		equipos.add(equipo);// añado el equipo a la lista
		equipo.setAula(this);// asigno el aula al objeto equipo

	}

}
