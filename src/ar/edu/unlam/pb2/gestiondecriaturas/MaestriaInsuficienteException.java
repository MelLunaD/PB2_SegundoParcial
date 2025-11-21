package ar.edu.unlam.pb2.gestiondecriaturas;

public class MaestriaInsuficienteException extends Exception {
	private static final long serialVersionUID = 1L;

	public MaestriaInsuficienteException(String mensaje) {
        super(mensaje);
    }
}