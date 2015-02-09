package testes;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import classes.Babysitter;

public class TestaBabysitter {
	private Calendar data;
	private Babysitter babySitter, babySitter2;
	
	@Before
	public void criaServicoBabysitter() {
		this.data = Calendar.getInstance();
		babySitter = new Babysitter();
	}
	
	@Test
	public void testMetodosBabySitter() {
		Assert.assertEquals(data.get(Calendar.HOUR_OF_DAY), babySitter.getHoraEntrada());
		babySitter.setHoraSaida(data.get(Calendar.HOUR_OF_DAY) + 3);
		Assert.assertEquals((data.get(Calendar.HOUR_OF_DAY) + 3), babySitter.getHoraSaida());
		babySitter.horasTrabalhadas();
		Assert.assertEquals((babySitter.getHorasPrecoNormal() * 25) + (babySitter.getHorasPrecoExtra() * 50), babySitter.calculaPrecoTotal(), 0.001);
	}
	
	@Test
	public void TesteToString() {
		babySitter.setHoraSaida(data.get(Calendar.HOUR_OF_DAY) + 3);
		babySitter.horasTrabalhadas();
		Assert.assertEquals("Serviço --- Babysitter ---" +
				"\nInício -> " + babySitter.getInicioServico() + " Hora de entrada -> " + babySitter.getHoraEntrada() +
				"\nFim -> " + babySitter.getFim() + " Hora de saída -> " + babySitter.getHoraSaida() +
				"\nHoras -> " + babySitter.getHorasPrecoNormal() + ", preço normal | " + babySitter.getHorasPrecoExtra() + ", preço dobrado" +
				"\nCusto final: " + babySitter.calculaPrecoTotal(), babySitter.toString());
	}
	
}