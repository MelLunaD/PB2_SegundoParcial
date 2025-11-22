package ar.edu.unlam.pb2.gestiondecriaturas;

public class LlamaInterna extends Transformacion {
	
    public LlamaInterna(InterfaceCriatura criatura) {
        super(criatura);

        if (super.getAfinidad() != Elementos.FUEGO) {
             super.volverInestable();
        }
    }

    @Override
    public Integer getEnergia() {
        int energiaBase = super.getEnergia();
        
        if (super.getAfinidad() == Elementos.FUEGO) {
            energiaBase += 30;
        }
        
        return energiaBase;
    }
}