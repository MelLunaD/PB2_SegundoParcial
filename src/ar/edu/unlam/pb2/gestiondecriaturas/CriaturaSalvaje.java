package ar.edu.unlam.pb2.gestiondecriaturas;

import java.util.Random;

public class CriaturaSalvaje extends Criatura {
	private Random randomGenerator;
	private Boolean esTest;
	private Boolean testBoolean;
	
	public CriaturaSalvaje(String nombre, Integer energia, Elementos afinidad, Integer maestriaMinima) {
		super(nombre, energia, afinidad, maestriaMinima, true);
		this.esTest = false;
		this.testBoolean = false;
		this.randomGenerator = new Random();
	}
	
	public CriaturaSalvaje(String nombre, Integer energia, Elementos afinidad, Integer maestriaMinima, Boolean esTest, Boolean testBoolean) {
		super(nombre, energia, afinidad, maestriaMinima, true);
		this.esTest = true;
		this.testBoolean = testBoolean;
	}

	@Override
	public Integer getEnergia() {
		return super.getEnergia();
	}

	@Override
	public Elementos getAfinidad() {
		return super.getAfinidad();
	}
	
	public void setRandomGenerator(Random randomGenerator) {
	    this.randomGenerator = randomGenerator;
	}
	
	@Override
    public Boolean pacificar() {
		
		// Usamos una variable de control para forzar el Booleano y poder Testearlo
		if (esTest) {
			if ( testBoolean ) {
				super.pacificar();
				return true;
			} else return false;
		}
		
		if (randomGenerator.nextBoolean()) {
			super.pacificar();
			return true;
		}

		return false;
    }

	@Override
	public void entrenar(Integer nivelDeMaestriaDelMaestro) {
		Integer aumento = 15 + randomGenerator.nextInt(86);
		
		if ( this.energia + aumento > 200 ) {
			throw new IllegalStateException("La Criatura Salvaje excedió el límite de energía (200) durante el entrenamiento.");
		}
		
		this.aumentarEnergia(aumento);
	}
}