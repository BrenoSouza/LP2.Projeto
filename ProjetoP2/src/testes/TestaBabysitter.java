package testes;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import classes.Babysitter;

public class TestaBabysitter {
	private Calendar data;
	private Babysitter babySitter;
	
	@Before
	public void criaServicoBabysitter() {
		this.data = Calendar.getInstance();
		babySitter = new Babysitter();
	}
	@Test
	public void testMetodosBabySitter() {
		Assert.assertEquals(data.get(Calendar.HOUR_OF_DAY), babySitter.getHoraEntrada());
		babySitter.setHoraSaida(2);
		Assert.assertEquals(2, babySitter.getHoraSaida());
		Assert.assertEquals(450.0, babySitter.calculaPrecoTotal(), 0.001);
		babySitter.horasTrabalhadas();
		Assert.assertEquals(2, babySitter.getHorasPrecoNormal());
		Assert.assertEquals(8, babySitter.getHorasPrecoExtra());
	}
	
}