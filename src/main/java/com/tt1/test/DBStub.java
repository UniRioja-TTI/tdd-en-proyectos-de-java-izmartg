package com.tt1.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Implementación de la interfaz IDB mediante el uso de listas.
 * Esta clase actúa como un "Stub" para simular el comportamiento de una base de datos
 * real durante las fases de desarrollo y pruebas TDD.
 * @author izmartg
 * @version 1.0
 */
public class DBStub implements IDB {
    private ArrayList<ToDo> tareas;
    private ArrayList<String> correos;

    /**
     * Constructor que inicializa las estructuras de datos en memoria.
     */
    DBStub() {
        tareas = new ArrayList<ToDo>();
        correos = new ArrayList<String>();
    }

    @Override
    public void crearToDo(ToDo tarea) {
        if(!contieneToDo(tarea))
            tareas.add(tarea);
    }

    @Override
    public ToDo devuelveToDo(ToDo tarea) {
        String nombre = tarea.getNombre();
        if(contieneToDo(tarea)){
            for (ToDo t: tareas) {
                if(t.getNombre().equals(nombre))
                    return t;
            }
        }
        return null;
    }

    @Override
    public void actualizaToDo(ToDo tarea) {
        if(contieneToDo(tarea)) {
            eliminaToDo(tarea);
            crearToDo(tarea);
        }
    }

    @Override
    public void eliminaToDo(ToDo tarea) {
        tareas.remove(tarea);
    }

    @Override
    public boolean contieneToDo(ToDo tarea) {
        return tareas.contains(tarea);
    }

    @Override
    public List<ToDo> devuelveToDos(){
        return tareas;
    }

    @Override
    public List<ToDo> devuelveCaducados() {
        List<ToDo> caducados = new ArrayList<>();
        Date hoy = new Date();
        for (ToDo todo : tareas) {
            if (!todo.isCompletado() && todo.getFecha_limite().before(hoy))
                caducados.add(todo);
        }
        return caducados;
    }

    @Override
    public void crearCorreo(String correo) {
        if(!correos.contains(correo))
            correos.add(correo);
    }

    @Override
    public boolean contieneCorreo(String correo) {
        return correos.contains(correo);
    }

    @Override
    public List<String> devuelveCorreos() {
        return correos;
    }
}