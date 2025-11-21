package ar.edu.unlam.pb2.gestiondecriaturas;

public class CriaturaDomesticada extends Criatura {
	// FALTA - Aumentan su energía de forma estable.
	// LISTO - Nunca se vuelven inestables.
	public CriaturaDomesticada(String nombre, Integer energia, Elementos afinidad, Integer maestriaMinima) {
		super(nombre, energia, afinidad, maestriaMinima);
	}
	
	public void volverInestable() {
	}

	@Override
	public Integer getEnergia() {
		return super.getEnergia();
	}

	@Override
	public Elementos getAfinidad() {
		return super.getAfinidad();
	}

	@Override
	public void entrenar(Integer nivelDeMaestriaDelMaestro) {
		this.aumentarEnergia(10);
	}
	
	@Override
	public Boolean pacificar() {
		return true;
	}
}