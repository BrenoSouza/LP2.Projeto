package testes;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import classes.Opiniao;

public class TestaOpiniao {

	private Opiniao opiniao;
	
	@Before
	public void criaOpiniao() throws Exception {
		opiniao = new Opiniao("Muito bom!", 5);
	}
	
	@Test
	public void testaErrosNoConstrutor() {
		try {
			new Opiniao("Muito, Muito, Muito, Muito, Muito, Muito, Muito, Muito, "
					+ "Muito, Muito, Muito, Muito, Muito, Muito, Muito, Muito, Muito, "
					+ "Muito, Muito, Muito, Muito, Muito, Muito, Muito, Muito bom!", 5);
			Assert.fail("Esperava exceção, pois o comentário tem mais de 140 caracteres");
		} catch (Exception e) {
			Assert.assertEquals("Mensagem errada", "O comentario não pode ser vazio e deve possuir no maximo 140 caracteres.", e.getMessage());
		}
		
		try {
			new Opiniao("", 5);
			Assert.fail("Esperava exceção, pois o comentário está vazio.");
		} catch (Exception e) {
			Assert.assertEquals("Mensagem errada", "O comentario não pode ser vazio e deve possuir no maximo 140 caracteres.", e.getMessage());
		}
		
		try {
			new Opiniao("Muito bom!", 10);
			Assert.fail("Esperava exceção, pois a nota é maior que 5.");
		} catch (Exception e) {
			Assert.assertEquals("Mensagem errada", "A nota deve ser entre 0 e 5.", e.getMessage());
		}
		
		try {
			new Opiniao("Muito bom!", -1);
			Assert.fail("Esperava exceção, pois a nota é menor que 0.");
		} catch (Exception e) {
			Assert.assertEquals("Mensagem errada", "A nota deve ser entre 0 e 5.", e.getMessage());
		}
		
		
	}
	
	
	
}