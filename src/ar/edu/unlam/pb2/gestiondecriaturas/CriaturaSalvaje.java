package ar.edu.unlam.pb2.gestiondecriaturas;

import java.util.Random;

public class CriaturaSalvaje extends Criatura {
	// Son mas dificiles de controlar
	// Cuando se intenta entrenarlas, pueden aumentar su energía de manera
	//  impredecible (deben lanzar una unchecked exception cuando superan 200).
	
	public CriaturaSalvaje(String nombre, Integer energia, Elementos afinidad, Integer maestriaMinima) {
		super(nombre, energia, afinidad, maestriaMinima);
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
		Integer aumento = (1 + new Random().nextInt()) * 5;
		
		if ( this.energia + aumento > 200 ) {
			throw new IllegalStateException("La Criatura Salvaje excedió el límite de energía (200) durante el entrenamiento.");
		}
		
		this.aumentarEnergia(aumento);
	}
}