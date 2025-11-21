package ar.edu.unlam.pb2.gestiondecriaturas;

public class CriaturaDomesticada extends Criatura {
	// FALTA - Aumentan su energía de forma estable.
	// LISTO - Nunca se vuelven inestables.
	public CriaturaDomesticada(String nombre, Integer energia, Elementos afinidad, Integer maestriaMinima) {
		super(nombre, energia, afinidad, maestriaMinima);
	}
	
	public void volverInestable() {
		this.estaInestable = false;
	}

	@Override
	public Integer getEnergia() {
		return this.energia;
	}

	@Override
	public Elementos getAfinidad() {
		return this.afinidad;
	}

	@Override
	public void entrenar(Integer nivelDeMaestriaDelMaestro) {
		this.aumentarEnergia(10);
	}
	
	@Override
	public void pacificar() {
	}
}