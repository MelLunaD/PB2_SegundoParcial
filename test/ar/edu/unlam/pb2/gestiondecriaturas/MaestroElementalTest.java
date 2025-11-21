package ar.edu.unlam.pb2.gestiondecriaturas;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MaestroElementalTest {
	private String nombreElegido = "Ash";
	private Integer nivelDeMaestriaElegido = 22;
	private Elementos miElemento = Elementos.FUEGO;
	private MaestroElemental maestro;
	
	@Before
	public void setUp() throws Exception {
		maestro = new MaestroElemental(nombreElegido, nivelDeMaestriaElegido, miElemento);
	}
	
	@Test
	public void queSePuedaCrearElMaestroElemental() {
		assertNotNull(maestro);
	}
	
	@Test
	public void queAlCrearElMaestroSeGuardenSusDatos() {
		assertTrue(maestro.getNombre().equals(nombreElegido));
	}
	
	// Para los Tests que arrojan una Exception
	// @Test(expected = MaestriaInsuficienteException.class)
}