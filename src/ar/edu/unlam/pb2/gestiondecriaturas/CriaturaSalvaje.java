package ar.edu.unlam.pb2.gestiondecriaturas;

public class CriaturaSalvaje extends Criatura {
	// Son mas dificiles de controlar
	// Cuando se intenta entrenarlas, pueden aumentar su energía de manera
	//  impredecible (deben lanzar una unchecked exception cuando superan 200).
	
	public CriaturaSalvaje(String nombre, Integer energia, Elementos afinidad, Boolean estaInestable) {
		super(nombre, energia, afinidad, estaInestable);
	}
}