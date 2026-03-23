package com.tt1.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Year;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DBStubTest {
	private static IDB db = new DBStub();
	
	@BeforeEach
	void setUpBeforeClass() throws Exception {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Year.now().getValue()+1, Calendar.MARCH, 20, 16, 0, 0);
		Date fecha1 = calendar.getTime();
		calendar.set(2020, Calendar.MARCH, 20, 16, 0, 0);
		Date fecha2 = calendar.getTime();
		
		ToDo tarea1 = new ToDo(
				"Implementacion DBStub",
				"Hay que implementar DBStub.",
				fecha1,
				false
				);
		ToDo tarea2 = new ToDo(
				"Implementacion Repositorio",
				"Hay que implementar Repositorio.",
				fecha1,
				false
				);
		ToDo tarea3 = new ToDo(
				"Implementacion Servicio",
				"Hay que implementar Servicio.",
				fecha2,
				false
				);
		
		db.crearToDo(tarea1);
		db.crearToDo(tarea2);
		db.crearToDo(tarea3);
		db.crearCorreo("prueba@prueba.com");
		db.crearCorreo("nose@nose.com");
	}

	@Test
	void testCrearToDo() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2026, Calendar.JUNE, 20, 16, 0, 0);
		Date fecha = calendar.getTime();

		ToDo tarea = new ToDo(
				"Prueba",
				"Esto es una prueba.",
				fecha,
				false
				);
		
		db.crearToDo(tarea);
		
		assertTrue(db.contieneToDo(tarea));
	}

	@Test
	void testDevuelveToDo() {
		ToDo tarea1 = new ToDo("Implementacion DBStub", "", new Date(), false);
		ToDo tarea2 = new ToDo("Implementacion Repositorio", "", new Date(), false);
		ToDo tarea3 = new ToDo("Implementacion Servicio", "", new Date(), false);
		assertTrue(db.contieneToDo(tarea1));
		assertTrue(db.contieneToDo(tarea2));
		assertTrue(db.contieneToDo(tarea3));
	}

	@Test
	void testActualizaToDo() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2026, Calendar.JUNE, 20, 16, 0, 0);
		Date fecha = calendar.getTime();
		ToDo tarea1 = new ToDo(
				"Implementacion DBStub",
				"Hay que implementar y testear DBStub", 
				fecha, 
				false
				);
		db.actualizaToDo(tarea1);
		ToDo nuevaTarea1 = db.devuelveToDo(tarea1);
		assertTrue(nuevaTarea1.getNombre().equals(tarea1.getNombre()));
		assertTrue(nuevaTarea1.getDescripcion().equals(tarea1.getDescripcion()));
		assertTrue(nuevaTarea1.getFecha_limite().equals(tarea1.getFecha_limite()));
		assertTrue(nuevaTarea1.isCompletado()==(tarea1.isCompletado()));
	}

	@Test
	void testEliminaToDo() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2026, Calendar.JUNE, 20, 16, 0, 0);
		Date fecha = calendar.getTime();
		ToDo tarea1 = new ToDo(
				"Implementacion DBStub",
				"Hay que implementar y testear DBStub", 
				fecha, 
				false
				);
		db.eliminaToDo(tarea1);
		assertFalse(db.contieneToDo(tarea1));
	}
	
	@Test
	void testContieneToDo() {
		assertTrue(db.contieneToDo(new ToDo("Implementacion Repositorio", "", null, false)));
		assertFalse(db.contieneToDo(new ToDo("No esta", "", null, false)));
	}
	
	@Test
	void testDevuelveToDos() {
		List<ToDo> todos = db.devuelveToDos();

		assertTrue(todos.contains(new ToDo("Implementacion DBStub", "", null, false)));
		assertTrue(todos.contains(new ToDo("Implementacion Repositorio", "", null, false)));
		assertTrue(todos.contains(new ToDo("Implementacion Servicio", "", null, false)));
		assertFalse(todos.contains(new ToDo("No esta", "", null, false)));
	}
	
	@Test
	void testDevuelveCaducados() {
		List<ToDo> todos = db.devuelveCaducados();

		assertFalse(todos.contains(new ToDo("Implementacion DBStub", "", null, false)));
		assertFalse(todos.contains(new ToDo("Implementacion Repositorio", "", null, false)));
		assertTrue(todos.contains(new ToDo("Implementacion Servicio", "", null, false)));
	}

	@Test
	void testCrearCorreo() {
		String correo = "ejemplo@ejemplo.com";
		db.crearCorreo(correo);
		assertTrue(db.contieneCorreo(correo));
	}
	
	@Test
	void testContieneCorreo() {
		assertTrue(db.contieneCorreo("prueba@prueba.com"));
		assertTrue(db.contieneCorreo("nose@nose.com"));
		assertFalse(db.contieneCorreo("noesta@noesta.com"));
	}
}
