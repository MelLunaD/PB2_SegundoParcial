package ar.edu.unlam.pb2.gestiondecriaturas;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MaestroElementalTest {
	private String nombreElegido = "Ash";
	private Integer nivelDeMaestriaElegido = 22;
	private Elementos elementoElegido = Elementos.FUEGO;
	private MaestroElemental maestro;
	private CriaturaDomesticada appa;
	private CriaturaSalvaje unagi;
	private CriaturaAncestral momo;
	
	@Before
	public void setUp() throws Exception {
		maestro = new MaestroElemental(nombreElegido, nivelDeMaestriaElegido, elementoElegido);
		appa = new CriaturaDomesticada("Appa", 150, Elementos.AIRE, 30);
		unagi = new CriaturaSalvaje("Unagi", 50, Elementos.AGUA, 40);
		momo = new CriaturaAncestral("Momo", 50, Elementos.AIRE, 15);
	}
	
	@Test
	public void queSePuedaCrearElMaestroElemental() {
		assertNotNull(maestro);
	}
	
	@Test
	public void queAlCrearElMaestroSeGuardenSusDatos() {
		assertEquals(nombreElegido, maestro.getNombre());
		assertEquals(nivelDeMaestriaElegido, maestro.getMaestria());
		assertEquals(elementoElegido, maestro.getAfinidadElemental());
		assertTrue(maestro.getColeccionDeCriaturas().isEmpty());
	}
	
	@Test
	public void queSePuedaAgregarUnaCriaturaALaColeccion() {
		maestro.agregarCriatura(appa);
	}
	
	@Test
	public void queSePuedanAgregarVariasCriaturasALaColeccion() {
		maestro.agregarCriatura(appa);
		maestro.agregarCriatura(unagi);
		maestro.agregarCriatura(momo);
	}
	
	@Test
	public void queNoSePuedanAgregarCriaturasConNombresRepetidosALaColeccion() {
		CriaturaSalvaje appa2 = new CriaturaSalvaje("Appa", 50, Elementos.AIRE, 20);
		maestro.agregarCriatura(appa);
		maestro.agregarCriatura(appa2);
		assertEquals(1, maestro.getColeccionDeCriaturas().size());
	}

	
	// Tests de entrenar criatura
	@Test
	public void queElMaestroPuedaEntrenarUnaCriaturaConMaestriaSuficiente() throws MaestriaInsuficienteException, EntrenamientoExtremoException {
	    MaestroElemental maestroApto = new MaestroElemental("Harry", 35, Elementos.TIERRA);
	    
	    maestroApto.agregarCriatura(appa); 
	    maestroApto.entrenarCriatura(appa.getNombre());
	    
	    assertEquals(160, appa.getEnergia(), 0.01); 
	}

	@Test(expected = MaestriaInsuficienteException.class)
	public void queAlEntrenarConMaestriaInsuficienteLanceMaestriaInsuficienteException() throws MaestriaInsuficienteException, EntrenamientoExtremoException {
	    maestro.agregarCriatura(appa);
	    
	    maestro.entrenarCriatura(appa.getNombre());
	}

	@Test(expected = EntrenamientoExtremoException.class)
	public void queAlEntrenarAncestralConMaestriaExtremaLanceEntrenamientoExtremoException() throws MaestriaInsuficienteException, EntrenamientoExtremoException {
	    MaestroElemental maestroExtremo = new MaestroElemental("Voldemort", 50, Elementos.FUEGO);
	    maestroExtremo.agregarCriatura(momo);
	    
	    maestroExtremo.entrenarCriatura(momo.getNombre());
	}

	@Test(expected = IllegalArgumentException.class)
	public void queAlIntentarEntrenarUnaCriaturaQueNoPoseeLanceException() throws MaestriaInsuficienteException, EntrenamientoExtremoException {
	    maestro.entrenarCriatura("MomoNoExiste");
	}
	
	// Tests de pacificar criatura.

	@Test
	public void queElMaestroPuedaPacificarUnaCriaturaAncestralInestable() {
	    momo.volverInestable();
	    maestro.agregarCriatura(momo);
	    
	    maestro.pacificarCriatura(momo.getNombre());
	    
	    assertFalse(momo.getEstaInestable());
	}

	@Test
	public void quePacificarUnaCriaturaSalvajeCambieElEstadoSoloSiHayExito() {
	    CriaturaSalvaje unagiControlada = new CriaturaSalvaje("UnagiControl", 50, Elementos.AGUA, 40, true, true);
	    maestro.agregarCriatura(unagiControlada);
	    
	    maestro.pacificarCriatura(unagiControlada.getNombre());
	    
	    assertFalse(unagiControlada.getEstaInestable());
	}
	
	// Tests de transformar criatura
	@Test
	public void queElMaestroPuedaTransformarUnaCriaturaConUnRitual() {
	    maestro.agregarCriatura(unagi);
	    
	    Transformacion bendicion = new BendicionDelRio(unagi);
	    maestro.transformarCriatura(unagi.getNombre(), bendicion);
	    InterfaceCriatura criaturaObtenida = maestro.obtenerCriatura(unagi.getNombre());
	    
	    assertTrue(criaturaObtenida instanceof BendicionDelRio);
	    assertEquals(100, criaturaObtenida.getEnergia(), 0.01); 
	}

	@Test(expected = IllegalArgumentException.class)
	public void queAlIntentarTransformarUnaCriaturaQueNoPoseeLanceException() {
	    Transformacion ritual = new BendicionDelRio(unagi); 
	    
	    maestro.transformarCriatura("Fantasma", ritual); 
	}
	
	@Test
	public void queDosMaestrosConDistintoNombreNoSeanIguales() {
	    MaestroElemental maestro1 = new MaestroElemental("Toph", 20, Elementos.TIERRA);
	    MaestroElemental maestro2 = new MaestroElemental("Katara", 20, Elementos.AGUA);
	    
	    assertNotEquals(maestro1.hashCode(), maestro2.hashCode()); 
	    assertNotEquals(maestro1, maestro2);
	}
	
	@Test
	public void queDosMaestrosConMismoNombreSeanConsideradosIguales() {
	    MaestroElemental maestro1 = new MaestroElemental("Zuko", 20, Elementos.FUEGO);
	    MaestroElemental maestro2 = new MaestroElemental("Zuko", 50, Elementos.AIRE);
	    
	    assertEquals(maestro1, maestro2); 
	    assertEquals(maestro1.hashCode(), maestro2.hashCode());
	}

	@Test
	public void quePacificarNoHagaNadaSiLaCriaturaYaEstaTranquila() {
	    maestro.agregarCriatura(appa); 
	    
	    maestro.pacificarCriatura(appa.getNombre());
	    
	    assertFalse(appa.getEstaInestable());
	}

	@Test
	public void quePacificarNoFalleSiSeBuscaUnaCriaturaQueNoExiste() {
	    try {
	        maestro.pacificarCriatura("Fantasma");
	    } catch (Exception e) {
	        fail("No debería haber lanzado ninguna excepción al buscar una criatura inexistente.");
	    }
	}
	
	// Tests para manejar el hashCode y el equals
	
	@Test
	public void queEqualsDevuelvaFalseCuandoElObjetoEsNull() {
	    assertFalse(maestro.equals(null)); // Cubre: if (obj == null)
	}

	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void queEqualsDevuelvaFalseCuandoElObjetoEsDeDistintaClase() {
	    String nombre = "Zuko";
	    assertFalse(maestro.equals(nombre)); // Cubre: if (getClass() != obj.getClass())
	}
}