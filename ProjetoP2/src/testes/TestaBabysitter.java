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
		babySitter.setHoraSaida(data.get(Calendar.HOUR_OF_DAY) + 3);
		Assert.assertEquals((data.get(Calendar.HOUR_OF_DAY) + 3), babySitter.getHoraSaida());
		Assert.assertEquals(125.0, babySitter.calculaPrecoTotal(), 0.001);
	}
	
}