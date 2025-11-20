package ar.edu.unlam.pb2.gestiondecriaturas;

public class CriaturaAncestral extends Criatura {
	public static final Integer MINIMA_ENERGIA = 100;
	// LISTO - Su energía nunca puede bajar de 100.
	// FALTA - Son extremadamente poderosas, pero sensibles a entrenamientos extremos.

	public CriaturaAncestral(String nombre, Integer energia, Elementos afinidad, Boolean estaInestable) {
		super(nombre, energia, afinidad, estaInestable);
		
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
}
