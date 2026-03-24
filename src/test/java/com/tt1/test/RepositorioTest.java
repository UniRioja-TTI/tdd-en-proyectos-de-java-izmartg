package com.tt1.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Year;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RepositorioTest {

	private static IDB db = new DBStub();
	private static Repositorio rep = new Repositorio(db);

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
	void testDevuelveToDo() {
		ToDo busqueda = new ToDo("Implementacion DBStub","",null,false);
		ToDo busquedaRep = db.devuelveToDo(busqueda);
		ToDo busquedaDB = rep.devuelveToDo(busquedaRep);
		assertTrue(busquedaDB.getNombre().equals(busquedaRep.getNombre()));
		assertTrue(busquedaDB.getDescripcion().equals(busquedaRep.getDescripcion()));
		assertTrue(busquedaDB.getFecha_limite().equals(busquedaRep.getFecha_limite()));
		assertTrue(busquedaDB.isCompletado()==busquedaRep.isCompletado());
	}

	@Test
	void testCompletarToDo() {
		ToDo busqueda = new ToDo("Implementacion DBStub","",null,false);
		rep.completarToDo(busqueda);
		assertTrue(db.devuelveToDo(busqueda).isCompletado());
	}

	@Test
	void testCrearToDo() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Year.now().getValue()+1, Calendar.MARCH, 20, 16, 0, 0);

		String nombre = "Ejemplo";
		String descripcion = "ejemplo";
		Date fecha = calendar.getTime();
		boolean completado = false;

		ToDo ejemplo = new ToDo(nombre, descripcion, fecha, completado);
		rep.crearToDo(ejemplo);
		ToDo resultado = db.devuelveToDo(ejemplo);

		assertTrue(resultado.getNombre().equals(nombre));
		assertTrue(resultado.getDescripcion().equals(descripcion));
		assertTrue(resultado.getFecha_limite().equals(fecha));
		assertTrue(resultado.isCompletado()==completado);
	}

	@Test
	void testContieneToDo() {
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
				"Incorrecto",
				"No va",
				fecha2,
				false
				);

		assertTrue(rep.contieneToDo(tarea1));
		assertTrue(rep.contieneToDo(tarea2));
		assertFalse(rep.contieneToDo(tarea3));
	}

	@Test
	void testDevolverToDos() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Year.now().getValue()+1, Calendar.MARCH, 20, 16, 0, 0);
		Date fecha1 = calendar.getTime();
		calendar.set(2020, Calendar.MARCH, 20, 16, 0, 0);
		Date fecha2 = calendar.getTime();

		ToDo tarea1 = new ToDo("Implementacion DBStub", null, null, false);
		ToDo tarea2 = new ToDo("Implementacion Repositorio", null, null, false);
		ToDo tarea3 = new ToDo("No estoy", null, null, false);

		List<ToDo> tareas= rep.devolverToDos();
		assertTrue(tareas.contains(tarea1));
		assertTrue(tareas.contains(tarea2));
		assertFalse(tareas.contains(tarea3));

	}

	@Test
	void testDevolverCaducados() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Year.now().getValue()+1, Calendar.MARCH, 20, 16, 0, 0);
		Date fecha1 = calendar.getTime();
		calendar.set(2020, Calendar.MARCH, 20, 16, 0, 0);
		Date fecha2 = calendar.getTime();

		ToDo tarea1 = new ToDo("Implementacion DBStub", null, null, false);
		ToDo tarea2 = new ToDo("Implementacion Repositorio", null, null, false);
		ToDo tarea3 = new ToDo("Implementacion Servicio", null, null, false);

		List<ToDo> tareas = rep.devolverCaducados();

		assertFalse(tareas.contains(tarea1));
		assertFalse(tareas.contains(tarea2));
		assertTrue(tareas.contains(tarea3));
	}

	@Test
	void testCrearCorreo() {
		String cOriginal = "nuevo@nuevo.com";

		rep.crearCorreo(cOriginal);
		assertTrue(db.contieneCorreo(cOriginal));
	}

    @Test
    void testContieneCorreo() {
        assertTrue(rep.contieneCorreo("prueba@prueba.com"));
        assertFalse(rep.contieneCorreo("noestoy@noestoy.com"));
    }

	@Test
	void testDevolverCorreos() {
		List<String> correos = rep.devolverCorreos();

		assertTrue(correos.contains("prueba@prueba.com"));
		assertTrue(correos.contains("nose@nose.com"));
		assertFalse(correos.contains("noestoy@noestoy.com"));
	}
}
