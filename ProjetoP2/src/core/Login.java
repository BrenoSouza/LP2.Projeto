package core;

import java.io.Serializable;

public class Login implements Serializable{

	private static final long serialVersionUID = -1716949112678460565L;
	private String nome;
	private String senha;
	private String confirmaSenha;
	private String login;
	private String dica;
	
	/**
	 * Construtor da classe login.
	 * @param nome
	 * O nome do usuário da conta.
	 * @param login
	 * O login do usuário da conta.
	 * @param senha
	 * A senha do usuário da conta.
	 * @param confirmaSenha
	 * A senha do usuário da conta.
	 * @param dica
	 * Uma dica de senha.
	 */
	public Login(String nome, String login, String senha, String confirmaSenha, String dica) throws ParametrosInvalidosException {
		
	  if (nome.equals("") || nome == null || login == null || login.equals("") || senha == null || senha.equals("") ||
	      confirmaSenha == null || confirmaSenha.equals("") || dica == null || dica.equals("") || verificaSenha(senha, confirmaSenha)) {
      throw new ParametrosInvalidosException ("Dados inválidos. Tente novamente.");
	  }
	  
		this.nome = nome;
		this.senha = senha;
		this.confirmaSenha = confirmaSenha;
		this.login = login;
		this.dica = dica;
		
	}
	/**
	 * Setter do nome do usuário da conta.
	 * @param nome
	 * Nome do usuário da conta.
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	/**
	 * Setter da senha da conta.
	 * @param senha
	 * A nova senha da conta.
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}
	/**
	 * Setter do login da conta.
	 * @param login
	 * O novo login da conta.
	 */
	public void setLogin(String login) {
		this.login = login;
	}
	/**
	 * Setter da dica da conta.
	 * @param dica
	 * A nova dica da conta.
	 */
	public void setDica(String dica) {
		this.dica = dica;
	}
	/**
	 * Getter da senha da conta.
	 * @return
	 * A senha da conta.
	 */
	public String getSenha() {
		return senha;
	}
	/**
	 * Getter do login da conta.
	 * @return
	 * O login da conta.
	 */
	public String getLogin() {
		return login;
	}
	/**
	 * Getter da dica da senha da conta.
	 * @return
	 * A dica da senha da conta.
	 */
	public String getDica() {
		return dica;
	}
	/**
	 * Getter do nome do usuário da conta.
	 * @return
	 * O nome do usuário da conta.
	 */
	public String getNome() {
		return nome;
	}
	
	private boolean verificaSenha(String senha, String confirmaSenha) {
	  if (senha.equals(confirmaSenha)) {
	    return true;
	  }
	  return false;
	}

}
