package com.tt1.test;

import java.util.Date;
import java.util.List;

/**
 * Clase de servicios que coordina la lógica de negocio de la aplicación.
 * Se encarga de validar los datos antes de persistirlos en el {@link Repositorio}
 * y de gestionar las notificaciones automáticas a través del {@link IMailer}.
 * @author izmartg
 * @version 1.0
 */
public class Servicio {
    /** Repositorio para la gestión de datos. */
    private static Repositorio repo;

    /** Servicio de mensajería para notificaciones. */
    private static IMailer mailer;

    /**
     * Constructor que inicializa los servicios necesarios.
     * @param repo Instancia del repositorio de datos.
     * @param mailer Instancia del servicio de envío de correos.
     */
    public Servicio(Repositorio repo, IMailer mailer) {
        this.repo = repo;
        this.mailer = mailer;
    }

    /**
     * Crea una nueva tarea tras validar que el nombre y la fecha no son nulos.
     * Al finalizar, dispara el proceso de notificación de tareas caducadas.
     * @param tarea Objeto ToDo con la información de la tarea a crear.
     */
    public void crearToDo(ToDo tarea) {
        String nombre = tarea.getNombre();
        Date fecha = tarea.getFecha_limite();

        if(nombre!=null && !nombre.isEmpty() && fecha!=null)
            repo.crearToDo(tarea);

        enviarCorreos();
    }

    /**
     * Verifica la existencia de una tarea y actualiza el estado de notificaciones.
     * @param tarea Tarea a buscar.
     * @return true si la tarea existe en el repositorio.
     */
    public boolean existeToDo(ToDo tarea) {
        enviarCorreos();
        return repo.contieneToDo(tarea);
    }

    /**
     * Registra un nuevo correo electrónico tras validar que la cadena no está vacía.
     * @param correo Dirección de correo a registrar.
     */
    public void crearCorreo(String correo) {
        if(correo!=null && !correo.isEmpty()) {
            repo.crearCorreo(correo);
        }
        enviarCorreos();
    }

    /**
     * Comprueba si un correo está registrado y actualiza el estado de notificaciones.
     * @param correo Email a verificar.
     * @return true si el correo ya existe.
     */
    public boolean existeCorreo(String correo) {
        enviarCorreos();
        return repo.contieneCorreo(correo);
    }

    /**
     * Marca una tarea como completada si esta existe previamente.
     * @param tarea Tarea a finalizar.
     */
    public void completarToDo(ToDo tarea) {
        if(existeToDo(tarea))
            repo.completarToDo(tarea);
    }

    /**
     * Recupera el listado de todas las tareas y actualiza las notificaciones.
     * @return Lista de todos los objetos ToDo.
     */
    public List<ToDo> consultarToDos() {
        enviarCorreos();
        return repo.devolverToDos();
    }

    /**
     * Método interno que gestiona el envío masivo de correos de advertencia.
     * Cruza la lista de tareas caducadas con la lista de correos registrados
     * para enviar una notificación por cada tarea fuera de plazo a cada usuario.
     */
    private void enviarCorreos() {
        List<ToDo> toDos = repo.devolverCaducados();
        List<String> emails = repo.devolverCorreos();

        for (String email : emails) {
            for(ToDo toDo : toDos) {
                this.mailer.enviaCorreo(email, "Se ha caducado la tarea " + toDo.getNombre());
            }
        }
    }
}