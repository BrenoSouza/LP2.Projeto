package core;

public class Login {

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
	
	public String getSenha() {
		return senha;
	}
	
	public String getLogin() {
		return login;
	}

	
	public String getDica() {
		return dica;
	}

}
