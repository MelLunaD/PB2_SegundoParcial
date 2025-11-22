package ar.edu.unlam.pb2.gestiondecriaturas;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class GestorDeInteraccionesTest {
    private GestorDeInteracciones gestor;
    
    private CriaturaAncestral momo;
    private CriaturaDomesticada appa;
    private CriaturaSalvaje unagi;
    private CriaturaAncestral shaw;
    private CriaturaSalvaje topo;
    
    private final Integer MAESTRIA_MIN = 10;
    
    @Before
    public void setUp() throws Exception {
        gestor = new GestorDeInteracciones();
        
        momo = new CriaturaAncestral("Momo", 100, Elementos.AIRE, MAESTRIA_MIN);
        appa = new CriaturaDomesticada("Appa", 150, Elementos.AIRE, MAESTRIA_MIN);
        unagi = new CriaturaSalvaje("Unagi", 50, Elementos.AGUA, MAESTRIA_MIN);
        shaw = new CriaturaAncestral("Shaw", 120, Elementos.FUEGO, MAESTRIA_MIN);
        topo = new CriaturaSalvaje("Topo", 30, Elementos.TIERRA, MAESTRIA_MIN);

        momo.pacificar();
        appa.pacificar();
        unagi.volverInestable();
        shaw.pacificar();
        topo.volverInestable();
    }

    @Test
    public void queAncestralDomineYElPerdedorPierdaEnergia() {
        gestor.interactuarCriaturas(momo, appa);
        
        // Momo gana 20
        assertEquals(120, momo.getEnergia(), 0.01); // 100 + 20 = 120
        
        // Appa pierde 15
        assertEquals(135, appa.getEnergia(), 0.01); // 150 - 15 = 135
        
        // Ambos siguen tranquilos
        assertFalse(momo.getEstaInestable());
        assertFalse(appa.getEstaInestable());
    }
    
    @Test
    public void queAncestralDomineCuandoEsLaSegundaCriatura() {
        gestor.interactuarCriaturas(unagi, shaw); 
        
        // Shaw gana 20: 
        assertEquals(140, shaw.getEnergia().intValue()); // 120 + 20 = 140
        
        // Perdedor Unagi pierde 15: 
        assertEquals(35, unagi.getEnergia().intValue()); // 50 - 15 = 35
    }
    
    @Test
    public void queAncestralDomineYSeRespeteElMinimoDe100DelPerdedorAncestral() {
        shaw.setEnergia(105); 
        
        gestor.interactuarCriaturas(shaw, topo);
        
        // Shaw gana 20
        assertEquals(125, shaw.getEnergia().intValue()); // 105 + 20 = 125
        
        // Topo pierde 15
        assertEquals(15, topo.getEnergia().intValue());  // 30 - 15 = 15
    }

    @Test
    public void queDosAncestralesUsenReglasDeAfinidad() {
        gestor.interactuarCriaturas(momo, shaw);
        
        assertFalse(momo.getEstaInestable());
        assertFalse(shaw.getEstaInestable());
        
        assertEquals(100, momo.getEnergia(), 0.01);
        assertEquals(120, shaw.getEnergia(), 0.01);
    }

    @Test
    public void queDosCriaturasNormalesUsenReglasDeAfinidadCompartida() {
        CriaturaDomesticada appa2 = new CriaturaDomesticada("Appa2", 100, Elementos.AIRE, MAESTRIA_MIN);

        gestor.interactuarCriaturas(appa, appa2);
        
        // Ambos ganan 10 de energía
        assertEquals(160, appa.getEnergia().intValue());  // 150 + 10 = 160
        assertEquals(110, appa2.getEnergia().intValue()); // 100 + 10 = 110
    }

    @Test
    public void queDosCriaturasNormalesUsenReglasDeAfinidadOpuesta() {
        CriaturaSalvaje salamandra = new CriaturaSalvaje("Flareon", 50, Elementos.FUEGO, MAESTRIA_MIN); 
        salamandra.pacificar();

        gestor.interactuarCriaturas(unagi, salamandra);
        
        assertTrue(unagi.getEstaInestable());
        assertTrue(salamandra.getEstaInestable()); 
        
        // Energía no cambia
        assertEquals(50, unagi.getEnergia().intValue());
        assertEquals(50, salamandra.getEnergia().intValue());
    }
    
    @Test
    public void queCriaturaPerdedoraPierda15Energia() {
        CriaturaSalvaje debil = new CriaturaSalvaje("Debil", 20, Elementos.AGUA, MAESTRIA_MIN); 
        
        gestor.interactuarCriaturas(momo, debil);
        
        assertEquals(5, debil.getEnergia().intValue()); // 20 - 15 = 5
    }
    
    private void assertInteraccion(Elementos a1, Elementos a2, boolean debeSerInestable) {
        CriaturaAncestral c1 = new CriaturaAncestral("C1", 100, a1, MAESTRIA_MIN); 
        CriaturaAncestral c2 = new CriaturaAncestral("C2", 100, a2, MAESTRIA_MIN);
        
        c1.pacificar();
        c2.pacificar(); 

        InteraccionPorAfinidad afinidadTest = new InteraccionPorAfinidad();
        afinidadTest.interactuar(c1, c2);

        if (debeSerInestable) {
            assertTrue("Fallo en el par opuesto: " + a1 + " vs " + a2, c1.getEstaInestable());
            assertTrue("Fallo en el par opuesto: " + a1 + " vs " + a2, c2.getEstaInestable());
        } else {
            assertFalse("Debería ser neutro: " + a1 + " vs " + a2, c1.getEstaInestable());
            assertFalse("Debería ser neutro: " + a1 + " vs " + a2, c2.getEstaInestable());
        }
    }

    @Test
    public void queSonAfinidadesOpuestasCubraTodosLosParesYCasoNeutro() {
    	// Casos opuestos
        assertInteraccion(Elementos.AGUA, Elementos.FUEGO, true);
        assertInteraccion(Elementos.FUEGO, Elementos.AGUA, true);
        assertInteraccion(Elementos.AIRE, Elementos.TIERRA, true);
        assertInteraccion(Elementos.TIERRA, Elementos.AIRE, true);
        
        // Casos neutros
        assertInteraccion(Elementos.AGUA, Elementos.TIERRA, false);
        assertInteraccion(Elementos.FUEGO, Elementos.AIRE, false);
    }
}