package com.accesoadatos.hibernate.tarea04;

import util.MyEntityManager;
import util.Utilidades;

import javax.persistence.Query;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * 
 * @author Patricia Pola Caballero
 */
public class App {

	static Scanner input = new Scanner(System.in);
	static boolean encontrado = false;

	public static void main(String[] args) {
		//instancio clase 
        int idAula = 0;
		// Introduzco aulas y equipos
		MyEntityManager.introduceAulaEquipo();
		boolean salir = false;
		int opcion = 0;
		do {
			Utilidades.muestraMenu();
			try {

				opcion = input.nextInt();

				input.nextLine();
				switch (opcion) {
				case 1:
					MyEntityManager.listaAulasEquipos();
					break;
				case 2:
					 MyEntityManager.insertaAula();
					break;
				case 3:
					System.out.println("Introduce el ID del aula que quieres borrar:");
					idAula = Utilidades.solicitaId();
					MyEntityManager.borrarAula(idAula);

					break;
				case 4:
					System.out.println("Modificar aula");
					encontrado = false;
					do {
						System.out.println("Introduce el ID del aula que quieres modificar:");
						// solicita id
						idAula = Utilidades.solicitaId();
						// busca el aula indicada
						Aula aulaBuscada = MyEntityManager.em.find(Aula.class, idAula);
						if (aulaBuscada != null) {
							//
							MyEntityManager.modificaNombreAula(aulaBuscada);
							encontrado = true;
						} else {
							System.out.println("No se encontró un aula con el ID proporcionado. Inténtalo de nuevo.");
						}
					} while (!encontrado);

					break;
				case 5:
					// Solicitar los datos del equipo
					System.out.println("Introduce el número de serie del equipo:");
					String numeroSerie = input.nextLine();
					// solicita fecha
				Date fecha= 	Utilidades.solicitarFecha();
					// solicita características
					System.out.println("Introduce las características del equipo:");
					String caracteristicas = input.nextLine();

					// Muestra las aulas disponibles
					MyEntityManager.muestraAulas();

					do {
						// Solicitar el ID del aula al que se desea añadir el equipo
						System.out.println("Introduce el ID del aula al que quieres añadir el equipo:");
						idAula = Utilidades.solicitaId(); // Almacena el ID del aula solicitado

						// Verificar si el ID del aula es válido
						Aula aulaEquipo = MyEntityManager.em.find(Aula.class, idAula);

						// si el aula existe
						if (aulaEquipo != null) {
							// crea equipo y lo asigna al aula
							MyEntityManager.creaEquipo(numeroSerie, fecha, caracteristicas, aulaEquipo);
							encontrado = true; // Establece encontrado a true para salir del bucle
						} else {
							System.out.println("No se encontró un aula con el ID proporcionado. Inténtalo de nuevo.");
							encontrado = false; // Permite al bucle continuar solicitando el ID del aula
						}
					} while (!encontrado);
					break;
				case 6:
					System.out.println("Introduce el número de serie del equipo que quieres buscar");
					String numSerie = input.nextLine();
					MyEntityManager.listaEquipos(numSerie);
					break;
				case 7:
					salir = true;
					break;

				default:
					System.out.println("Las opciones son números del 1 al 7");
				}
			} catch (InputMismatchException e) {
				System.out.println("No has introducido un número");
				input.nextLine();
			}
		} while (!salir);

	}

}
