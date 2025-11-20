package ar.edu.unlam.pb2.gestiondecriaturas;

import java.util.Objects;

public abstract class Criatura {
	public static final Boolean INESTABLE = true;
	public static final Boolean TRANQUILO = false;
	protected String nombre;
	protected Integer energia;
	protected Elementos afinidad;
	protected Boolean estaInestable;
	
	public Criatura(String nombre, Integer energia, Elementos afinidad, Boolean estaInestable) {
		this.nombre = nombre;
		this.energia = energia;
		this.afinidad = afinidad;
		this.estaInestable = estaInestable;
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
		Criatura other = (Criatura) obj;
		return Objects.equals(nombre, other.nombre);
	}
	
	public String getNombre() {
		return this.nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getEnergia() {
		return this.energia;
	}
	public void setEnergia(Integer energia) {
		if ( energia < 0 || energia > 200 ) {
			return;
		}
		
		this.energia = energia;
	}
	public Elementos getAfinidad() {
		return this.afinidad;
	}
	public void setAfinidad(Elementos afinidad) {
		this.afinidad = afinidad;
	}
	public Boolean getEstaInestable() {
		return this.estaInestable;
	}
	public void volverInestable() {
		this.estaInestable = Criatura.INESTABLE;
	}
	public void volverTranquila() {
		this.estaInestable = Criatura.TRANQUILO;
	}
	
}