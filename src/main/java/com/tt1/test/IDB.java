package com.tt1.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface IDB {
		// OPERACIONES DE TAREAS
	public void crearToDo(ToDo tarea);
	public ToDo devuelveToDo(ToDo tarea);
	public void actualizaToDo(ToDo tarea);
	public void eliminaToDo(ToDo tarea);
	public boolean contieneToDo(ToDo tarea);
	public List<ToDo> devuelveToDos();
	public List<ToDo> devuelveCaducados();
	
		// OPERACIONES DE CORREOS
	public void crearCorreo(String correo);
	public List<String> devuelveCorreos();
	public boolean contieneCorreo(String correo);
}
