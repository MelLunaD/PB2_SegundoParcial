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
		int energiaAjustada = Math.min(energia, 200);
		
		this.energia = Math.max(energiaAjustada, CriaturaAncestral.MINIMA_ENERGIA);
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
	public void entrenar(Integer nivelDeMaestriaDelMaestro) throws EntrenamientoExtremoException {
		if (nivelDeMaestriaDelMaestro >= UMBRAL_MAESTRIA_EXTREMA) {
            throw new EntrenamientoExtremoException(
            		"La Criatura Ancestral [" + this.nombre + "] es sensible al entrenamiento de Maestría Nivel " + nivelDeMaestriaDelMaestro + 
                    ". El umbral de riesgo es " + UMBRAL_MAESTRIA_EXTREMA + "."
            );
        }
        
        this.aumentarEnergia(10);
	}
}