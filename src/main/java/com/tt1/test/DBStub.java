package com.tt1.test;

import java.util.ArrayList;

public class DBStub {
	private ArrayList<ToDo> tareas;
	private ArrayList<String> correos;
	
		// OPERACIONES DE TAREAS
	public void crearToDo(ToDo tarea) {
		if(!contieneToDo(tarea))
			tareas.add(tarea);
	}
	
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
	
	public void actualizaToDo(ToDo tarea) {
		if(contieneToDo(tarea)) {
			eliminaToDo(tarea);
			crearToDo(tarea);
		}
	}
	
	public void eliminaToDo(ToDo tarea) {
		tareas.remove(tarea);
	}
	
	public boolean contieneToDo(ToDo tarea) {
		return tareas.contains(tarea);
	}
	
		// OPERACIONES DE CORREOS
	public void crearCorreo(String correo) {
		if(!correos.contains(correo))
			correos.add(correo);
	}
}