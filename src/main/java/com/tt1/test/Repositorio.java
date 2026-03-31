package com.tt1.test;

import java.util.List;

/**
 * Clase que gestiona el acceso y la persistencia de los datos de la aplicación.
 * Actúa como un repositorio que utiliza una implementación de {@link IDB}
 * para realizar operaciones sobre tareas ToDo y direcciones de correo.
 * @author izmartg
 * @version 1.0
 */
public class Repositorio {
    /** Referencia a la implementación de la base de datos. */
    private IDB db;

    /**
     * Constructor que inyecta la dependencia de la base de datos.
     * @param db Instancia que implementa la interfaz IDB.
     */
    Repositorio(IDB db){
        this.db = db;
    }

    // OPERACIONES DE TAREAS

    /**
     * Busca y devuelve una tarea específica del repositorio.
     * @param tarea Tarea de referencia.
     * @return El objeto ToDo encontrado o null si no existe.
     */
    public ToDo devuelveToDo(ToDo tarea) {
        return db.devuelveToDo(tarea);
    }

    /**
     * Marca una tarea como finalizada y actualiza su estado en la base de datos.
     * @param tarea Tarea que se desea completar.
     */
    public void completarToDo(ToDo tarea) {
        tarea.setCompletado(true);
        db.actualizaToDo(tarea);
    }

    /**
     * Registra una nueva tarea en el repositorio.
     * @param tarea Objeto ToDo a crear.
     */
    public void crearToDo(ToDo tarea) {
        db.crearToDo(tarea);
    }

    /**
     * Verifica si una tarea ya existe en el sistema.
     * @param tarea Tarea a comprobar.
     * @return true si la tarea está registrada.
     */
    public boolean contieneToDo(ToDo tarea) {
        return db.contieneToDo(tarea);
    }

    /**
     * Obtiene el listado de todas las tareas registradas.
     * @return Lista de objetos ToDo.
     */
    public List<ToDo> devolverToDos() {
        return db.devuelveToDos();
    }

    /**
     * Obtiene únicamente las tareas que han excedido su fecha límite.
     * @return Lista de tareas caducadas.
     */
    public List<ToDo> devolverCaducados() {
        return db.devuelveCaducados();
    }

    // OPERACIONES DE CORREOS

    /**
     * Añade una nueva dirección de correo al repositorio.
     * @param correo Dirección de email.
     */
    public void crearCorreo (String correo) {
        db.crearCorreo(correo);
    }

    /**
     * Comprueba si un correo electrónico ya está registrado.
     * @param correo Email a buscar.
     * @return true si el correo ya existe.
     */
    public boolean contieneCorreo(String correo) {
        return db.contieneCorreo(correo);
    }

    /**
     * Recupera la lista completa de correos electrónicos.
     * @return Lista de cadenas con los emails.
     */
    public List<String> devolverCorreos () {
        return db.devuelveCorreos();
    }
}