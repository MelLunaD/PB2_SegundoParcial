package ar.edu.unlam.pb2.gestiondecriaturas;

import java.util.HashMap;
import java.util.Objects;

public abstract class Criatura {
	public static final Boolean INESTABLE = true;
	public static final Boolean TRANQUILO = false;
	protected String nombre;
	protected Integer energia;
	protected Elementos afinidad;
	protected Elementos afinidadTemporal;
	protected Boolean estaInestable;
	private HashMap<Class<? extends Transformacion>, Transformacion> transformacionesActivas;
	
	public Criatura(String nombre, Integer energia, Elementos afinidad, Boolean estaInestable) {
		this.nombre = nombre;
		this.energia = energia;
		this.afinidad = afinidad;
		this.estaInestable = estaInestable;
		transformacionesActivas = new HashMap<Class<? extends Transformacion>, Transformacion>();
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
		int energiaCalculada = this.energia;

	    for (Transformacion t : this.transformacionesActivas.values()) {
	        energiaCalculada = t.modificarEnergia(energiaCalculada); 
	    }
	    
	    return Math.min(energiaCalculada, 200);
	}
	
	public void setEnergia(Integer energia) {
		if ( energia < 0 || energia > 200 ) {
			return;
		}
		
		this.energia = energia;
	}
	public Elementos getAfinidad() {
		if( this.afinidadTemporal != null ) {
			return this.afinidadTemporal;
		}
		
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

	public void setAfinidadTemporal(Elementos afinidadTemporal) {
		this.afinidadTemporal = afinidadTemporal;
	}
	
	public void transformar(Transformacion transformacion) {
		Class<? extends Transformacion> tipoEfecto = transformacion.getClass(); 
	    this.transformacionesActivas.put(tipoEfecto, transformacion); 
	    
	    transformacion.aplicarEfectoACriatura(this);
	}

	public void aumentarEnergia(int cantidad) {
		if (cantidad <= 0) {
	        return;
	    }
	    
	    int nuevaEnergia = this.energia + cantidad;
	    this.energia = Math.min(nuevaEnergia, 200);
	}
}