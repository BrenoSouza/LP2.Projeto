package core;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import core.Babysitter;

public class TestaBabysitter {
	private Calendar data;
	private Babysitter babySitter;
	private Babysitter outraBabysitter;
	
	@Before
	public void criaServicoBabysitter() {
		this.data = Calendar.getInstance();
		babySitter = new Babysitter();
		outraBabysitter = new Babysitter();
		babySitter.setHoraSaida(3);
		outraBabysitter.setHoraSaida(6);
	}
	
	@Test
	public void testMetodosBabySitter() {
		Assert.assertEquals(data.get(Calendar.HOUR_OF_DAY), babySitter.getHoraEntrada());
		Assert.assertEquals(data.get(Calendar.HOUR_OF_DAY) + 3, babySitter.getHoraSaida());
		babySitter.horasTrabalhadas();
		Assert.assertEquals((babySitter.getHorasPrecoNormal() * 25) + (babySitter.getHorasPrecoExtra() * 50), babySitter.calculaPrecoTotal(), 0.001);
	}
	
	@Test
	public void TesteToString() {
		babySitter.horasTrabalhadas();
		Assert.assertEquals("Serviço --- Babysitter ---" +
				"\nInício -> " + babySitter.getInicioServico() + " Hora de entrada -> " + babySitter.getHoraEntrada() +
				"\nFim -> " + babySitter.getFim() + " Hora de saída -> " + babySitter.getHoraSaida() +
				"\nHoras -> " + babySitter.getHorasPrecoNormal() + ", preço normal | " + babySitter.getHorasPrecoExtra() + ", preço dobrado" +
				"\nCusto final: " + babySitter.calculaPrecoTotal(), babySitter.toString());
	}
	
	@Test
	public void TesteEquals() {
		Assert.assertFalse(babySitter.equals(outraBabysitter));
		outraBabysitter.setHoraSaida(3);
		Assert.assertTrue(babySitter.equals(outraBabysitter));
	}
	
}