package ar.edu.unlam.pb2.gestiondecriaturas;

public class VinculoTerrestre implements Transformacion {

	@Override
	public Integer modificarEnergia(Integer energiaActual) {
		return Math.max(energiaActual, 50);
	}

	@Override
	public void aplicarEfectoACriatura(Criatura criatura) {
	}
}