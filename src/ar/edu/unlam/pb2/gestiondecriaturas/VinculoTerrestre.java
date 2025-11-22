package ar.edu.unlam.pb2.gestiondecriaturas;

public class VinculoTerrestre extends Transformacion {
	
    public VinculoTerrestre(InterfaceCriatura criatura) {
    	super(criatura);
    }

    @Override
    public Integer getEnergia() {
        int energiaBase = super.getEnergia();
    	
    	return Math.max(energiaBase, 50);
    }
}