package core;

import java.io.Serializable;

public class Login implements Serializable {

	private String nome;
	private String senha;
	private String login;
	private String dica;
	
	public Login(String nome, String login, String senha, String dica) {
		
		this.nome = nome;
		this.senha = senha;
		this.login = login;
		this.dica = dica;
		
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setDica(String dica) {
		this.dica = dica;
	}

	public String getSenha() {
		return senha;
	}
	
	public String getLogin() {
		return login;
	}
	
	public String getDica() {
		return dica;
	}
	
	public String getNome() {
		return nome;
	}

}
