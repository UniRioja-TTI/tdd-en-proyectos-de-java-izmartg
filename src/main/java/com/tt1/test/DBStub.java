package com.tt1.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DBStub implements IDB{
	private ArrayList<ToDo> tareas;
	private ArrayList<String> correos;
	
	DBStub() {
		tareas = new ArrayList<ToDo>();
		correos = new ArrayList<String>();
	}
	
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
	
	public List<ToDo> devuelveToDos(){
		return tareas;
	}
	
	public List<ToDo> devuelveCaducados() {
		List<ToDo> caducados = new ArrayList<>();
		Date hoy = new Date();
		
		for (ToDo todo : tareas) {
			if (!todo.isCompletado() && todo.getFecha_limite().before(hoy))
				caducados.add(todo);
		}
		
		return caducados;
	}
	
		// OPERACIONES DE CORREOS
	public void crearCorreo(String correo) {
		if(!correos.contains(correo))
			correos.add(correo);
	}
	
	public boolean contieneCorreo(String correo) {
		return correos.contains(correo);
	}

	@Override
	public List<String> devuelveCorreos() {
		return correos;
	}
}