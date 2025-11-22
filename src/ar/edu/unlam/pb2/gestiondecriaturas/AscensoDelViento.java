package ar.edu.unlam.pb2.gestiondecriaturas;

public class AscensoDelViento extends Transformacion {
	
    public AscensoDelViento(InterfaceCriatura criatura) {
    	super(criatura);
    }

    @Override
    public Elementos getAfinidad() {
        return Elementos.AIRE;
    }
}