package ar.edu.unlam.pb2.gestiondecriaturas;

import java.util.*;

public class GestorDeReportes implements InterfaceReportes {
    private final Map<String, MaestroElemental> listaDeMaestros;
    
    public GestorDeReportes(Map<String, MaestroElemental> listaDeMaestros) {
        this.listaDeMaestros = listaDeMaestros;
    }

    public List<InterfaceCriatura> obtenerTodasLasCriaturas() {
        List<InterfaceCriatura> todasLasCriaturas = new ArrayList<>();
        
        for (MaestroElemental maestro : this.listaDeMaestros.values()) {
            for (InterfaceCriatura criatura : maestro.getColeccionDeCriaturas().values()) {
                todasLasCriaturas.add(criatura);
            }
        }
        
        return todasLasCriaturas;
    }

	@Override
	public InterfaceCriatura obtenerCriaturaConMayorEnergia() {
		InterfaceCriatura criaturaConMayorEnergia = null;
        
        for (MaestroElemental maestro : this.listaDeMaestros.values()) {
            for (InterfaceCriatura criatura : maestro.getColeccionDeCriaturas().values()) {
            	if( criaturaConMayorEnergia == null || criatura.getEnergia() > criaturaConMayorEnergia.getEnergia()) {
            		criaturaConMayorEnergia = criatura;
            	}
            }
        }
        
        return criaturaConMayorEnergia;
	}

	@Override
	public MaestroElemental obtenerMaestroConMasCriaturasTransformadas() {
		MaestroElemental maestroConMasCriaturasTransformadas = null;
		Integer cantidadCriaturasActual = 0;
		Integer cantidadCriaturasMayor = 0;
        
        for (MaestroElemental maestro : this.listaDeMaestros.values()) {
        	cantidadCriaturasActual = 0;
        	
            for (InterfaceCriatura criatura : maestro.getColeccionDeCriaturas().values()) {
            	if(criatura instanceof Transformacion) {
            		cantidadCriaturasActual++;
            	}
            }

            if(cantidadCriaturasActual > cantidadCriaturasMayor) {
            	maestroConMasCriaturasTransformadas = maestro;
            	cantidadCriaturasMayor = cantidadCriaturasActual;
            }
        }
        
		return maestroConMasCriaturasTransformadas;
	}

	@Override
	public Map<Elementos, Integer> obtenerCantidadDeCriaturasPorAfinidad() {
	    Map<Elementos, Integer> criaturasPorAfinidad = new HashMap<Elementos, Integer>();
	    
	    for (MaestroElemental maestro : this.listaDeMaestros.values()) {
            for (InterfaceCriatura criatura : maestro.getColeccionDeCriaturas().values()) {
            	Elementos afinidadActual = criatura.getAfinidad();
            	Integer cantidadActual = criaturasPorAfinidad.getOrDefault(afinidadActual, 0);
    			criaturasPorAfinidad.put(afinidadActual, cantidadActual + 1);
            }
        }
	    
		return criaturasPorAfinidad;
	}
}