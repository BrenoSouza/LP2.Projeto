package classes;

import java.util.Calendar;

public class Hospede {

	private String nome, cpf;
	private Calendar dataNascimento;
	private Opiniao opiniao;
	/**
	 * Contrutor da classe Hospede.
	 * @param nome
	 * O nome do hóspede.
	 * @param cpf
	 * O CPF do hóspede.
	 * @param dataNascimento
	 * Um objeto Calendar contendo a data de nascimento do hóspede.
	 * @throws Exception
	 * Joga uma excessão caso qualquer um dos atributos estiverem em formato inválido.
	 */
	public Hospede(String nome, String cpf, Calendar dataNascimento) throws Exception{
		if (nome == null|| nome.isEmpty() || cpf == null || cpf.isEmpty() || dataNascimento == null){
			throw new Exception ("Dados inválidos. Tente novamente");
		}
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.opiniao = null;
	}
	/**
	 * Getter do nome do hóspede.
	 * @return
	 * O nome do hóspede.
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * Getter do CPF do hóspede.
	 * @return
	 * O CPF do hóspede.
	 */
	public String getCpf() {
		return cpf;
	}
	/**
	 * Getter da data de nascimento do hóspede.
	 * @return
	 * Um objeto Calendar contendo a data de nascimento do hóspede.
	 */
	public Calendar getDataNascimento() {
		return dataNascimento;
	}
	/**
	 * O setter do parâmetro Opiniao do hóspede.
	 * @param opiniao
	 * Um objeto Opiniao contendo a opinião do hóspede sobre o hotel.
	 */
	public void setOpiniao(Opiniao opiniao){
		this.opiniao = opiniao;
	}
	/**
	 * O getter do parâmetro opinião do hóspede.
	 * @return
	 * Um objeto Opiniao contendo a opinião do hóspede sobre o hotel.
	 */
	public Opiniao getOpiniao() {
		return opiniao;
	}
//	static public boolean isCpfValido(String cpf){
//		if (cpf.indexOf("-") != 10 || cpf.isEmpty() || cpf == null || 
//	}
	@Override
	public String toString(){
		return "Nome: " + getNome() + ".\n"
				+ "CPF: " + getCpf() + ".\n"
				+ "Data de Nascimento: " + getDataNascimento() + ".\n"  // Ajustar depois a formatação desse objeto Calendar
				+ ((getOpiniao() == null) ? "." : ("Opinião: " + getOpiniao().toString() + "."));

	}

}
