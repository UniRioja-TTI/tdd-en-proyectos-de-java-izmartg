package com.tt1.test;

import java.util.Date;

public class ToDo {
	private String nombre;
	private String descripcion;
	private Date fecha_limite;
	private boolean completado;

	ToDo(String nombre, String descripcion, Date fecha_limite, boolean completado) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.fecha_limite = fecha_limite;
		this.completado = completado;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Date getFecha_limite() {
		return fecha_limite;
	}
	public void setFecha_limite(Date fecha_limite) {
		this.fecha_limite = fecha_limite;
	}
	public boolean isCompletado() {
		return completado;
	}
	public void setCompletado(boolean completado) {
		this.completado = completado;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
	    if (o == null || getClass() != o.getClass()) return false;
	    
	    ToDo toDo = (ToDo) o;
	    
	    return nombre != null ? nombre.equals(toDo.nombre) : toDo.nombre == null;
	}
}
