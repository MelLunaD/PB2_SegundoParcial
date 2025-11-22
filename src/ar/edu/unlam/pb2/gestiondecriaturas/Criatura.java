package ar.edu.unlam.pb2.gestiondecriaturas;
import java.util.Objects;

public abstract class Criatura implements InterfaceCriatura{
	public static final Boolean INESTABLE = true;
	public static final Boolean TRANQUILA = false;
	protected String nombre;
	protected Integer energia;
	protected Elementos afinidad;
	protected Boolean estaInestable;
	protected Integer maestriaMinimaRequerida;
	
	public Criatura(String nombre, Integer energia, Elementos afinidad, Integer maestriaMinima) {
		inicializarConstructorDeCriatura(nombre, energia, afinidad, maestriaMinima);
	}
	
	public Criatura(String nombre, Integer energia, Elementos afinidad, Integer maestriaMinima, Boolean inestable) {
		inicializarConstructorDeCriatura(nombre, energia, afinidad, maestriaMinima);
		this.estaInestable = inestable;
	}
	
	private void inicializarConstructorDeCriatura(String nombre, Integer energia, Elementos afinidad, Integer maestriaMinima) {
		this.nombre = nombre;
		this.afinidad = afinidad;
		this.estaInestable = false;
		this.maestriaMinimaRequerida = maestriaMinima;
		
		if(energia < 0)
			this.energia = 0;
		else if (energia > 200)
			this.energia = 200;
		else
			this.energia = energia;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombre);
	}

	// En Criatura.java
	@Override
	public boolean equals(Object obj) {
	    if (this == obj)
	        return true;
	    if (obj == null)
	        return false;
	    
	    if (!(obj instanceof Criatura)) 
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

    @Override
    public Boolean pacificar() {
        if (this.estaInestable) {
            this.estaInestable = Criatura.TRANQUILA;
        }

        return true;
    }

    @Override
    public Integer getMaestriaMinimaRequerida() {
        return this.maestriaMinimaRequerida;
    }
    
    @Override
    public abstract void entrenar(Integer nivelDeMaestriaDelMaestro) throws EntrenamientoExtremoException;

	public void setMaestriaMinimaRequerida(Integer maestria) {
		if ( maestria < 1 || maestria > 50)
			return;
		
		this.maestriaMinimaRequerida = maestria;
	}
}