package com.tt1.test;

import java.util.Date;

/**
 * Representa una tarea pendiente (ToDo) dentro del sistema de gestión.
 * Esta clase almacena la información básica de una tarea, incluyendo su nombre,
 * descripción, fecha límite y su estado de finalización.
 * @author izmartg
 * @version 1.0
 */
public class ToDo {
    private String nombre;
    private String descripcion;
    private Date fecha_limite;
    private boolean completado;

    /**
     * Constructor para crear una nueva instancia de una tarea.
     * @param nombre Nombre identificativo de la tarea.
     * @param descripcion Detalle o explicación de lo que se debe realizar.
     * @param fecha_limite Fecha máxima permitida para completar la tarea.
     * @param completado Estado inicial de la tarea (true si ya está terminada).
     */
    ToDo(String nombre, String descripcion, Date fecha_limite, boolean completado) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha_limite = fecha_limite;
        this.completado = completado;
    }

    /**
     * Obtiene el nombre de la tarea.
     * @return El nombre actual de la tarea.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Modifica el nombre de la tarea.
     * @param nombre Nuevo nombre para la tarea.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la descripción detallada de la tarea.
     * @return La descripción de la tarea.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Modifica la descripción de la tarea.
     * @param descripcion Nueva descripción detallada.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene la fecha límite establecida para la tarea.
     * @return Un objeto Date con la fecha límite.
     */
    public Date getFecha_limite() {
        return fecha_limite;
    }

    /**
     * Establece una nueva fecha límite para la tarea.
     * @param fecha_limite Nueva fecha límite deseada.
     */
    public void setFecha_limite(Date fecha_limite) {
        this.fecha_limite = fecha_limite;
    }

    /**
     * Indica si la tarea ha sido marcada como completada.
     * @return true si la tarea está lista, false en caso contrario.
     */
    public boolean isCompletado() {
        return completado;
    }

    /**
     * Cambia el estado de finalización de la tarea.
     * @param completado Nuevo estado (true para completada, false para pendiente).
     */
    public void setCompletado(boolean completado) {
        this.completado = completado;
    }

    /**
     * Compara esta tarea con otro objeto para determinar si son iguales.
     * Se considera que dos tareas son iguales si tienen el mismo nombre.
     * @param o Objeto a comparar.
     * @return true si los objetos son idénticos o tienen el mismo nombre.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ToDo toDo = (ToDo) o;

        return nombre != null ? nombre.equals(toDo.nombre) : toDo.nombre == null;
    }

    /**
     * Devuelve una representación en cadena de texto de la tarea.
     * Incluye todos los campos: nombre, descripción, fecha y estado.
     * @return String con la información detallada del objeto ToDo.
     */
    @Override
    public String toString() {
        return "ToDo{" +
            "nombre='" + nombre + '\'' +
            ", descripcion='" + descripcion + '\'' +
            ", fecha_limite=" + fecha_limite +
            ", completado=" + completado +
            '}';
    }
}