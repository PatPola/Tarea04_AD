package util;

import com.accesoadatos.hibernate.tarea04.Aula;
import com.accesoadatos.hibernate.tarea04.Equipo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * hibernate.hbm2ddl.auto
 * create: Esta opción indica a Hibernate que debe crear el esquema de la base
 * de datos desde cero cada vez que se inicie la aplicación. Esto significa que
 * Hibernate eliminará y recreará todas las tablas en la base de datos según las
 * anotaciones de las clases de entidad. Esto es útil durante el desarrollo,
 * pero no se recomienda para entornos de producción, ya que puede provocar la
 * pérdida de datos existentes.
 * 
 * update: Con esta opción, Hibernate actualizará el esquema de la base de datos
 * según las anotaciones de las clases de entidad. Hibernate agregará nuevas
 * tablas, columnas y restricciones que estén definidas en las clases de
 * entidad, pero no eliminará o modificará las tablas y columnas existentes en
 * la base de datos. Sin embargo, esta opción puede no ser perfecta en todos los
 * casos y puede causar problemas si hay cambios complejos en el esquema.
 * 
 * validate: Hibernate solo validará el esquema de la base de datos con las
 * anotaciones de las clases de entidad, pero no realizará cambios en la base de
 * datos. Si hay discrepancias entre el esquema de la base de datos y las
 * anotaciones de las clases de entidad, Hibernate lanzará una excepción.
 * 
 * none: Con esta opción, Hibernate no realizará ninguna acción en el esquema de
 * la base de datos. La validación y la actualización del esquema deben
 * realizarse manualmente por el desarrollador.
 */
public class MyEntityManager {
// instancia de EntityManager, que se utilizará para interactuar con la base de datos utilizando JPA (Java Persistence API).
	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("tarea04");
	public static EntityManager em = (EntityManager) emf.createEntityManager();
	static Scanner input = new Scanner(System.in);
	static boolean encontrado = false;
	static Query query;

	public static void listaEquipos(String numSerie) {
		// creo el query para asignar el parámetro numSerie
		query = em.createQuery("FROM Equipo e WHERE e.numeroSerie = :numSerie");
		query.setParameter("numSerie", numSerie);
		// busca en la lista de equipos
		List<Equipo> equipo = (List<Equipo>) query.getResultList();
		if (!equipo.isEmpty()) {
			// instancio equipo para obtener el primer elemento
			Equipo equipoAbuscar = equipo.get(0);
			// Muestra equipo
			System.out.println("Equipo encontrado");
			System.out.println("Id: " + equipoAbuscar.getId() + " -Nº de serie: " + equipoAbuscar.getNumeroSerie()
					+ " -Fecha alta: " + equipoAbuscar.getFechaAlta() + " -Características: "
					+ equipoAbuscar.getCaracteristicas());
			// Muestra nombre del aula a la que pertenece
			System.out.println("Aula: " + equipoAbuscar.getAula().getNombre());
		} else {
			System.out.println("No se ha encontrado el equipo con numero de serie " + numSerie);
		}
	}

	public static void creaEquipo(String numeroSerie, Date fechaAlta, String caracteristicas, Aula aulaEquipo) {
		// Creo el equipo
		Equipo equipo = new Equipo(numeroSerie, fechaAlta, caracteristicas);
		// Inicio transacción
		em.getTransaction().begin();
		// inserto equipo en el aula
		aulaEquipo.insertarAula(equipo);
		// confirmo transacción
		em.getTransaction().commit();
		System.out.println("Equipo añadido correctamente al aula: " + aulaEquipo.getNombre());
	}

	public static void muestraAulas() {

		// Mostrar todas las aulas disponibles
		System.out.println("Aulas disponibles:");
		// creo el query para buscar
		query = em.createQuery("FROM Aula", Aula.class);
		// obtengo la lista de resultados
		List<Aula> aulasDisponibles = (List<Aula>) query.getResultList();
		for (Aula aulasD : aulasDisponibles) {
			System.out.println("ID: " + aulasD.getId() + ", Nombre: " + aulasD.getNombre());
		}
	}

