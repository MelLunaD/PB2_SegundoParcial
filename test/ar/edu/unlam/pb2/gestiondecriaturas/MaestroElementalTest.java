package ar.edu.unlam.pb2.gestiondecriaturas;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MaestroElementalTest {
	public String nombreElegido = "Ash";
	public Integer nivelDeMaestriaElegido = 22;
	Elementos miElemento = Elementos.FUEGO;
	
	public MaestroElemental maestro;

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
}