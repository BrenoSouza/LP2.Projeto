package core;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestaRestaurante {

	private Restaurante servicoRestaurante1;
	private Restaurante servicoRestaurante2;
	
	@Before
	public void criaServico() throws IllegalArgumentException {
		servicoRestaurante1 = new Restaurante(true, 100.00);
		servicoRestaurante2 = new Restaurante(false, 200.00);
	}
	
	@Test
	public void testaErrosNoConstrutor() {
		try {
	         new Restaurante(true, -1.0);
	         Assert.fail("Esperava excecao preco informado é negativo!");
	      } catch (IllegalArgumentException e) {
	         Assert.assertEquals("Mensagem errada",
	               "O preço da conta nao pode ser menor que zero.", e.getMessage());
	      }
	}
	
	@Test
	public void testaCalculaPrecoTotal() {
		Assert.assertTrue(servicoRestaurante1.calculaPrecoTotal() == 100.00);
		servicoRestaurante1.setPreco(50.0);
		Assert.assertTrue(servicoRestaurante1.calculaPrecoTotal() == 50.0);
		Assert.assertTrue(servicoRestaurante2.calculaPrecoTotal() == 200.00);	
	}
	
	@Test
	public void TesteToString() {
		Assert.assertEquals("Serviço --- Restaurante ---\nHora -> " + Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + "\nCobertura? Sim\nCusto final: 100.0", servicoRestaurante1.toString());
	}
	
	@Test
	public void TestEquals() {
		Assert.assertFalse(servicoRestaurante1.equals(servicoRestaurante2));
		servicoRestaurante1.setPreco(200.00);
		servicoRestaurante1.setCobertura(false);
		Assert.assertTrue(servicoRestaurante1.equals(servicoRestaurante2));
	}
}
