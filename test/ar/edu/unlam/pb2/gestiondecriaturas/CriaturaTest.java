package ar.edu.unlam.pb2.gestiondecriaturas;

import static org.junit.Assert.*;

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
		unagi = new CriaturaSalvaje("Unagi", 150, Elementos.AGUA, Criatura.INESTABLE);
		momo = new CriaturaAncestral("Momo", 50, Elementos.AIRE, Criatura.TRANQUILO);
		appa = new CriaturaDomesticada("Appa", 150, Elementos.AIRE);
		shaw = new CriaturaAncestral("Shaw", 200, Elementos.FUEGO, Criatura.INESTABLE);
		topo = new CriaturaSalvaje("Topo", 100, Elementos.TIERRA, Criatura.TRANQUILO);
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
	public void queAlCrearUnaCriaturaSeGuardenSusDatos() {
		assertEquals("Unagi", unagi.getNombre());
		assertEquals((Integer) 150, unagi.getEnergia());
		assertEquals(Elementos.AGUA, unagi.getAfinidad());
		assertEquals(Criatura.INESTABLE, unagi.getEstaInestable());
	}
}
