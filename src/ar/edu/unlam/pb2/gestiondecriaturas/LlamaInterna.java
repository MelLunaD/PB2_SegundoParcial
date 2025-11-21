package ar.edu.unlam.pb2.gestiondecriaturas;

public class LlamaInterna extends Transformacion {
	
    public LlamaInterna(Criatura criatura) {
        super(criatura);

        if (getAfinidad() != Elementos.FUEGO) {
             criatura.volverInestable(); 
        }
    }

    @Override
    public Integer getEnergia() {
        int energiaBase = this.criatura.getEnergia();
        
        if (this.criatura.getAfinidad() == Elementos.FUEGO) {
            energiaBase += 30;
        }
        
        return energiaBase;
    }
}