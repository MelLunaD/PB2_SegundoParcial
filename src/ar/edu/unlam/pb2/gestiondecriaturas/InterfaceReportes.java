package ar.edu.unlam.pb2.gestiondecriaturas;

import java.util.List;
import java.util.Map;

public interface InterfaceReportes {
    List<InterfaceCriatura> obtenerTodasLasCriaturas();
    InterfaceCriatura obtenerCriaturaConMayorEnergia();
    MaestroElemental obtenerMaestroConMasCriaturasTransformadas();
    Map<Elementos, Integer> obtenerCantidadDeCriaturasPorAfinidad();
}