package core;

import gui.Main;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestaAluguelCarro {

	private AluguelCarro aluguelCarroLuxo, outroCarroLuxo;
	private AluguelCarro aluguelCarroLuxoTanqueCheio;
	private AluguelCarro aluguelCarroLuxoComSeguro;	
	private AluguelCarro aluguelCarroExecutivo;
	private AluguelCarro aluguelCarroExecutivo2;
	private AluguelCarro aluguelCarroExecutivo3;
	private Calendar dataInicio, dataFim;
	
	@Before
	public void criaServicoAluguel() throws IllegalArgumentException {
		aluguelCarroLuxo = new AluguelCarro(1, true, false, false);
		outroCarroLuxo = new AluguelCarro(1, true, false, false);
		aluguelCarroLuxoTanqueCheio = new AluguelCarro(1, true, true, false);
		aluguelCarroLuxoComSeguro = new AluguelCarro(1, true, false, true);
		aluguelCarroExecutivo = new AluguelCarro(1, false, false, false);
		aluguelCarroExecutivo2 = new AluguelCarro(10, false, false, false);
		aluguelCarroExecutivo3 = new AluguelCarro(10, false, true, true);
		dataInicio = Calendar.getInstance();
		dataFim = Calendar.getInstance();
	}
	
	@Test
	public void testaErrosNoConstrutor() {
		try {
	         new AluguelCarro(-2, true, true, true);
	         Assert.fail("Esperava excecao o número de diárias informado é menor que 1!");
	      } catch (IllegalArgumentException e) {
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
	
	@Test
	public void TesteToString() {
		//Executivo
		dataFim.set(Calendar.DAY_OF_MONTH, dataInicio.get(Calendar.DAY_OF_MONTH) + aluguelCarroExecutivo.getDiarias());
		Assert.assertEquals("Serviço --- Aluguel de Carro ---" +
				"\nInício -> " + Main.converteParaString(dataInicio) +
				"\nFim -> " + Main.converteParaString(dataFim) +
				"\nExtras -> Tanque cheio? Não\n          Segurado? Não\n          Luxo? Não" +
				"\nCusto final: 60.0" , aluguelCarroExecutivo.toString());
		//Luxo
		dataFim.set(Calendar.DAY_OF_MONTH, dataInicio.get(Calendar.DAY_OF_MONTH) + aluguelCarroLuxo.getDiarias());
		Assert.assertEquals("Serviço --- Aluguel de Carro ---" +
				"\nInício -> " + Main.converteParaString(dataInicio) +
				"\nFim -> " + Main.converteParaString(dataFim) +
				"\nExtras -> Tanque cheio? Não\n          Segurado? Não\n          Luxo? Sim" +
				"\nCusto final: 100.0" , aluguelCarroLuxo.toString());
	}
	
	@Test
	public void TesteEquals() {
		Assert.assertFalse(aluguelCarroLuxo.equals(aluguelCarroLuxoTanqueCheio));
		Assert.assertTrue(aluguelCarroLuxo.equals(outroCarroLuxo));
	}
	
	
}
