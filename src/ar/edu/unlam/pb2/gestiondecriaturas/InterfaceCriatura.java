package ar.edu.unlam.pb2.gestiondecriaturas;

public interface InterfaceCriatura {
    String getNombre();
    Integer getEnergia();
    Elementos getAfinidad();
    Boolean getEstaInestable();
    
    void entrenar(Integer nivelDeMaestriaDelMaestro) throws EntrenamientoExtremoException;
    Boolean pacificar();
    void volverInestable();
    void aumentarEnergia(Integer cantidad);
    public void setEnergia(Integer energia);
    
    InterfaceCriatura getCriaturaBase();
    Integer getMaestriaMinimaRequerida();
}