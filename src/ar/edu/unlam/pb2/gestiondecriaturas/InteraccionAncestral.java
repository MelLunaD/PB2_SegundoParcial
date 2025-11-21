package ar.edu.unlam.pb2.gestiondecriaturas;

public class InteraccionAncestral implements InterfaceInteraccion {
	private final Integer AUMENTO_ANCESTRAL = 20;
	private final Integer DISMINUCION_NO_ANCESTRAL = 15;
	private final InterfaceInteraccion interaccionAfinidad = new InteraccionPorAfinidad();

	@Override
	public void interactuar(InterfaceCriatura criatura1, InterfaceCriatura criatura2) {
		Boolean criatura1EsAncestral = criatura1.getCriaturaBase() instanceof CriaturaAncestral;
		Boolean criatura2EsAncestral = criatura2.getCriaturaBase() instanceof CriaturaAncestral;

		if ( (!criatura1EsAncestral && !criatura2EsAncestral) || (criatura1EsAncestral && criatura2EsAncestral) ) {
			interaccionAfinidad.interactuar(criatura1, criatura2);
			return;
		}
		
		InterfaceCriatura dominante, perdedor;
		
		if( criatura1EsAncestral ) {
			dominante = criatura1;
			perdedor = criatura2;
		} else {
			dominante = criatura2;
			perdedor = criatura1;
		}
		
		dominante.aumentarEnergia(AUMENTO_ANCESTRAL);
		perdedor.setEnergia(perdedor.getEnergia() - DISMINUCION_NO_ANCESTRAL);
	}
}