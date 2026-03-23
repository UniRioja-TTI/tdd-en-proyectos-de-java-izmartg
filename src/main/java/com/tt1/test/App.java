package com.tt1.test;

import java.util.Date;
import java.util.Scanner;

public class App{
	private IDB db = new DBStub();
	private IMailer mailer = new MailerStub();
	private Repositorio repositorio = new Repositorio(db);
	private static Servicio servicio = new Servicio(repositorio, mailer);
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String nombre = "";
		String descripcion = "";
		Date fecha = null;
		int dia = 0;
		int mes = 0;
		int anyo = 0;
		int opcion = 1;
		
		while(opcion!=0) {
			System.out.println(
					"Elige una opción:\n"
					+ "		1. Crear un ToDo.\n"
					+ "		2. Agregar un correo electrónico.\n"
					+ " 	3. Marcar tarea como finalizada.\n"
					+ " 	4. Consultar ToDo's sin completar.\n"
					+ " 	0. Salir.");
			opcion = Integer.parseInt(sc.nextLine());
			switch (opcion) {
			case 1: {
				System.out.println("Introduzca un nombre: ");
				nombre = sc.nextLine();
				System.out.println("Introduzca la descripción");
				descripcion = sc.nextLine();
				System.out.println("Pasemos a la fecha de entrega:\n    Día:");
				dia = Integer.parseInt(sc.nextLine());
				System.out.println("    Mes:");
				mes = Integer.parseInt(sc.nextLine());
				System.out.println("    Anyo:");
				anyo = Integer.parseInt(sc.nextLine());
				java.time.LocalDate localDate = java.time.LocalDate.of(anyo, mes, dia);
				fecha = java.util.Date.from(localDate.atStartOfDay(java.time.ZoneId.systemDefault()).toInstant());
				
				servicio.crearToDo(new ToDo(nombre, descripcion, fecha, false));
				if(!servicio.existeToDo(new ToDo(nombre, null, null, false)))
					System.out.println("Los parámetros son incorrectos.");
				break;
			}
			case 2: {
				// servicio.crearCorreo();
				break;
			}
			case 3: {
				// servicio.completarToDo();
				break;
			}
			case 4: {
				// servicio.consultarToDos();
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + opcion);
			}
		}
	}
}