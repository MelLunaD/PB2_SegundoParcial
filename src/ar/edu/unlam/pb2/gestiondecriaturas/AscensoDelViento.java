package ar.edu.unlam.pb2.gestiondecriaturas;

public class AscensoDelViento implements Transformacion {

	@Override
	public Integer modificarEnergia(Integer energiaActual) {
		return energiaActual;
	}

	@Override
	public void aplicarEfectoACriatura(Criatura criatura) {
		criatura.setAfinidadTemporal(Elementos.AIRE);
	}
}