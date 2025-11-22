package ar.edu.unlam.pb2.gestiondecriaturas;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GestionDeCriaturasTest {
	public GestionDeCriaturas gestion;

	public String nombreElegido = "Ash";
	public Integer nivelDeMaestriaElegido = 22;
	Elementos miElemento = Elementos.FUEGO;

	private CriaturaDomesticada appa;
	private CriaturaDomesticada appaApto;
    private CriaturaAncestral momo;
    private CriaturaSalvaje unagiAlto;
    
    private MaestroElemental maestroApto;
    private MaestroElemental maestroNoApto;
    private MaestroElemental maestroExtremo;

	@Before
	public void setUp() throws Exception {
		gestion = new GestionDeCriaturas();

		appa = new CriaturaDomesticada("Appa", 150, Elementos.AIRE, 30);
		appaApto = new CriaturaDomesticada("AppaApto", 150, Elementos.AIRE, 30);
        momo = new CriaturaAncestral("Momo", 100, Elementos.AIRE, 15);
        unagiAlto = new CriaturaSalvaje("UnagiAlto", 190, Elementos.AGUA, 40);
        
        maestroApto = new MaestroElemental("Apto", 45, Elementos.FUEGO);
        maestroNoApto = new MaestroElemental("NoApto", 5, Elementos.FUEGO);
        maestroExtremo = new MaestroElemental("Extremo", 50, Elementos.FUEGO);
        
        maestroApto.agregarCriatura(unagiAlto); // Para IllegalStateException
        maestroApto.agregarCriatura(appaApto);
        maestroExtremo.agregarCriatura(momo); 	// Para EntrenamientoExtremoException
        maestroNoApto.agregarCriatura(appa); 	// Para MaestriaInsuficienteException

        gestion.agregarMaestroElemental(maestroApto);
        gestion.agregarMaestroElemental(maestroNoApto);
        gestion.agregarMaestroElemental(maestroExtremo);
	}

	@Test
	public void queSePuedaCrearUnaGestionDeCriaturas() {
		assertNotNull(gestion);
	}
	
	@Test
	public void queSePuedaRegistrarYObtenerUnMaestroElemental() {
		MaestroElemental maestroEsperado = maestroApto;
		MaestroElemental maestroObtenido = gestion.obtenerMaestroElemental("Apto");
		
		assertTrue( maestroEsperado.equals(maestroObtenido) );
	}
	
	@Test
	public void queElEntrenamientoSeaExitosoCuandoHayMaestriaSuficiente() {
	    Integer energiaInicial = appaApto.getEnergia(); 

	    gestion.intentarEntrenar("Apto", "AppaApto");
	    
	    assertEquals(energiaInicial + 10, appaApto.getEnergia(), 0.01); 
	}

	@Test
	public void queIntenarEntrenarRetorneSiElMaestroNoExiste() {
	    gestion.intentarEntrenar("MaestroInexistente", "Appa");
	}
	
	@Test
	public void queAtrapeMaestriaInsuficienteException() {
	    gestion.intentarEntrenar("NoApto", "Appa");
	}

	@Test
	public void queAtrapeEntrenamientoExtremoException() {
	    gestion.intentarEntrenar("Extremo", "Momo");
	}

	@Test
	public void queAtrapeIllegalStateExceptionPorEnergiaExcedida() {
	    gestion.intentarEntrenar("Apto", "UnagiAlto");
	}

	@Test
	public void queAtrapeIllegalArgumentExceptionPorCriaturaInexistente() {
	    gestion.intentarEntrenar("Apto", "CriaturaInexistente");
	}
	
	@Test
	public void queIntenarTransformarRetorneSiElMaestroNoExiste() {
	    Transformacion ritual = new BendicionDelRio(appa);
	    try {
	        gestion.intentarTransformar("MaestroInexistente", "Appa", ritual);
	    } catch (Exception e) {
	        fail("No debería haber lanzado ninguna excepción.");
	    }
	}

	@Test
	public void queLaTransformacionDeUnaCriaturaSeaExitosa() {
	    Transformacion bendicion = new BendicionDelRio(appaApto); 
	    
	    gestion.intentarTransformar("Apto", "AppaApto", bendicion);
	    
	    InterfaceCriatura appaTransformada = maestroApto.obtenerCriatura("AppaApto");

	    assertTrue(appaTransformada instanceof BendicionDelRio);
	    
	    assertEquals(180, appaTransformada.getEnergia(), 0.01); 
	}

	@Test
	public void queAtrapeIllegalArgumentExceptionAlIntentarTransformarCriaturaInexistente() {
	    Transformacion ritual = new BendicionDelRio(appaApto);
	    
	    gestion.intentarTransformar("Apto", "Fantasma", ritual);
	}

	// ------------------------------------------------------------------------------------------------

	@Test
	public void queLaInteraccionEntreDosCriaturasSeaExitosa() {
	    Integer energiaInicialAppa = appa.getEnergia();
	    
	    gestion.interactuarCriaturas("Apto", "Appa", "Apto", "Unagi");
	    
	    assertEquals(energiaInicialAppa, appa.getEnergia());
	}
	
	@Test
	public void queLaInteraccionRetorneSiLasCriaturasSonMismas() {
	    gestion.interactuarCriaturas("Apto", "AppaApto", "Apto", "AppaApto");
	}
	@Test
	public void queLaInteraccionRetorneSiLaPrimeraCriaturaNoExiste() {
	    gestion.interactuarCriaturas("Apto", "CriaturaInexistente", "Apto", "AppaApto");
	}

	@Test
	public void queInteraccionEntreDosCriaturasDistintasSeEjecuteExitosamente() {
	    gestion.interactuarCriaturas("Apto", "AppaApto", "Apto", "Unagi");

	    CriaturaAncestral momoCompartida = new CriaturaAncestral("MomoComp", 100, Elementos.AIRE, 15);
	    gestion.agregarMaestroElemental(new MaestroElemental("M2", 30, Elementos.AIRE));
	    gestion.obtenerMaestroElemental("M2").agregarCriatura(momoCompartida);
	    
	    int energiaInicialAppa = appaApto.getEnergia(); 

	    gestion.interactuarCriaturas("Apto", "AppaApto", "M2", "MomoComp");

	    assertEquals(energiaInicialAppa - 15, appaApto.getEnergia().intValue()); 
	}

	// ------------------------------------------------------------------------------------------------

	@Test
	public void queObtenerCriaturaDevuelvaNullSiElMaestroNoExiste() {
	    InterfaceCriatura criaturaObtenida = gestion.obtenerCriatura("MaestroInexistente", "Appa");
	    
	    assertNull(criaturaObtenida);
	}

	@Test
	public void queObtenerCriaturaDevuelvaNullSiLaCriaturaNoExiste() {
	    InterfaceCriatura criaturaObtenida = gestion.obtenerCriatura("Apto", "Fantasma");
	    
	    assertNull(criaturaObtenida);
	}

	// ------------------------------------------------------------------------------------------------

	@Test
	public void queGetReportesDevuelvaUnaInstanciaDelGestorDeReportes() {
	    InterfaceReportes reportes = gestion.getReportes();
	    
	    assertNotNull(reportes);
	    assertTrue(reportes instanceof GestorDeReportes);
	}
}