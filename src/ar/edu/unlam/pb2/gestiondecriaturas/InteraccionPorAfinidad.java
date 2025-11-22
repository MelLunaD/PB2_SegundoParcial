package ar.edu.unlam.pb2.gestiondecriaturas;

public class InteraccionPorAfinidad implements InterfaceInteraccion {
	private final Integer AUMENTO_POR_AFINIDAD = 10;

	@Override
	public void interactuar(InterfaceCriatura criatura1, InterfaceCriatura criatura2) {
		Elementos afinidad1 = criatura1.getAfinidad();
		Elementos afinidad2 = criatura2.getAfinidad();
		
		if( afinidad1 == afinidad2 ) {
			criatura1.aumentarEnergia(AUMENTO_POR_AFINIDAD);
			criatura2.aumentarEnergia(AUMENTO_POR_AFINIDAD);
		} else if ( sonAfinidadesOpuestas(afinidad1, afinidad2) ) {
			criatura1.volverInestable();
			criatura2.volverInestable();
		}
	}
	
	private boolean sonAfinidadesOpuestas(Elementos afinidad1, Elementos afinidad2) {
        return (afinidad1 == Elementos.AGUA && afinidad2 == Elementos.FUEGO) || 
               (afinidad1 == Elementos.FUEGO && afinidad2 == Elementos.AGUA) ||
               (afinidad1 == Elementos.AIRE && afinidad2 == Elementos.TIERRA) ||
               (afinidad1 == Elementos.TIERRA && afinidad2 == Elementos.AIRE);
    }
}