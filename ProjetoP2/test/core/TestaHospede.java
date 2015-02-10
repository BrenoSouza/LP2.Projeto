package core;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import core.Hospede;
import core.Opiniao;

public class TestaHospede {

	private Hospede hospede;
	private Hospede outroHospede;

	private Calendar dataNascimento = Calendar.getInstance();
	private Calendar segundaDataNascimento = Calendar.getInstance();
	Opiniao opiniao;
	
	@Before
	public void criaHospede() throws Exception {
		hospede = new Hospede("Joao", "Brazil", "111111111-11", dataNascimento);
		segundaDataNascimento.setTime(dataNascimento.getTime());
		segundaDataNascimento.add(Calendar.DAY_OF_YEAR, 2);
		outroHospede = new Hospede("Jaiminho", "Tagamandápio", "111111111-12", segundaDataNascimento);
		opiniao = new Opiniao("Odiei, hotel lixo, 5 estrelas", 5);
		
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
	         new Hospede("Joao", "Brazil", "   .   .   -  ", dataNascimento);
	         Assert.fail("Esperava excecao, o Cpf é vazio!");
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
		
	}
	@Test
	public void testa_setters() throws Exception{
		Assert.assertEquals(hospede.getNome(), "Joao");
		hospede.setNome("Fulano de tal");
		Assert.assertEquals(hospede.getNome(), "Fulano de tal");
		Assert.assertEquals(hospede.getCpf(), "111111111-11");
		hospede.setCpf("222222222-22");
		Assert.assertEquals(hospede.getCpf(), "222222222-22");
		Assert.assertEquals(hospede.getDataNascimento(), dataNascimento);
		hospede.setDataNascimento(segundaDataNascimento);
		Assert.assertEquals(hospede.getDataNascimento(), segundaDataNascimento);
		hospede.setOpiniao(opiniao);
		Assert.assertEquals(hospede.getOpiniao(), opiniao);
		Assert.assertEquals(hospede.getEndereco(), "Brazil");
		hospede.setEndereco("Lua, colônia espacial nº 5");
		Assert.assertEquals(hospede.getEndereco(), "Lua, colônia espacial nº 5");
	}
	@Test
	public void testa_equals() throws Exception{
		Assert.assertFalse(hospede.equals(outroHospede));
		outroHospede = new Hospede("Joao", "Brazil", "111111111-11", dataNascimento);
		Assert.assertTrue(hospede.equals(outroHospede));
		outroHospede.setNome("Maria");
		Assert.assertFalse(hospede.equals(outroHospede));
	}
	
}

