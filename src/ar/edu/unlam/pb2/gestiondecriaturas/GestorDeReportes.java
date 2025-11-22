package ar.edu.unlam.pb2.gestiondecriaturas;

import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GestorDeReportes implements InterfaceReportes {
    private final Map<String, MaestroElemental> listaDeMaestros; 
    
    public GestorDeReportes(Map<String, MaestroElemental> listaDeMaestros) {
        this.listaDeMaestros = listaDeMaestros;
    }

    private List<InterfaceCriatura> consolidarTodasLasCriaturas() {
        List<InterfaceCriatura> todasLasCriaturas = new ArrayList<>();
        
        for (MaestroElemental maestro : this.listaDeMaestros.values()) {
            for (InterfaceCriatura criatura : maestro.getColeccionDeCriaturas().values()) {
                todasLasCriaturas.add(criatura);
            }
        }
        
        return todasLasCriaturas;
    }

	@Override
	public List<InterfaceCriatura> listarTodasLasCriaturas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InterfaceCriatura obtenerCriaturaConMayorEnergia() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MaestroElemental determinarMaestroConMasTransformadas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Elementos, Integer> obtenerCantidadCriaturasPorAfinidad() {
		// TODO Auto-generated method stub
		return null;
	}
}