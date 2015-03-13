package core;

import java.util.Calendar;
import gui.Main;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
		data.set(Calendar.HOUR_OF_DAY, babySitter.getHoraEntrada() + 3);
		Assert.assertEquals(data.get(Calendar.HOUR_OF_DAY), babySitter.getHoraSaida());
		babySitter.horasTrabalhadas();
		Assert.assertEquals((babySitter.getHorasPrecoNormal() * 25) + (babySitter.getHorasPrecoExtra() * 50), babySitter.calculaPrecoTotal(), 0.001);
	}
	
	@Test
	public void TesteToString() {
		babySitter.horasTrabalhadas();
		babySitter.toString();
		Assert.assertEquals("Serviço --- Babysitter ---" +
				Main.quebraDeLinha + "Início -> " + babySitter.getInicioServico() + " Hora de entrada -> " + babySitter.getHoraEntrada() +
				Main.quebraDeLinha + "Fim -> " + babySitter.getFim() + " Hora de saída -> " + babySitter.getHoraSaida() +
				Main.quebraDeLinha + "Horas -> " + babySitter.getHorasPrecoNormal() + ", preço normal | " + babySitter.getHorasPrecoExtra() + ", preço dobrado" +
				Main.quebraDeLinha + "Custo final: " + babySitter.calculaPrecoTotal(), babySitter.toString());
	}
	
	@Test
	public void TesteEquals() {
		Assert.assertFalse(babySitter.equals(outraBabysitter));
		outraBabysitter.setHoraSaida(3);
		Assert.assertTrue(babySitter.equals(outraBabysitter));
		outraBabysitter.setHoraSaida(6);
		Assert.assertFalse(babySitter.equals(outraBabysitter));
	}
	
}