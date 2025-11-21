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
		unagi = new CriaturaSalvaje("Unagi", 150, Elementos.AGUA, 40);
		momo = new CriaturaAncestral("Momo", 50, Elementos.AIRE, 15);
		appa = new CriaturaDomesticada("Appa", 150, Elementos.AIRE, 30);
		shaw = new CriaturaAncestral("Shaw", 200, Elementos.FUEGO, 45);
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
	public void queAlCrearUnaCriaturaSeGuardenSusDatos() {
		assertEquals("Unagi", unagi.getNombre());
		assertEquals((Integer) 150, unagi.getEnergia());
		assertEquals(Elementos.AGUA, unagi.getAfinidad());
		assertEquals(Criatura.INESTABLE, unagi.getEstaInestable());
	}
	
	// Para los Tests que arrojan una Exception
	// @Test(expected = MaestriaInsuficienteException.class)
}
