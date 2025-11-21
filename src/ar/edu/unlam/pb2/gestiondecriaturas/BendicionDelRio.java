package ar.edu.unlam.pb2.gestiondecriaturas;

public class BendicionDelRio extends Transformacion {

    public BendicionDelRio(Criatura criatura) {
        super(criatura);
    }

    @Override
    public Integer getEnergia() {
        int energiaAnterior = this.criatura.getEnergia();
        
        int energiaNueva = energiaAnterior * 2;
        
        return Math.min(energiaNueva, 180);
    }
}