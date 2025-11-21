package ar.edu.unlam.pb2.gestiondecriaturas;

public abstract class Transformacion implements InterfaceCriatura {
    protected InterfaceCriatura criatura; 

    public Transformacion(InterfaceCriatura criaturaDecorada) {
        this.criatura = criaturaDecorada;
    }

    @Override
    public String getNombre() {
        return criatura.getNombre();
    }

    @Override
    public Integer getEnergia() {
        return criatura.getEnergia();
    }
    
    @Override
    public Elementos getAfinidad() {
        return criatura.getAfinidad();
    }
    
    @Override
    public Boolean getEstaInestable() {
        return criatura.getEstaInestable();
    }
    
    @Override
    public void entrenar(Integer nivelDeMaestriaDelMaestro) throws EntrenamientoExtremoException {
        criatura.entrenar(nivelDeMaestriaDelMaestro);
    }
    
    @Override
    public void pacificar() {
        criatura.pacificar();
    }
    
    @Override
    public void aumentarEnergia(Integer cantidad) {
        criatura.aumentarEnergia(cantidad);
    }
    
    @Override
    public void volverInestable() {
        criatura.volverInestable();
    }
    
    @Override
    public void setAfinidadTemporal(Elementos afinidadTemporal) {
        criatura.setAfinidadTemporal(afinidadTemporal);
    }

	@Override
	public Integer getMaestriaMinimaRequerida() {
		return this.criatura.getMaestriaMinimaRequerida();
	}
    
    @Override
    public InterfaceCriatura getCriaturaBase() {
        return criatura.getCriaturaBase();
    }
    
    @Override
	public void setEnergia(Integer energia) {
		criatura.setEnergia(energia);
	}
}