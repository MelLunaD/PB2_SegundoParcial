package ar.edu.unlam.pb2.gestiondecriaturas;

public class LlamaInterna implements Transformacion {

	@Override
	public Integer modificarEnergia(Integer energiaActual) {
		return energiaActual;
	}

	@Override
	public void aplicarEfectoACriatura(Criatura criatura) {
		if( criatura.getAfinidad() == Elementos.FUEGO ) {
			criatura.aumentarEnergia(30);
		} else {
			criatura.volverInestable();
		}
	}
}