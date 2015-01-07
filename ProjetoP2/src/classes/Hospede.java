package classes;

import java.util.Calendar;

public class Hospede {

	private String nome, cpf;
	private Calendar dataNascimento;
	private Opiniao opiniao;
	private String residencia;
	
	/**
	 * Contrutor da classe Hospede.
	 * @param nome
	 * O nome do h�spede.
	 * @param cpf
	 * O CPF do h�spede.
	 * @param dataNascimento
	 * Um objeto Calendar contendo a data de nascimento do h�spede.
	 * @throws Exception
	 * Joga uma excess�o caso qualquer um dos atributos estiverem em formato inv�lido.
	 */
	public Hospede(String nome, String residencia, String cpf, Calendar dataNascimento) throws Exception{
		if (nome == null || residencia == null || residencia.equals("") || nome.isEmpty() || Hospede.isCpfValido(cpf) == false || dataNascimento == null){
			throw new Exception ("Dados inv�lidos. Tente novamente");
		}
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.opiniao = null;
		this.residencia = residencia;
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
	 * Um objeto Opiniao contendo a opini�o do h�spede sobre o hotel.
	 */
	public Opiniao getOpiniao() {
		return opiniao;
	}
	/**
	 * M�todo que checa se o CPF inserido � aceit�vel.
	 * @param cpf
	 * Uma String contendo um CPF.
	 * @return
	 * true, se o CPF for v�lido.
	 */
	static private boolean isCpfValido(String cpf){
		if (cpf.indexOf("-") != 9 || cpf.isEmpty() || cpf == null || cpf.length() != 12){
			return false;
		}return true;
	}
	
	public void setResidencia(String residencia) {
		this.residencia = residencia;
	}
	
	public String getResidencia() {
		return residencia;
	}
	
	@Override
	public String toString(){
		return "Nome: " + getNome() + ".\n"
				+ "CPF: " + getCpf() + ".\n"
				+ "Data de Nascimento: " + getDataNascimento() + ".\n"  // Ajustar depois a formata��o desse objeto Calendar
				+ ((getOpiniao() == null) ? "." : ("Opini�o: " + getOpiniao().toString() + "."));

	}

}
