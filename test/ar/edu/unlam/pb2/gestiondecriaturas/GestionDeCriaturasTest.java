package ar.edu.unlam.pb2.gestiondecriaturas;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GestionDeCriaturasTest {
	public GestionDeCriaturas gestion;

	@Before
	public void setUp() throws Exception {
		gestion = new GestionDeCriaturas();
	}

	@Test
	public void queSePuedaCrearUnaGestionDeCriaturas() {
		assertNotNull(gestion);
	}
}