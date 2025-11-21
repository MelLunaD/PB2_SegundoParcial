package ar.edu.unlam.pb2.gestiondecriaturas;

public class VinculoTerrestre extends Transformacion {
	
    public VinculoTerrestre(Criatura criatura) {
    	super(criatura);
    }

    @Override
    public Integer getEnergia() {
        int energiaBase = this.criatura.getEnergia();
    	
    	return Math.max(energiaBase, 50);
    }
}