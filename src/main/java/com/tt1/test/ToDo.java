package com.tt1.test;

import java.util.Date;

public class ToDo {
	private String nombre;
	private String descripcion;
	private Date fecha_limite;
	private boolean completado;
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
}
