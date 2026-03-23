package com.tt1.test;

import java.util.Date;
import java.util.List;

public class Servicio {
	private static Repositorio repo;
	private static IMailer mailer;
	public Servicio(Repositorio repo, IMailer mailer) {
		this.repo = repo;
		this.mailer = mailer;
	}
	
	public void crearToDo(ToDo tarea) {
		String nombre = tarea.getNombre();
		Date fecha = tarea.getFecha_limite();
		
		if(nombre!=null && !nombre.isEmpty() && fecha!=null) 
			repo.crearToDo(tarea);
	}
	
	public boolean existeToDo(ToDo tarea) {
		return repo.contieneToDo(tarea);
	}
	
	public void crearCorreo(String correo) {
		if(correo!=null && !correo.isEmpty()) {
			repo.crearCorreo(correo);
		}
	}
	public void completarToDo(ToDo tarea) {
		if(existeToDo(tarea))
			repo.completarToDo(tarea);
	}
	public List<ToDo> consultarToDos() {
		return repo.devolverToDos();
	}
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