	public static void modificaNombreAula(Aula aulaBuscada) {
		System.out.println("Vas a modificar el aula " + aulaBuscada.getNombre() + " Introduce el nuevo nombre");
		String nuevoNombre = input.nextLine();
		aulaBuscada.setNombre(nuevoNombre);
		// realizamos transacción
		em.getTransaction().begin();
		// fuerza a JPA a enviar las instrucciones SQL
		em.flush();
		// confimo transacción
		em.getTransaction().commit();

		System.out.println("Aula  " + aulaBuscada.getId() + " ahora se llama " + aulaBuscada.getNombre());
	}

	public static int borrarAula(int idAula) {
		System.out.println("Borrar aula");
		encontrado = false;

		do {
			// busca el aula indicada
			Aula aulaBuscada = MyEntityManager.em.find(Aula.class, idAula);
			if (aulaBuscada != null) {
				encontrado = true;
				try {
					// Eliminar el aula encontrada
					MyEntityManager.em.getTransaction().begin();
					// elimino el aula
					MyEntityManager.em.remove(aulaBuscada);
					// confimo eliminación
					MyEntityManager.em.getTransaction().commit();
					System.out.println("Aula id: " + idAula + " - " + aulaBuscada.getNombre() + " Eliminada");
				}catch(Exception e) {
					em.getTransaction().rollback();
					System.out.println("Error al eliminar el aula: " + e.getMessage());
				}
			} else {
				System.out.println("No se encontró un aula con el ID proporcionado. Inténtalo de nuevo:");
				idAula = Utilidades.solicitaId();
			}
		} while (!encontrado);

		return idAula;
	}

	public static void  insertaAula() {
		String nombreAula;
		System.out.println("INSERTAR AULA");
		System.out.println("Introduce nombre del aula");
		nombreAula = input.nextLine();
		Aula aula = new Aula();
		// establezco el nombre recibido
		aula.setNombre(nombreAula);
		try{
			// inicio transacción
			em.getTransaction().begin();
			// registra el objeto aula en el contexto de persistencia, se programará para
			// ser insertado en la base de datos en la siguiente transacción.
			em.persist(aula);
			em.flush();
			// confirmo transacción
			em.getTransaction().commit();
			System.out.println("\nEl aula " + nombreAula + " ha sido insertado correctamente. ");
		}catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("Error al insertar aula: " + e.getMessage());

		}

    }

	public static void listaAulasEquipos() {
		System.out.println("Listado de aulas y sus equipos");
		// creo query
		query = (Query) em.createQuery("FROM Aula", Aula.class);
		// obtengo lista
		List<Aula> aulas = (List<Aula>) query.getResultList();
		if (!aulas.isEmpty()) {
			for (Aula aula : aulas) {
				System.out.println("\nAula: " + aula.getId() + ". " + aula.getNombre() + "\n");
				for (Equipo equipo : aula.getEquipos()) {
					System.out.println(" -Equipo id: " + equipo.getId() + " serialNumber: " + equipo.getNumeroSerie()
							+ " Características: " + equipo.getCaracteristicas() + "\n");
				}
			}
		} else {
			System.out.println("No hay aulas");

		}
	}

	public static void introduceAulaEquipo() {
		// instancia del calendario
		Calendar calendar = Calendar.getInstance();
		calendar.set(2023, Calendar.DECEMBER, 01);
		// creacion de equipos
		Equipo equipo1 = new Equipo("a1234", calendar.getTime(), "i7");
		calendar.set(2024, 01, 01);
		Equipo equipo2 = new Equipo("b1235", calendar.getTime(), "i5");
		calendar.set(2022, 05, 10);
		Equipo equipo3 = new Equipo("c1334", calendar.getTime(), "i3");

		// crear aulas y equipos
		Aula aula1 = new Aula("bases de datos");
		Aula aula2 = new Aula("acceso a datos");
		// Asignamos los equipos a las aulas y aulas a los equipos
		aula1.insertarAula(equipo1);
		aula2.insertarAula(equipo2);
		aula2.insertarAula(equipo3);
		// inicio transacción
		em.getTransaction().begin();
		// registro objetos
		em.persist(aula1);
		em.persist(aula2);
		// fuerza a JPA a enviar las instrucciones SQL

		em.flush();
		// confirmo transacción
		em.getTransaction().commit();
	}

}
