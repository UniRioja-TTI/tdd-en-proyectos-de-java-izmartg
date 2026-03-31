package com.tt1.test;

import java.util.List;

/**
 * Interfaz que define las operaciones permitidas sobre la base de datos de tareas y correos.
 * Proporciona un contrato estándar para la gestión de persistencia de objetos ToDo
 * y direcciones de correo electrónico.
 * @author izmartg
 * @version 1.0
 */
public interface IDB {

    /**
     * Registra una nueva tarea en el sistema si no existe previamente.
     * @param tarea Objeto ToDo que se desea almacenar.
     */
    public void crearToDo(ToDo tarea);

    /**
     * Recupera una tarea almacenada que coincida con la proporcionada.
     * @param tarea Tarea de referencia para la búsqueda.
     * @return El objeto ToDo completo almacenado, o null si no se encuentra.
     */
    public ToDo devuelveToDo(ToDo tarea);

    /**
     * Sustituye la información de una tarea existente por una nueva versión.
     * @param tarea Tarea con los datos actualizados.
     */
    public void actualizaToDo(ToDo tarea);

    /**
     * Elimina de forma permanente una tarea del sistema.
     * @param tarea Tarea que se desea borrar.
     */
    public void eliminaToDo(ToDo tarea);

    /**
     * Comprueba si una tarea específica ya está registrada en el sistema.
     * @param tarea Tarea a verificar.
     * @return true si la tarea existe, false en caso contrario.
     */
    public boolean contieneToDo(ToDo tarea);

    /**
     * Recupera el listado completo de todas las tareas almacenadas.
     * @return Una lista con todos los objetos ToDo.
     */
    public List<ToDo> devuelveToDos();

    /**
     * Filtra y devuelve las tareas que no han sido completadas y cuya fecha límite es anterior a hoy.
     * @return Lista de tareas pendientes y fuera de plazo.
     */
    public List<ToDo> devuelveCaducados();

    /**
     * Añade una nueva dirección de correo al listado de notificaciones.
     * @param correo Cadena de texto con la dirección de email.
     */
    public void crearCorreo(String correo);

    /**
     * Recupera todas las direcciones de correo almacenadas.
     * @return Lista de cadenas de texto con los correos registrados.
     */
    public List<String> devuelveCorreos();

    /**
     * Verifica si un correo electrónico ya forma parte del listado.
     * @param correo Dirección de email a buscar.
     * @return true si el correo ya está registrado.
     */
    public boolean contieneCorreo(String correo);
}