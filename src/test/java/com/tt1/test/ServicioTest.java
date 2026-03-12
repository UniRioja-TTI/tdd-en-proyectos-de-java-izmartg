package com.tt1.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Year;
import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ServicioTest {

	private static IDB db = new DBStub();
	private static Servicio serv = new Servicio();
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
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
		calendar.set(Year.now().getValue()+1, Calendar.MARCH, 20, 16, 0, 0);
		Date fecha = calendar.getTime();

			// CASO NORMAL
		ToDo ejemplo1 = new ToDo("Ejemplo1", "Esto es un ejemplo", fecha, false);
		serv.crearToDo(ejemplo1);
		assertTrue(db.contieneToDo(ejemplo1));
		
			// CASO SIN NOMBRE O NULL
		ToDo ejemplo2 = new ToDo("", "Esto es un ejemplo", fecha, false);
		ToDo ejemplo3 = new ToDo(null, "Esto es un ejemplo", fecha, false);
		serv.crearToDo(ejemplo2);
		serv.crearToDo(ejemplo3);
		assertFalse(db.contieneToDo(ejemplo2));
		assertFalse(db.contieneToDo(ejemplo3));
			
			// CASO FECHA NULL
		ToDo ejemplo4 = new ToDo("Ejemplo 4", "Esto es un ejemplo", null, false);
		serv.crearToDo(ejemplo4);
		assertFalse(db.contieneToDo(ejemplo4));
	}

	@Test
	void testCrearCorreo() {
		fail("Not yet implemented");
	}

	@Test
	void testCompletarToDo() {
		fail("Not yet implemented");
	}

	@Test
	void testConsultarToDos() {
		fail("Not yet implemented");
	}

}
