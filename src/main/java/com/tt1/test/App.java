package com.tt1.test;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Clase principal que actúa como interfaz de usuario por consola.
 * Gestiona el flujo del programa, permitiendo la interacción con el sistema
 * de tareas y correos a través de un menú de opciones.
 * @author izmartg
 * @version 1.0
 */
public class App {
    /** Base de datos simulada en memoria. */
    private static IDB db = new DBStub();

    /** Servicio de mensajería para las notificaciones. */
    private static IMailer mailer = new MailerStub();

    /** Repositorio que centraliza el acceso a los datos. */
    private static Repositorio repositorio = new Repositorio(db);

    /** Capa de servicio que contiene la lógica de negocio. */
    private static Servicio servicio = new Servicio(repositorio, mailer);

    /**
     * Método de entrada de la aplicación. Ejecuta el bucle principal del menú.
     * @param args Argumentos de la línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion = 1;

        while(opcion!=0) {
            System.out.println(
                "Elige una opción:\n"
                    + "       1. Crear un ToDo.\n"
                    + "       2. Agregar un correo electrónico.\n"
                    + "    3. Marcar tarea como finalizada.\n"
                    + "    4. Consultar ToDo's sin completar.\n"
                    + "    0. Salir.");
            try {
                opcion = Integer.parseInt(sc.nextLine());
                switch (opcion) {
                    case 1: crearToDo(sc); break;
                    case 2: crearCorreo(sc); break;
                    case 3: completarToDo(sc); break;
                    case 4: consultarToDos(sc); break;
                    case 0: System.out.println("Saliendo..."); break;
                    default: System.out.println("No se reconoce dicha opción.");
                }
            } catch (Exception e) {
                System.out.println("Error en la entrada de datos. Inténtelo de nuevo.");
            }
        }
    }

    /**
     * Muestra por consola el listado de todas las tareas registradas en el sistema.
     * @param sc Scanner para la lectura de datos (no requerido en este método).
     */
    private static void consultarToDos(Scanner sc) {
        List<ToDo> tareas = servicio.consultarToDos();
        for (ToDo tarea : tareas) {
            System.out.println(tarea.toString());
        }
    }

    /**
     * Solicita al usuario el nombre de una tarea para marcarla como completada.
     * @param sc Scanner para capturar el nombre de la tarea.
     */
    private static void completarToDo(Scanner sc) {
        System.out.println("Escribe el nombre de la tarea completada: ");
        String nombre = sc.nextLine();
        ToDo tarea = new ToDo(nombre, null, null, false);
        if(servicio.existeToDo(tarea)) {
            servicio.completarToDo(tarea);
            System.out.println("Se ha cambiado al estado completado.");
        } else {
            System.out.println("No existe una tarea con este nombre");
        }
    }

    /**
     * Solicita y registra una nueva dirección de correo electrónico en el sistema.
     * @param sc Scanner para capturar el email introducido por el usuario.
     */
    private static void crearCorreo(Scanner sc) {
        System.out.println("Escriba el correo a anyadir: ");
        String correo = sc.nextLine();
        servicio.crearCorreo(correo);
        if(!servicio.existeCorreo(correo))
            System.out.println("El correo introducido es inválido.");
    }

    /**
     * Gestiona el proceso de creación de una tarea pidiendo nombre, descripción y fecha.
     * Convierte la entrada de fecha (día, mes, año) a un objeto {@link Date}.
     * @param sc Scanner para capturar los múltiples parámetros de la tarea.
     */
    private static void crearToDo(Scanner sc) {
        System.out.println("Introduzca un nombre: ");
        String nombre = sc.nextLine();
        System.out.println("Introduzca la descripción: ");
        String descripcion = sc.nextLine();

        System.out.println("Pasemos a la fecha de entrega:\n    Día: ");
        int dia = Integer.parseInt(sc.nextLine());
        System.out.println("    Mes: ");
        int mes = Integer.parseInt(sc.nextLine());
        System.out.println("    Año: ");
        int anyo = Integer.parseInt(sc.nextLine());

        java.time.LocalDate localDate = java.time.LocalDate.of(anyo, mes, dia);
        Date fecha = java.util.Date.from(localDate.atStartOfDay(java.time.ZoneId.systemDefault()).toInstant());

        servicio.crearToDo(new ToDo(nombre, descripcion, fecha, false));
        if(!servicio.existeToDo(new ToDo(nombre, null, null, false)))
            System.out.println("Algunos de los parámetros son incorrectos y no se ha podido añadir.");
    }
}