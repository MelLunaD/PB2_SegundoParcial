package ar.edu.unlam.pb2.gestiondecriaturas;

public class BendicionDelRio implements Transformacion {
	@Override
	public Integer modificarEnergia(Integer energiaActual) {
        return Math.min(energiaActual * 2, 180);
	}

	@Override
	public void aplicarEfectoACriatura(Criatura criatura) {
	}
}