package com.tt1.test;

import java.util.List;

public class Repositorio {
	private IDB db;

	Repositorio(IDB db){
		this.db = db;
	}

		// OPERACIONES DE TAREAS
	public ToDo devuelveToDo(ToDo tarea) {
		return db.devuelveToDo(tarea);
	}
	public void completarToDo(ToDo tarea) {
		tarea.setCompletado(true);
		db.actualizaToDo(tarea);
	}
	public void crearToDo(ToDo tarea) {
		db.crearToDo(tarea);
	}
	public boolean contieneToDo(ToDo tarea) {
		return db.contieneToDo(tarea);
	}
	public List<ToDo> devolverToDos() {
		return db.devuelveToDos();
	}
	public List<ToDo> devolverCaducados() {
		return db.devuelveCaducados();
	}

		// OPERACIONES DE CORREOS
	public void crearCorreo (String correo) {
		db.crearCorreo(correo);
	}
    public boolean contieneCorreo(String correo) {
        return db.contieneCorreo(correo);
    }
    public List<String> devolverCorreos () {
		return db.devuelveCorreos();
	}
}
