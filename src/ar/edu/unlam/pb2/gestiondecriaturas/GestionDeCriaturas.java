package ar.edu.unlam.pb2.gestiondecriaturas;

import java.util.HashMap;

public class GestionDeCriaturas {
	private HashMap<String, MaestroElemental> listaDeMaestros = new HashMap<String, MaestroElemental> ();
	
	public void agregarMaestroElemental(MaestroElemental maestro) {
		listaDeMaestros.put(maestro.getNombre(), maestro);
	}

	public MaestroElemental obtenerMaestroElemental(String nombreElegido) {
		return listaDeMaestros.get(nombreElegido);
	}
	
}