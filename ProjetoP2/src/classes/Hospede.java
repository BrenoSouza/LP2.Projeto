package classes;

import java.util.Calendar;

public class Hospede {

	private String nome, cpf;
	private Calendar dataNascimento;
	private Opiniao opiniao;
	private String endereco;
	
	/**
	 * Contrutor da classe Hospede.
	 * @param nome
	 * O nome do h�spede.
	 * @param endereco
	 * O endereço do Hospede.
	 * @param cpf
	 * O CPF do h�spede.
	 * @param dataNascimento
	 * Um objeto Calendar contendo a data de nascimento do h�spede.
	 * @throws Exception
	 * Joga uma excess�o caso qualquer um dos atributos estiverem em formato inv�lido.
	 */
	public Hospede(String nome, String endereco, String cpf, Calendar dataNascimento) throws Exception{
		if (nome == null || endereco == null || endereco.isEmpty() || nome.isEmpty() || dataNascimento == null || cpf == null || cpf.equals("   .   .   -  ")){
			throw new Exception ("Dados inv�lidos. Tente novamente");
		}
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.opiniao = null;
		this.endereco = endereco;
	}
	
	/**
	 * Getter do nome do h�spede.
	 * @return
	 * O nome do h�spede.
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * Getter do CPF do h�spede.
	 * @return
	 * O CPF do h�spede.
	 */
	public String getCpf() {
		return cpf;
	}
	
	/**
	 * Getter da data de nascimento do h�spede.
	 * @return
	 * Um objeto Calendar contendo a data de nascimento do h�spede.
	 */
	public Calendar getDataNascimento() {
		return dataNascimento;
	}
	
	/**
	 * O setter do par�metro Opiniao do h�spede.
	 * @param opiniao
	 * Um objeto Opiniao contendo a opini�o do h�spede sobre o hotel.
	 */
	public void setOpiniao(Opiniao opiniao){
		this.opiniao = opiniao;
	}
	
	/**
	 * O getter do par�metro opini�o do h�spede.
	 * @return
	 * Uma objeto Opiniao contendo a opini�o do h�spede sobre o hotel.
	 */
	public Opiniao getOpiniao() {
		return opiniao;
	}
	
	/**
	 * O setter do endereco do h�spede.
	 * @param endereco
	 * Uma String contendo o endereço do h�spede.
	 */
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	/**
	 * Getter o endereço do h�spede.
	 * @return
	 * Um String contendo o endereço do h�spede.
	 */
	public String getEndereco() {
		return endereco;
	}
	
	@Override
	public String toString(){
		return "Nome: " + getNome() + ".\n"
				+ "CPF: " + getCpf() + ".\n"
				+ "Endereço: " + getEndereco() + ".\n"
				+ "Data de Nascimento: " + getDataNascimento() + ".\n"  // Ajustar depois a formata��o desse objeto Calendar
				+ ((getOpiniao() == null) ? "." : ("Opini�o: " + getOpiniao().toString() + "."));

	}

}
