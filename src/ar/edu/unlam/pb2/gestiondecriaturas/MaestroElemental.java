package ar.edu.unlam.pb2.gestiondecriaturas;

import java.util.HashMap;
import java.util.Objects;

public class MaestroElemental {
	private String nombre;
	private Integer maestria;
	private Elementos afinidad;
	private HashMap<String, Criatura> coleccionDeCriaturas = new HashMap<String, Criatura>();

	public MaestroElemental(String nombre, Integer nivelDeMaestria, Elementos afinidadElemental) {
		this.nombre = nombre;
		this.maestria = nivelDeMaestria;
		this.afinidad = afinidadElemental;
	}

	public HashMap<String, Criatura> getColeccionDeCriaturas() {
		return this.coleccionDeCriaturas;
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

	@Override
	public int hashCode() {
		return Objects.hash(nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MaestroElemental other = (MaestroElemental) obj;
		return Objects.equals(nombre, other.nombre);
	}
}