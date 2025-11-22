package ar.edu.unlam.pb2.gestiondecriaturas;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;

public class GestorDeReportesTest {

    private Map<String, MaestroElemental> listaDeMaestros;
    private GestorDeReportes gestor;

    private final Integer MAESTRIA_MIN = 10;
    
    @Before
    public void setUp() throws Exception {
        // Solo inicializamos las colecciones y el Gestor, sin crear criaturas.
        listaDeMaestros = new HashMap<>();
        gestor = new GestorDeReportes(listaDeMaestros);
    }
    
    // --- TESTS REQUERIDOS (Parte IV) ---

    @Test
    public void queListarTodasLasCriaturasDevuelvaElTotalDelSistema() {
        MaestroElemental m1 = new MaestroElemental("A", 40, Elementos.AIRE);
        MaestroElemental m2 = new MaestroElemental("B", 30, Elementos.FUEGO);
        
        CriaturaDomesticada c1 = new CriaturaDomesticada("C1", 100, Elementos.AGUA, MAESTRIA_MIN);
        CriaturaSalvaje c2 = new CriaturaSalvaje("C2", 50, Elementos.TIERRA, MAESTRIA_MIN);
        
        m1.agregarCriatura(c1);
        m2.agregarCriatura(c2);
        
        listaDeMaestros.put(m1.getNombre(), m1);
        listaDeMaestros.put(m2.getNombre(), m2);
        
        List<InterfaceCriatura> todas = gestor.obtenerTodasLasCriaturas();
        
        assertEquals(2, todas.size()); 
        assertTrue(todas.contains(c1));
        assertTrue(todas.contains(c2));
    }
    
    @Test
    public void queSeObtengaLaCriaturaConMayorEnergiaTotal() {
        MaestroElemental m1 = new MaestroElemental("A", 40, Elementos.AIRE);
        
        CriaturaAncestral c1_base = new CriaturaAncestral("C1", 90, Elementos.FUEGO, MAESTRIA_MIN);
        Transformacion c1_fuerte = new BendicionDelRio(c1_base);
        
        CriaturaDomesticada c2 = new CriaturaDomesticada("C2", 150, Elementos.AIRE, MAESTRIA_MIN);
        
        m1.agregarCriatura(c1_fuerte);
        m1.agregarCriatura(c2);
        listaDeMaestros.put(m1.getNombre(), m1);
        
        InterfaceCriatura masFuerte = gestor.obtenerCriaturaConMayorEnergia();
        
        assertNotNull(masFuerte);
        assertEquals("C1", masFuerte.getNombre());
        assertEquals(180, masFuerte.getEnergia().intValue());
    }
    
    @Test
    public void queObtenerCriaturaConMayorEnergiaManejeLaIgualdad() {
        MaestroElemental m1 = new MaestroElemental("A", 40, Elementos.AIRE);
        CriaturaDomesticada c1 = new CriaturaDomesticada("C1", 150, Elementos.AGUA, MAESTRIA_MIN);
        CriaturaDomesticada c2 = new CriaturaDomesticada("C2", 150, Elementos.FUEGO, MAESTRIA_MIN);
        
        m1.agregarCriatura(c1);
        m1.agregarCriatura(c2);
        listaDeMaestros.put(m1.getNombre(), m1);
        
        InterfaceCriatura masFuerte = gestor.obtenerCriaturaConMayorEnergia();

        assertNotNull(masFuerte);
        assertEquals(150, masFuerte.getEnergia().intValue());
    }
    
