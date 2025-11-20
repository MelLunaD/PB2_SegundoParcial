package ar.edu.unlam.pb2.gestiondecriaturas;

public class CriaturaDomesticada extends Criatura {
	// FALTA - Aumentan su energía de forma estable.
	// LISTO - Nunca se vuelven inestables.
	public CriaturaDomesticada(String nombre, Integer energia, Elementos afinidad) {
		super(nombre, energia, afinidad, false);
	}
	
	public void volverInestable() {
		this.estaInestable = false;
	}

}
