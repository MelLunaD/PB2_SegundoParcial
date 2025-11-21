package ar.edu.unlam.pb2.gestiondecriaturas;

public class BendicionDelRio extends Transformacion {

    public BendicionDelRio(InterfaceCriatura criatura) {
        super(criatura);
    }

    @Override
    public Integer getEnergia() {
        int energiaAnterior = super.getEnergia();
        
        int energiaNueva = energiaAnterior * 2;
        
        return Math.min(energiaNueva, 180);
    }
}