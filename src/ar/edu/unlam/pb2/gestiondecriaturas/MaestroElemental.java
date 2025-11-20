package ar.edu.unlam.pb2.gestiondecriaturas;

import java.util.HashMap;

public class MaestroElemental {
	private String nombre;
	private Integer maestria;
	private Elementos afinidad;
	private HashMap<String, Criatura> coleccionDeCriaturas = new HashMap<String, Criatura>();
	
	public MaestroElemental(String nombre, Integer nivelDeMaestria, Elementos afinidadElemental) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return this.nombre;
	}

	public Integer getMaestria() {
		return this.maestria;
	}

	public Elementos getAfinidadElemental() {
		return this.afinidad;
	}

	public HashMap<String, Criatura> getColeccionDeCriaturas() {
		return this.coleccionDeCriaturas;
	}
}