    @Test
    public void queSeDetermineElMaestroConMasCriaturasTransformadas() {
        MaestroElemental m1 = new MaestroElemental("M1", 40, Elementos.AIRE);
        MaestroElemental m2 = new MaestroElemental("M2", 50, Elementos.FUEGO);
        
        CriaturaSalvaje c1 = new CriaturaSalvaje("C1", 100, Elementos.AGUA, MAESTRIA_MIN);
        CriaturaDomesticada c2 = new CriaturaDomesticada("C2", 150, Elementos.AIRE, MAESTRIA_MIN);
        
        m1.agregarCriatura(new BendicionDelRio(c1)); 
        
        m2.agregarCriatura(new LlamaInterna(c2));
        m2.agregarCriatura(new AscensoDelViento(c1));
        
        listaDeMaestros.put(m1.getNombre(), m1);
        listaDeMaestros.put(m2.getNombre(), m2);
        
        MaestroElemental maestroGanador = gestor.obtenerMaestroConMasCriaturasTransformadas();
        
        assertNotNull(maestroGanador);
        assertEquals("M2", maestroGanador.getNombre());
    }
    
    @Test
    public void queObtenerCriaturaConMayorEnergiaDevuelvaNullSiNoHayCriaturas() {
        InterfaceCriatura masFuerte = gestor.obtenerCriaturaConMayorEnergia();
        
        assertNull(masFuerte);
    }
    
    @Test
    public void queObtenerCriaturaConMayorEnergiaSeInicialiceConLaPrimeraCriatura() {
        MaestroElemental m1 = new MaestroElemental("Unico", 30, Elementos.AIRE);
        CriaturaDomesticada c1 = new CriaturaDomesticada("Solo", 85, Elementos.AGUA, 10);
        
        m1.agregarCriatura(c1);
        listaDeMaestros.put(m1.getNombre(), m1);
        
        InterfaceCriatura masFuerte = gestor.obtenerCriaturaConMayorEnergia();
        
        assertNotNull(masFuerte);
        assertEquals("Solo", masFuerte.getNombre());
    }
    
    @Test
    public void queObtenerMaestroTransformadoManejeEmpateOCuentaMenor() {
        MaestroElemental m1 = new MaestroElemental("M1", 40, Elementos.AIRE);
        MaestroElemental m2 = new MaestroElemental("M2", 50, Elementos.FUEGO);
        MaestroElemental m3 = new MaestroElemental("M3", 30, Elementos.TIERRA);
        
        CriaturaDomesticada c1 = new CriaturaDomesticada("C1", 100, Elementos.AGUA, MAESTRIA_MIN);
        
        m1.agregarCriatura(new BendicionDelRio(c1)); 
        
        m2.agregarCriatura(new LlamaInterna(c1)); 
        
        m3.agregarCriatura(c1); 
        
        listaDeMaestros.put(m1.getNombre(), m1);
        listaDeMaestros.put(m2.getNombre(), m2);
        listaDeMaestros.put(m3.getNombre(), m3);

        MaestroElemental maestroGanador = gestor.obtenerMaestroConMasCriaturasTransformadas();
        
        assertNotNull(maestroGanador);
        assertEquals("M1", maestroGanador.getNombre());
    }

    @Test
    public void queSeObtengaElMapaDeCantidadDeCriaturasPorAfinidad() {
        MaestroElemental m1 = new MaestroElemental("A", 40, Elementos.AIRE);
        
        CriaturaDomesticada c1 = new CriaturaDomesticada("C1", 100, Elementos.AGUA, MAESTRIA_MIN);
        CriaturaSalvaje c2 = new CriaturaSalvaje("C2", 50, Elementos.TIERRA, MAESTRIA_MIN);
        CriaturaAncestral c3 = new CriaturaAncestral("C3", 120, Elementos.AGUA, MAESTRIA_MIN);
        
        m1.agregarCriatura(c1);
        m1.agregarCriatura(c2);
        m1.agregarCriatura(c3);
        listaDeMaestros.put(m1.getNombre(), m1);
        
        Map<Elementos, Integer> mapaAfinidades = gestor.obtenerCantidadDeCriaturasPorAfinidad();
        
        assertEquals(2, mapaAfinidades.get(Elementos.AGUA).intValue());
        assertEquals(1, mapaAfinidades.get(Elementos.TIERRA).intValue());
        assertNull(mapaAfinidades.get(Elementos.FUEGO));
        assertNull(mapaAfinidades.get(Elementos.AIRE));
    }
}