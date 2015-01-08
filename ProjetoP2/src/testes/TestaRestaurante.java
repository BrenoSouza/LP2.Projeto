package testes;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import classes.Restaurante;

public class TestaRestaurante {

	private Restaurante conta1;
	private Restaurante conta2;
	
	@Before
	public void criaConta() throws Exception {
		conta1 = new Restaurante(true, 100.00);
		conta2 = new Restaurante(false, 200.00);
	}
	
	@Test
	public void testaErrosNoConstrutor() {
		try {
	         new Restaurante(true, -1.0);
	         Assert.fail("Esperava excecao preco informado é negativo!");
	      } catch (Exception e) {
	         Assert.assertEquals("Mensagem errada",
	               "O preco da conta nao pode ser menor que zero.", e.getMessage());
	      }
	}
	
	@Test
	public void testaIsCobertura() {
		Assert.assertTrue(conta1.getIsCobertura());
		Assert.assertFalse(conta2.getIsCobertura());
	}
	
	@Test
	public void testaCalculaPrecoTotal() {
		Assert.assertTrue(conta1.calculaPrecoTotal() == 100.00);
		Assert.assertTrue(conta2.calculaPrecoTotal() == 200.00);	
	}
}
