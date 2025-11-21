package ar.edu.unlam.pb2.gestiondecriaturas;

import java.util.HashMap;
import java.util.Objects;

public abstract class Criatura implements InterfaceCriatura{
	public static final Boolean INESTABLE = true;
	public static final Boolean TRANQUILO = false;
	protected String nombre;
	protected Integer energia;
	protected Elementos afinidad;
	protected Boolean estaInestable;
	protected Elementos afinidadTemporal;
	protected Integer maestriaMinimaRequerida;
	
	public Criatura(String nombre, Integer energia, Elementos afinidad, Integer maestriaMinima) {
		this.nombre = nombre;
		this.afinidad = afinidad;
		this.estaInestable = false;
		this.maestriaMinimaRequerida = maestriaMinima;
		
		if(energia < 0 || energia > 200)
			this.energia = 0;
		else
			this.energia = energia;
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
	
	@Override
    public Integer getEnergia() {
        return this.energia; 
    }
    
    @Override
    public Elementos getAfinidad() {
        if( this.afinidadTemporal != null ) {
            return this.afinidadTemporal;
        }
        return this.afinidad;
    }
	
	public void setEnergia(Integer energia) {
		if ( energia < 0 || energia > 200 ) {
			return;
		}
		
		this.energia = energia;
	}
	
	public void setAfinidad(Elementos afinidad) {
		this.afinidad = afinidad;
	}

	@Override
    public InterfaceCriatura getCriaturaBase() {
        return this;
    }
	
	public Boolean getEstaInestable() {
		return this.estaInestable;
	}
	
	// Métodos de comportamiento
	
	@Override
	public void aumentarEnergia(Integer cantidad) {
		if (cantidad <= 0) {
	        return;
	    }
	    
	    int nuevaEnergia = this.energia + cantidad;
	    this.energia = Math.min(nuevaEnergia, 200);
	}
	
	@Override
	public void volverInestable() {
		this.estaInestable = Criatura.INESTABLE;
	}
	
	public void volverTranquila() {
		this.estaInestable = Criatura.TRANQUILO;
	}

    @Override
    public void pacificar() {
        if (this.estaInestable) {
            this.estaInestable = false;
        }
    }
    
    @Override
    public void setAfinidadTemporal(Elementos afinidadTemporal) {
        this.afinidadTemporal = afinidadTemporal;
    }

    @Override
    public Integer getMaestriaMinimaRequerida() {
        return this.maestriaMinimaRequerida;
    }
    
    @Override
    public abstract void entrenar(Integer nivelDeMaestriaDelMaestro) throws EntrenamientoExtremoException;
}