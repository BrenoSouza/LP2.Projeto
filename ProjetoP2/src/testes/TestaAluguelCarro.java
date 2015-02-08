package testes;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import classes.AluguelCarro;

public class TestaAluguelCarro {

	private AluguelCarro aluguelCarroLuxo;
	private AluguelCarro aluguelCarroLuxoTanqueCheio;
	private AluguelCarro aluguelCarroLuxoComSeguro;	
	private AluguelCarro aluguelCarroExecutivo;
	private AluguelCarro aluguelCarroExecutivo2;
	private AluguelCarro aluguelCarroExecutivo3;
	
	@Before
	public void criaServicoAluguel() throws Exception {
		aluguelCarroLuxo = new AluguelCarro(1, true, false, false);
		aluguelCarroLuxoTanqueCheio = new AluguelCarro(1, true, true, false);
		aluguelCarroLuxoComSeguro = new AluguelCarro(1, true, false, true);
		aluguelCarroExecutivo = new AluguelCarro(1, false, false, false);
		aluguelCarroExecutivo2 = new AluguelCarro(10, false, false, false);
		aluguelCarroExecutivo3 = new AluguelCarro(10, false, true, true);
	}
	
	@Test
	public void testaErrosNoConstrutor() {
		try {
	         new AluguelCarro(-2, true, true, true);
	         Assert.fail("Esperava excecao o número de diárias informado é menor que 1!");
	      } catch (Exception e) {
	         Assert.assertEquals("Mensagem errada",
	               "Parametros inválidos!", e.getMessage());
	      }
	}
	
	@Test
	public void testaCalculaPrecoDoCarro() {
		Assert.assertTrue(aluguelCarroLuxo.calculaPrecoTotal() == 100.00);
		Assert.assertTrue(aluguelCarroLuxoTanqueCheio.calculaPrecoTotal() == 250.00);
		Assert.assertTrue(aluguelCarroLuxoComSeguro.calculaPrecoTotal() == 200.00);
		Assert.assertTrue(aluguelCarroExecutivo.calculaPrecoTotal() == 60.00);
		Assert.assertTrue(aluguelCarroExecutivo2.calculaPrecoTotal() == 600.00);
		Assert.assertTrue(aluguelCarroExecutivo3.calculaPrecoTotal() == 850.00);
	}
	
	
}
