package ar.edu.unlam.pb2.gestiondecriaturas;

public interface InterfaceCriatura {
    String getNombre();
    Integer getEnergia();
    Elementos getAfinidad();
    Boolean getEstaInestable();
    
    void entrenar(Integer nivelDeMaestriaDelMaestro) throws EntrenamientoExtremoException;
    void pacificar();
    void volverInestable();
    void aumentarEnergia(Integer cantidad);
    
    void setAfinidadTemporal(Elementos afinidadTemporal);
    InterfaceCriatura getCriaturaBase();
    Integer getMaestriaMinimaRequerida();
}