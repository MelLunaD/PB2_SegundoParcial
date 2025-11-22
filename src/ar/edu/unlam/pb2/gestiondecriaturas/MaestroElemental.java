package ar.edu.unlam.pb2.gestiondecriaturas;

import java.util.HashMap;
import java.util.Objects;

public class MaestroElemental {
	private String nombre;
	private Integer maestria;
	private Elementos afinidad;
	private HashMap<String, InterfaceCriatura> coleccionDeCriaturas;

	public MaestroElemental(String nombre, Integer nivelDeMaestria, Elementos afinidadElemental) {
		this.nombre = nombre;
		this.maestria = nivelDeMaestria;
		this.afinidad = afinidadElemental;
		this.coleccionDeCriaturas = new HashMap<String, InterfaceCriatura>();
	}
	
	public void entrenarCriatura(String nombreCriatura) throws MaestriaInsuficienteException, EntrenamientoExtremoException {
		InterfaceCriatura criatura = this.coleccionDeCriaturas.get(nombreCriatura);
        
        if (criatura == null) {
            throw new IllegalArgumentException("La criatura ["+ nombreCriatura +"] no pertenece al Maestro [" + this.nombre + "].");
        }
        
        if (this.maestria < criatura.getMaestriaMinimaRequerida()) { 
            throw new MaestriaInsuficienteException("El Maestro [" + this.nombre + "] no tiene la maestría suficiente para entrenar a [" + criatura.getNombre() + "].");
        }
        
        criatura.entrenar(this.maestria); 
    }

    public void pacificarCriatura(String nombreCriatura) {
    	InterfaceCriatura criatura = this.coleccionDeCriaturas.get(nombreCriatura);
        
        if (criatura != null && criatura.getEstaInestable()) {
            criatura.pacificar(); 
        }
    }

    public void transformarCriatura(String nombreCriatura, Transformacion ritual) {
    	InterfaceCriatura criaturaActual = this.coleccionDeCriaturas.get(nombreCriatura);
        
        if (criaturaActual == null) {
            throw new IllegalArgumentException("La criatura no existe.");
        }
        
        InterfaceCriatura criaturaDecorada = ritual; 
        
        this.coleccionDeCriaturas.put(nombreCriatura, criaturaDecorada); 
    }
    
    public void agregarCriatura(InterfaceCriatura criatura) {
        this.coleccionDeCriaturas.put(criatura.getNombre(), criatura);
    }

	public HashMap<String, InterfaceCriatura> getColeccionDeCriaturas() {
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
	
	public InterfaceCriatura obtenerCriatura(String nombreCriatura) {
	    return this.coleccionDeCriaturas.get(nombreCriatura);
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