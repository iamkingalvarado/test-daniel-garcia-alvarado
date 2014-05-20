package com.example.danigarciaalva.models;

import java.util.ArrayList;

/*
 * POJO ClientService
 * 
 * Es el objeto principal que regresa el servicio.
 * Se añaden todos los campos que componen el JSON para el correcto funcionamiento de GSON
 * 
 * 
 */
public class ClientService {

	private String codigo;
	private String mensaje;
	private ArrayList<Client> datos;
	public ClientService(String codigo, String mensaje, ArrayList<Client> datos) {
		super();
		this.codigo = codigo;
		this.mensaje = mensaje;
		this.datos = datos;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public ArrayList<Client> getDatos() {
		return datos;
	}
	public void setDatos(ArrayList<Client> datos) {
		this.datos = datos;
	}
}
