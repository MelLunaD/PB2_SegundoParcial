package ar.edu.unlam.pb2.gestiondecriaturas;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GestionDeCriaturasTest {
	public GestionDeCriaturas gestion;

	public String nombreElegido = "Ash";
	public Integer nivelDeMaestriaElegido = 22;
	Elementos miElemento = Elementos.FUEGO;

	@Before
	public void setUp() throws Exception {
		gestion = new GestionDeCriaturas();
	}

	@Test
	public void queSePuedaCrearUnaGestionDeCriaturas() {
		assertNotNull(gestion);
	}
	
	@Test
	public void queSePuedaRegistrarUnMaestroElemental() {
		MaestroElemental ashKetchum = new MaestroElemental(nombreElegido, nivelDeMaestriaElegido, miElemento);
		//gestion.agregarMaestroElemental(ashKetchum);
	}
}