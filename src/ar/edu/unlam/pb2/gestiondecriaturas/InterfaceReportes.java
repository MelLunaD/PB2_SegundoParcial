package ar.edu.unlam.pb2.gestiondecriaturas;

import java.util.List;
import java.util.Map;

public interface InterfaceReportes {
    List<InterfaceCriatura> listarTodasLasCriaturas();
    InterfaceCriatura obtenerCriaturaConMayorEnergia();
    MaestroElemental determinarMaestroConMasTransformadas();
    Map<Elementos, Integer> obtenerCantidadCriaturasPorAfinidad();
}