package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.accesoadatos.hibernate.tarea04.Aula;
import com.accesoadatos.hibernate.tarea04.Equipo;

public class Utilidades {
	static Scanner input = new Scanner(System.in);

	public static void muestraMenu() {

		System.out.println("""
				1.Listar aulas y equipos
				2.Insertar aula
				3.Borrar aula
				4.Modificar aula
				5.Insertar equipo en aula
				6.Buscar equipo por nº de serie
				7.Salir
				""");

	}
	public static int solicitaId() {
		int idAula = 0;
		try {
			idAula = input.nextInt();
			input.nextLine();
		} catch (InputMismatchException e) {
			System.out.println("no has introducido un número");
			input.nextLine();
		}

		return idAula;
	}

	   public static Date solicitarFecha() {
	   
	        boolean fechaValida = false;
	        Date fecha = null;
	        do {
	            System.out.println("Introduce la fecha (YYYY-MM-DD):");
	            String fechaStr = input.nextLine();
	            try {
	                fecha = new SimpleDateFormat("yyyy-MM-dd").parse(fechaStr);
	                fechaValida = true;
	            } catch (ParseException e) {
	                System.out.println("Error al parsear la fecha. Asegúrate de que esté en el formato correcto (YYYY-MM-DD).");
	            }
	        } while (!fechaValida);
	        return fecha;
	    }
	

}
