package ar.edu.unlam.pb2.gestiondecriaturas;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class CriaturaTest {
	private Criatura momo;
	private Criatura appa;
	private Criatura unagi;
	private Criatura shaw;
	private Criatura topo;

	@Before
	public void setUp() throws Exception {
		unagi = new CriaturaSalvaje("Unagi", 50, Elementos.AGUA, 40);
		momo = new CriaturaAncestral("Momo", 50, Elementos.AIRE, 15);
		appa = new CriaturaDomesticada("Appa", 150, Elementos.AIRE, 30);
		shaw = new CriaturaAncestral("Shaw", 180, Elementos.FUEGO, 45);
		topo = new CriaturaSalvaje("Topo", 100, Elementos.TIERRA, 30);
	}

	@Test
	public void queSePuedaCrearUnaCriatura() {
		assertNotNull(momo);
		assertNotNull(appa);
		assertNotNull(unagi);
		assertNotNull(shaw);
		assertNotNull(topo);
	}
	
	@Test
	public void queLaEnergiaInicialNoPuedaSerNegativa() {
	    Integer energiaNegativa = -50; 
	    
	    CriaturaSalvaje topoConEnergiaNegativa = new CriaturaSalvaje(
	        "Topo", energiaNegativa, Elementos.TIERRA, 10
	    );
	    
	    assertEquals(0, topoConEnergiaNegativa.getEnergia().intValue()); 
	}
	
	@Test
	public void queLaEnergiaInicialNoPuedaExcederElMaximo() {
	    Integer energiaExcedida = 250; 
	    
	    CriaturaAncestral shawExcedida = new CriaturaAncestral(
	        "Shaw", energiaExcedida, Elementos.FUEGO, 40
	    );
	    
	    assertEquals(200, shawExcedida.getEnergia().intValue()); 
	}
	
	@Test
	public void queUnaCriaturaDomesticadaOAncestralSeInicialicenTranquilas() {
		assertFalse(appa.getEstaInestable());
	}
	
	@Test
	public void queUnaCriaturaSalvajeSeInicialiceInestable() {
		assertTrue(unagi.getEstaInestable());
	}
	
	@Test
	public void queAlCrearUnaCriaturaSeGuardenSusDatos() {
		assertEquals("Unagi", unagi.getNombre());
		assertEquals((Integer) 50, unagi.getEnergia());
		assertEquals(Elementos.AGUA, unagi.getAfinidad());
		assertEquals(Criatura.INESTABLE, unagi.getEstaInestable());
		assertEquals(40, unagi.getMaestriaMinimaRequerida(), 0.01);

		assertEquals("Momo", momo.getNombre());
		assertEquals((Integer) 100, momo.getEnergia());
		assertEquals(Elementos.AIRE, momo.getAfinidad());
		assertEquals(Criatura.TRANQUILA, momo.getEstaInestable());
		assertEquals(15, momo.getMaestriaMinimaRequerida(), 0.01);

		assertEquals("Appa", appa.getNombre());
		assertEquals((Integer) 150, appa.getEnergia());
		assertEquals(Elementos.AIRE, appa.getAfinidad());
		assertEquals(Criatura.TRANQUILA, appa.getEstaInestable());
		assertEquals(30, appa.getMaestriaMinimaRequerida(), 0.01);
	}
	
	@Test
	public void queSePuedanActualizarLosDatosDeUnaCriatura() {
		topo.setNombre("Topo2.0");
		topo.setEnergia(150);
		topo.setAfinidad(Elementos.AIRE);
		topo.volverInestable();
		topo.setMaestriaMinimaRequerida(45);

		assertEquals("Topo2.0", topo.getNombre());
		assertEquals(150, topo.getEnergia(), 0.01);
		assertEquals(Elementos.AIRE, topo.getAfinidad());
		assertTrue(topo.getEstaInestable());
		assertEquals(45, topo.getMaestriaMinimaRequerida(), 0.01);

		topo.setAfinidadTemporal(Elementos.TIERRA);
		assertEquals(Elementos.TIERRA, topo.getAfinidad());
	}
	
	@Test
	public void pacificarCriaturaSalvajeDebeSerExitosaCuandoElTestLoRequiere() {
		// Usamos una variable de control para forzar el Booleano y poder Testearlo
		CriaturaSalvaje topoSalvaje = new CriaturaSalvaje("Topo", 100, Elementos.TIERRA, 10, true, true); 

	    assertTrue(topoSalvaje.pacificar());
	    assertFalse(topoSalvaje.getEstaInestable());
	}

	@Test
	public void pacificarCriaturaSalvajeDebeFallarCuandoElTestLoRequiere() {
	    CriaturaSalvaje topoSalvaje = new CriaturaSalvaje("Topo", 100, Elementos.TIERRA, 10, true, false);
	    topoSalvaje.volverInestable(); 

	    assertFalse(topoSalvaje.pacificar());
	    assertTrue(topoSalvaje.getEstaInestable());
	}
	
	
	// Tuvimos que generar esto para llenar el Coverage del Random que creamos
	@Test
	public void pacificarEnProduccionDebeSerExitosoCuandoRandomDevuelveTrue() {
	    Random randomFalso = new Random() {
			private static final long serialVersionUID = 1L;

			@Override
	        public boolean nextBoolean() {
	            return true;
	        }
	    };
	    
	    CriaturaSalvaje topo = new CriaturaSalvaje("TopoProd", 100, Elementos.TIERRA, 10);
	    topo.setRandomGenerator(randomFalso);
	    topo.volverInestable(); 

	    assertTrue(topo.pacificar()); 
	    assertFalse(topo.getEstaInestable());
	}
	
	@Test
	public void pacificarEnProduccionDebeFallarCuandoRandomDevuelveFalse() {
	    Random randomFalso = new Random() {
			private static final long serialVersionUID = 1L;

			@Override
	        public boolean nextBoolean() {
	            return false;
	        }
	    };
	    
	    CriaturaSalvaje topo = new CriaturaSalvaje("TopoProd", 100, Elementos.TIERRA, 10);
	    topo.setRandomGenerator(randomFalso);
	    topo.volverInestable(); 

	    assertFalse(topo.pacificar()); 
	    assertTrue(topo.getEstaInestable());
	}
	
	@Test
	public void queAlEntrenarUnaCriaturaSalvajeAumenteSuEnergiaDeManeraImpredecible() throws IllegalStateException, EntrenamientoExtremoException {
		unagi.entrenar(45);
		assertTrue(unagi.getEnergia() >= 65);
	}
	
	@Test(expected = IllegalStateException.class)
	public void queAlEntrenarUnaCriaturaSalvajeYSupereElUmbralDeEnergiaLanceUnaException() throws IllegalStateException, EntrenamientoExtremoException {
		CriaturaSalvaje salmonDorado = new CriaturaSalvaje("Unagi", 190, Elementos.AGUA, 40);
		salmonDorado.entrenar(50);
	}
	
	@Test
	public void queAlEntrenarUnaCriaturaDomesticadaAumenteSuEnergiaDeManeraEstable() throws EntrenamientoExtremoException {
		Integer energiaInicial = appa.getEnergia();
		appa.entrenar(50);
		assertEquals(energiaInicial + 10, appa.getEnergia(), 0.01);
		appa.entrenar(50);
		assertEquals(energiaInicial + 20, appa.getEnergia(), 0.01);
		appa.entrenar(50);
		assertEquals(energiaInicial + 30, appa.getEnergia(), 0.01);
	}
	
	@Test
	public void queAlIntentarInestabilizarAUnaCriaturaDomesticadaSeMantengaTranquila() {
		appa.volverInestable();
		assertFalse(appa.getEstaInestable());
	}
	
	@Test
	public void queLaEnergiaDeUnaCriaturaAncestralNuncaBajeDe100() {
		shaw.setEnergia(50);
		assertEquals(100, shaw.getEnergia(), 0.01);
	}
	
	@Test(expected = EntrenamientoExtremoException.class)
	public void queAlEntrenarUnaCriaturaAncestralConUnaMaestriaDeAltoNivelArrojeException() throws EntrenamientoExtremoException {
		shaw.entrenar(50);
	}
	
	@Test
	public void queAlEntrenarUnaCriaturaAncestralConUnaMaestriaAdecuadaAumenteSuEnergia() throws EntrenamientoExtremoException {
		shaw.entrenar(20);
		assertEquals(190, shaw.getEnergia(), 0.01);
	}
	
	@Test
	public void queAlPacificarUnaCriaturaDomesticaSeMantengaTranquila() {
		assertFalse(appa.getEstaInestable());
		appa.pacificar();
		assertFalse(appa.getEstaInestable());
	}
	
	@Test
	public void queSetEnergiaIgnoreValoresMayoresA200() {
		Integer energiaBase = appa.getEnergia();
	    appa.setEnergia(250);                    
	    assertEquals(energiaBase, appa.getEnergia());
	}
	
	@Test
	public void queSetEnergiaIgnoreValoresNegativos() {
	    Integer energiaBase = appa.getEnergia();
	    
	    appa.setEnergia(-10);

	    assertEquals(energiaBase, appa.getEnergia());
	}

	@Test
	public void queGetCriaturaBaseDevuelvaLaInstanciaBase() {
	    InterfaceCriatura base = momo.getCriaturaBase();
	    
	    assertSame(momo, base); 
	}
	
	@Test
	public void queAumentarEnergiaIgnoreCantidadesIgualesACero() {
	    Integer energiaInicial = momo.getEnergia();
	    
	    momo.aumentarEnergia(0);
	    
	    assertEquals(energiaInicial, momo.getEnergia());
	}
	
	@Test
	public void queAumentarEnergiaIgnoreCantidadesNegativas() {
	    Integer energiaInicial = momo.getEnergia();
	    
	    momo.aumentarEnergia(-50);
	    
	    assertEquals(energiaInicial, momo.getEnergia());
	}
	
	@Test
	public void quePacificarNoAltereElEstadoSiYaEstaTranquila() {
	    assertFalse(momo.getEstaInestable()); 
	    
	    momo.pacificar();
	    
	    assertFalse(momo.getEstaInestable()); 
	    
	    momo.volverInestable();
	    
	    assertTrue(momo.getEstaInestable());
	}
	
	@Test
	public void queSetMaestriaMinimaIgnoreValoresMenoresAUno() {
	    Integer maestriaInicial = appa.getMaestriaMinimaRequerida();

	    appa.setMaestriaMinimaRequerida(0); 

	    assertEquals(maestriaInicial, appa.getMaestriaMinimaRequerida());
	}
	
	@Test
	public void queSetMaestriaMinimaIgnoreValoresMayoresACincuenta() {
	    Integer maestriaInicial = appa.getMaestriaMinimaRequerida();
	    
	    appa.setMaestriaMinimaRequerida(55);

	    assertEquals(maestriaInicial, appa.getMaestriaMinimaRequerida());
	}

	// Tests para manejar el hashCode y el equals
	
	@Test
	public void queDosCriaturasConMismoNombreSeanConsideradasIguales() {
	    Criatura momo2 = new CriaturaSalvaje("Momo", 150, Elementos.FUEGO, 10);
	    
	    assertEquals(momo, momo2); 
	    assertEquals(momo.hashCode(), momo2.hashCode());
	}
	
	@Test
	public void queDosCriaturasConDistintoNombreNoSeanIguales() {
	    assertNotEquals(momo, appa);
	    assertNotEquals(momo.hashCode(), appa.hashCode()); 
	}
	
	@Test
	public void queEqualsDevuelvaFalseCuandoElObjetoEsNull() {
	    assertFalse(momo.equals(null)); 
	}

	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void queEqualsDevuelvaFalseCuandoElObjetoEsDeDistintaClase() {
	    MaestroElemental maestro = new MaestroElemental("Boss", 50, Elementos.AIRE);
	    assertFalse(momo.equals(maestro)); 
	}

	@Test
	public void queEqualsDevuelvaTrueSiComparaConsigoMismo() {
	    assertTrue(momo.equals(momo)); 
	}
}
