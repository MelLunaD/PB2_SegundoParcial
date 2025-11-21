package ar.edu.unlam.pb2.gestiondecriaturas;

public class CriaturaAncestral extends Criatura {
	public static final Integer MINIMA_ENERGIA = 100;
    private static final Integer UMBRAL_MAESTRIA_EXTREMA = 40;

	public CriaturaAncestral(String nombre, Integer energia, Elementos afinidad, Integer maestriaMinima) {
		super(nombre, energia, afinidad, maestriaMinima);
		
		if ( energia < MINIMA_ENERGIA ) {
			this.energia = MINIMA_ENERGIA;
		}
	}
	
	public void setEnergia(Integer energia) {
		if ( energia < 0 || energia > 200 ) {
			return;
		}
		
		if ( energia < CriaturaAncestral.MINIMA_ENERGIA) {
			this.energia = CriaturaAncestral.MINIMA_ENERGIA;
		} else {
			this.energia = energia;
		}
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
	public void entrenar(Integer nivelDeMaestriaDelMaestro) throws EntrenamientoExtremoException {
		if (nivelDeMaestriaDelMaestro >= UMBRAL_MAESTRIA_EXTREMA) {
            throw new EntrenamientoExtremoException("La Criatura Ancestral " + this.nombre + " es sensible al entrenamiento de Maestría " + nivelDeMaestriaDelMaestro + ".");
        }
        
        this.aumentarEnergia(10);
	}
}