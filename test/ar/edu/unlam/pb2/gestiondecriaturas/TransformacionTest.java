package ar.edu.unlam.pb2.gestiondecriaturas;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TransformacionTest {
	private Criatura momo;
	private Criatura appa;
	private Criatura unagi;
	private Criatura meow;
	private Criatura topo;
	private Criatura charmander;
	private LlamaInterna llamaInterna;

	@Before
	public void setUp() throws Exception {
		unagi = new CriaturaSalvaje("Unagi", 150, Elementos.AGUA, 40);
		momo = new CriaturaAncestral("Momo", 50, Elementos.AIRE, 15);
		appa = new CriaturaDomesticada("Appa", 40, Elementos.AIRE, 30);
		meow = new CriaturaDomesticada("Meow", 20, Elementos.TIERRA,35);
		topo = new CriaturaSalvaje("Topo", 100, Elementos.TIERRA, 30);
		charmander = new CriaturaSalvaje("charmander",100, Elementos.FUEGO, 20);
		llamaInterna = new LlamaInterna(charmander);
	}

	@Test
	public void queLasCriaturasExistan() {
		assertNotNull(momo);
		assertNotNull(appa);
		assertNotNull(unagi);
		assertNotNull(meow);
		assertNotNull(topo);
		assertNotNull(charmander);
	}
	
	@Test
	public void queSePuedaTransformarUnaCriatura() {
		assertNotNull(llamaInterna);
	}
	
	@Test
	public void queSePuedanTransformarMuchasVeces(){
		AscensoDelViento ascenso = new AscensoDelViento(llamaInterna);
		VinculoTerrestre vinculo = new VinculoTerrestre(ascenso);
		BendicionDelRio bendicion = new BendicionDelRio(vinculo);
		assertNotNull(ascenso);
		assertNotNull(vinculo);
		assertNotNull(bendicion);
	}
	
	@Test
	public void queSeCalculenCorrectamenteMultiplesTransformaciones() {
	    VinculoTerrestre meowConVinculo = new VinculoTerrestre(meow);
	    BendicionDelRio meowConBendicion = new BendicionDelRio(meowConVinculo);
	    Integer energiaEsperada = 100;
	    Integer energiaObtenida = meowConBendicion.getEnergia();
	    assertEquals(energiaEsperada, energiaObtenida);
	}
	
	@Test
	public void queLaTransformacionLlamaInternaAumenteLaEnergiaAUnaCriaturaDeFuego() {
		Integer energiaEsperada = (charmander.getEnergia() + 30);
		Integer energiaObtenida = llamaInterna.getEnergia();
		assertEquals(energiaEsperada, energiaObtenida);
	}
	
	@Test
	public void queLaTransformacioLlamaInternaVuelvaInestableAUnaCriaturaQueNoSeaDeFuego() {
		LlamaInterna momoConLlamaInterna = new LlamaInterna(momo);
		assertTrue(momoConLlamaInterna.getEstaInestable());
	}
	
	@Test
	public void queLaTransformacionLlamaInternaNoMofidiqueLaEnergiaDeLaCriaturaSiNoEsDeFuego() {
	    LlamaInterna unagiConLlamaInterna = new LlamaInterna(unagi);
	    Integer energiaEsperada = unagi.getEnergia();
	    Integer energiaObtenida = unagiConLlamaInterna.getEnergia();
	    assertEquals(energiaEsperada, energiaObtenida); 
	}
	
	@Test
	public void queLaTransformacionBendicionDelRioMultipliquePorDosLaEnergia() {
		BendicionDelRio appaConBendicion = new BendicionDelRio(appa);
		Integer energiaEsperada = (appa.getEnergia() * 2);
		Integer energiaObtenida = appaConBendicion.getEnergia();
		assertEquals(energiaEsperada, energiaObtenida);
	}
	
	@Test
	public void queLaTransformacionBendicionDelRioLimiteLaEnergiaA180() {
		BendicionDelRio topoConBendicion = new BendicionDelRio(topo);
		Integer energiaEsperada = 180;
		Integer energiaObtenida = topoConBendicion.getEnergia();
		assertEquals(energiaEsperada, energiaObtenida);
	}
	
	@Test
	public void queLaTransformacionBendicionDelRioLimiteLaEnergiaDeUnaCriaturaAncestral() {
		BendicionDelRio momoConBendicion = new BendicionDelRio(momo);
		Integer energiaEsperada = 180; //Al ser más de 200, la bendicion del rio debería limitarlo a 180.
		Integer energiaObtenida = momoConBendicion.getEnergia();
		assertEquals(energiaEsperada, energiaObtenida);
	}
		
	@Test
	public void queLaTransformacionVinculoTerrestreGaranticeQueLaEnergiaNoBajeDe50() {
		VinculoTerrestre meowConVinculo = new VinculoTerrestre(meow);
		Integer energiaEsperada = 50;
		Integer energiaObtenida = meowConVinculo.getEnergia();
		assertEquals(energiaEsperada, energiaObtenida);
	}
	
	@Test
	public void queElVinculoTerrestreNoModifiqueLaEnergiaSiEsMayorA50() {
	    VinculoTerrestre topoConVinculo = new VinculoTerrestre(topo);
	    Integer energiaEsperada = 100;
	    Integer energiaObtenida = topoConVinculo.getEnergia();
	    assertEquals(energiaEsperada, energiaObtenida);
	}
	
	@Test
	public void queLaTransformacionAscensoDelVientoConviertaTemporalmenteLaAfinidadAlTipoAire() {
		AscensoDelViento topoConAscenso = new AscensoDelViento(topo);
		assertEquals(Elementos.AIRE, topoConAscenso.getAfinidad());
	}
	
	@Test
	public void queLaTransformacionAscensoDelVientoNoHagaPerderLaAfinidadPrevia() {
		AscensoDelViento topoConAscenso = new AscensoDelViento(topo);
		assertEquals(Elementos.TIERRA, topoConAscenso.getCriaturaBase().getAfinidad());
	}
	
	@Test
	public void queLaTransformacionMantengaElNombreDeLaCriaturaOriginal() {
	    String nombreEsperado = "charmander";
	    String nombreObtenido = llamaInterna.getNombre(); 
	    assertEquals(nombreEsperado, nombreObtenido);
	}

	@Test
	public void queLaTransformacionDelegueLaPacificacion() {
	    Boolean resultadoEsperado = momo.pacificar(); 
	    LlamaInterna momoConLlama = new LlamaInterna(momo);
	    Boolean resultadoObtenido = momoConLlama.pacificar();
	    assertEquals(resultadoEsperado, resultadoObtenido);
	}

	@Test
	public void queLaTransformacionDelegueLaObtencionDeMaestriaMinima() {
	    Integer maestriaEsperada = charmander.getMaestriaMinimaRequerida();
	    Integer maestriaObtenida = llamaInterna.getMaestriaMinimaRequerida(); 
	    assertEquals(maestriaEsperada, maestriaObtenida);
	}
	
	@Test
	public void queLaTransformacionDelegueElEntrenamiento() throws Exception {
	    Integer maestriaSuficiente = 50;
	    llamaInterna.entrenar(maestriaSuficiente);
	    assertTrue(charmander.getEnergia() > 100); 
	}
	
	@Test
	public void queLaTransformacionDelegueElAumentoDeEnergia() {
	    llamaInterna.aumentarEnergia(20);
	    assertTrue(charmander.getEnergia() >= 120);
	}
	
	@Test
	public void queLaTransformacionDelegueVolverInestable() {
		Criatura vaporeon = new CriaturaAncestral("Vaporeon",100, Elementos.FUEGO, 20);
	    assertFalse(vaporeon.getEstaInestable());
		AscensoDelViento vaporeonConAcenso = new AscensoDelViento(vaporeon);
		vaporeonConAcenso.volverInestable();
	    assertTrue(vaporeon.getEstaInestable());
	}
	
	@Test
	public void queLaTransformacionDelegueSetEnergia() {
	    llamaInterna.setEnergia(150);
	    assertEquals((Integer)150, charmander.getEnergia()); 
	}
}
