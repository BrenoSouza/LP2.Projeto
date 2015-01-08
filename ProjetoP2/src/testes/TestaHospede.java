package testes;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import classes.Hospede;

public class TestaHospede {

	private Hospede hospede;

	private Calendar dataNascimento = Calendar.getInstance();
	
	@Before
	public void criaHospede() throws Exception {
		hospede = new Hospede("Joao", "Brazil", "111111111-11", dataNascimento);
		
	}
	
	@Test
	public void testaErrosNoConstrutor() {		
		try {
	         new Hospede("", "Brazil", "111111111-11", dataNascimento);
	         Assert.fail("Esperava excecao, o nome está vazio!");
	      } catch (Exception e) {
	         Assert.assertEquals("Mensagem errada",
	               "Dados inválidos. Tente novamente", e.getMessage());
	      }
		
		try {
	         new Hospede("Joao", "", "111111111-11", dataNascimento);
	         Assert.fail("Esperava excecao, o endereço está vazio!");
	      } catch (Exception e) {
	         Assert.assertEquals("Mensagem errada",
	               "Dados inválidos. Tente novamente", e.getMessage());
	      }
		
		try {
	         new Hospede(null, "Brazil", "111111111-11", dataNascimento);
	         Assert.fail("Esperava excecao, o nome é um valor null!");
	      } catch (Exception e) {
	         Assert.assertEquals("Mensagem errada",
	               "Dados inválidos. Tente novamente", e.getMessage());
	      }
		
		try {
	         new Hospede("Joao", null, "111111111-11", dataNascimento);
	         Assert.fail("Esperava excecao, o endereço é um valor null!");
	      } catch (Exception e) {
	         Assert.assertEquals("Mensagem errada",
	               "Dados inválidos. Tente novamente", e.getMessage());
	      }
		
		try {
	         new Hospede("Joao", "Brazil", "11111111111", dataNascimento);
	         Assert.fail("Esperava excecao, o Cpf é inválido!");
	      } catch (Exception e) {
	         Assert.assertEquals("Mensagem errada",
	               "Dados inválidos. Tente novamente", e.getMessage());
	      }

		try {
	         new Hospede("Joao", "Brazil", "", dataNascimento);
	         Assert.fail("Esperava excecao, o Cpf é vazio!");
	      } catch (Exception e) {
	         Assert.assertEquals("Mensagem errada",
	               "Dados inválidos. Tente novamente", e.getMessage());
	      }   
		
		try {
	         new Hospede("Joao", "Brazil", "11111111-11", dataNascimento);
	         Assert.fail("Esperava excecao, o Cpf é inválido!");
	      } catch (Exception e) {
	         Assert.assertEquals("Mensagem errada",
	               "Dados inválidos. Tente novamente", e.getMessage());
	      }
		
		try {
	         new Hospede("Joao", "Brazil", "111111111-11", null);
	         Assert.fail("Esperava excecao, a data de nascimento é um valor null!");
	      } catch (Exception e) {
	         Assert.assertEquals("Mensagem errada",
	               "Dados inválidos. Tente novamente", e.getMessage());
	      }

		try {
	         new Hospede("Joao", "Brazil", null, dataNascimento);
	         Assert.fail("Esperava excecao, o CPF é um valor null!");
	      } catch (Exception e) {
	         Assert.assertEquals("Mensagem errada",
	               "Dados inválidos. Tente novamente", e.getMessage());
	      }
	
		try {
	         new Hospede("Joao", "Brazil", "1111c1111-11", dataNascimento);
	         Assert.fail("Esperava excecao, o CPF é um valor inválido!");
	      } catch (Exception e) {
	         Assert.assertEquals("Mensagem errada",
	               "Dados inválidos. Tente novamente", e.getMessage());
	      }
		
	}
		
	

}

