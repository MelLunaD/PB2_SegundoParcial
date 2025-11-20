package ar.edu.unlam.pb2.gestiondecriaturas;

public interface Transformacion {
	Integer modificarEnergia(Integer energiaActual);
	void aplicarEfectoACriatura(Criatura criatura);
}