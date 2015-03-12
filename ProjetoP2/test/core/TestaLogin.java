package core;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestaLogin {

  private String nome = "Breno";
  private String senha = "12345";
  private String confirmaSenha = "12345";
  private String login = "breno";
  private String dica = "Padrão";
  private Login login1;
  private Login login2;
  
  @Before
  public void criaObjetos() {
    login1 = new Login(nome, login, senha, confirmaSenha, dica);
  }
  
  @Test
  public void testaExceções() {
    
    try {
      login2 = new Login("", login, senha, confirmaSenha, dica);
      Assert.fail("Esperava exceção, pois o nome é vazio!");

    } catch (ParametrosInvalidosException e) {
      Assert.assertEquals("Dados inválidos. Tente novamente.", e.getMessage());
    }

    
    try {
      login2 = new Login(nome, "", senha, confirmaSenha, dica);
      Assert.fail("Esperava exceção, pois o login é vazio!");

    } catch (ParametrosInvalidosException e) {
      Assert.assertEquals("Dados inválidos. Tente novamente.", e.getMessage());
    }
    
    try {
      login2 = new Login(nome, null, senha, confirmaSenha, dica);
      Assert.fail("Esperava exceção, pois o login é null!");

    } catch (ParametrosInvalidosException e) {
      Assert.assertEquals("Dados inválidos. Tente novamente.", e.getMessage());
    }
    
    try {
      login2 = new Login(nome, login, "", confirmaSenha, dica);
      Assert.fail("Esperava exceção, pois a senha é vazia!");

    } catch (ParametrosInvalidosException e) {
      Assert.assertEquals("Dados inválidos. Tente novamente.", e.getMessage());
    }
    
    try {
      login2 = new Login(nome, login, null, confirmaSenha, dica);
      Assert.fail("Esperava exceção, pois a senha é null!");
    } catch (ParametrosInvalidosException e) {
      Assert.assertEquals("Dados inválidos. Tente novamente.", e.getMessage());
    }
    
    try {
      login2 = new Login(nome, login, senha, "123", dica);
      Assert.fail("Esperava exceção, pois a senha e o confirma senha são diferentes!");

    } catch (ParametrosInvalidosException e) {
      Assert.assertEquals("As senhas não conferem! Digite novamente!", e.getMessage());
    }
    
    try {
      login2 = new Login(nome, login, senha, confirmaSenha, "");
      Assert.fail("Esperava exceção, pois a dica é vazia!");

    } catch (ParametrosInvalidosException e) {

    }
    
    try {
      login2 = new Login(nome, login, senha, confirmaSenha, null);
      Assert.fail("Esperava exceção, pois a dica é null!");

    } catch (ParametrosInvalidosException e) {
    
    }
    
  }
  
}
