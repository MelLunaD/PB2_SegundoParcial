package ar.edu.unlam.pb2.gestiondecriaturas;

public class GestorDeInteracciones {
	private final InterfaceInteraccion interaccionPrincipal = new InteraccionAncestral();
    
    public void interactuarCriaturas(InterfaceCriatura c1, InterfaceCriatura c2) {
        interaccionPrincipal.interactuar(c1, c2);
        // En caso de ninguno ser Ancestral, la misma clase InteraccionAncestral llama a InteraccionPorAfinidad
    }
}