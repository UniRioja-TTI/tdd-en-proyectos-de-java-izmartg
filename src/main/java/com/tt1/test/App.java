package com.tt1.test;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class App{
	private static IDB db = new DBStub();
	private static IMailer mailer = new MailerStub();
	private static Repositorio repositorio = new Repositorio(db);
	private static Servicio servicio = new Servicio(repositorio, mailer);

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
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
				crearToDo(sc);
				break;
			}
			case 2: {
				crearCorreo(sc);
				break;
			}
			case 3: {
				completarToDo(sc);
				break;
			}
			case 4: {
				consultarToDos(sc);
				break;
			}
			default:
                System.out.println("No se reconoce dicha opción.");
			}
		}
	}

    private static void consultarToDos(Scanner sc) {
        List<ToDo> tareas = servicio.consultarToDos();
        for (ToDo tarea : tareas) {
            System.out.println(
                tarea.toString()
            );
        }
    }

    private static void completarToDo(Scanner sc) {
        String nombre = "";
        System.out.println("Escribe el nombre de la tarea completada: ");
        nombre = sc.nextLine();
        ToDo tarea = new ToDo(nombre, null, null, false);
        if(servicio.existeToDo(tarea)) {
            servicio.completarToDo(tarea);
            System.out.println("Se ha cambiado al estado completado.");
        } else System.out.println("No existe una tarea con este nombre");
    }

    private static void crearCorreo(Scanner sc) {
        String correo = "";
        System.out.println("Escriba el correo a anyadir: ");
        correo = sc.nextLine();
        servicio.crearCorreo(correo);
        if(!servicio.existeCorreo(correo))
            System.out.println("El correo introduzido es inválido.");
    }

    private static void crearToDo(Scanner sc) {
        String nombre = "";
        String descripcion = "";
        Date fecha = null;
        int dia = 0;
        int mes = 0;
        int anyo = 0;

        System.out.println("Introduzca un nombre: ");
        nombre = sc.nextLine();
        System.out.println("Introduzca la descripción: ");
        descripcion = sc.nextLine();
        System.out.println("Pasemos a la fecha de entrega:\n    Día: ");
        dia = Integer.parseInt(sc.nextLine());
        System.out.println("    Mes: ");
        mes = Integer.parseInt(sc.nextLine());
        System.out.println("    Anyo: ");
        anyo = Integer.parseInt(sc.nextLine());
        java.time.LocalDate localDate = java.time.LocalDate.of(anyo, mes, dia);
        fecha = java.util.Date.from(localDate.atStartOfDay(java.time.ZoneId.systemDefault()).toInstant());

        servicio.crearToDo(new ToDo(nombre, descripcion, fecha, false));
        if(!servicio.existeToDo(new ToDo(nombre, null, null, false)))
            System.out.println("Algunos de los parámetros son incorrectos y no se ha podido añadir.");
    }
}