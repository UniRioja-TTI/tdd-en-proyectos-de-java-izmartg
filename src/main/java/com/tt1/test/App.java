package com.tt1.test;

import java.util.Scanner;

public class App{
	public static void main(String[] args) {
		Servicio servicio = new Servicio();
		Scanner sc = new Scanner(System.in);
		int opcion = 1;
		
		while(opcion!=0) {
			System.out.println(
					"Elige una opción:\n"
					+ "		1. Crear un ToDo."
					+ "		2. Agregar un correo electrónico."
					+ " 	3. Marcar tarea como finalizada."
					+ " 	4. Consultar ToDo's sin completar."
					+ " 	0. Salir.");
			opcion = Integer.parseInt(sc.nextLine());
			switch (opcion) {
			case 1: {
				servicio.crearToDo(servicio);
				break;
			}
			case 2: {
				servicio.crearCorreo(servicio);
				break;
			}
			case 3: {
				servicio.completarToDo(servicio);
				break;
			}
			case 4: {
				servicio.consultarToDos(servicio);
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + opcion);
			}
		}
	}
}