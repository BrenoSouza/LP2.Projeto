package core;

import gui.Main;

import java.io.Serializable;
import java.util.Calendar;

public class Hospede implements Comparable<Hospede>, Serializable{

	private static final long serialVersionUID = -8295292718915935142L;
	private String nome, cpf;
	private Calendar dataNascimento;
	private Opiniao opiniao;
	private String endereco;
	private Contrato contratoLigado = null;


	/**
	 * Contrutor da classe Hospede.
	 * @param nome
	 * O nome do hóspede.
	 * @param endereco
	 * O endereço do Hospede.
	 * @param cpf
	 * O CPF do hóspede.
	 * @param dataNascimento
	 * Um objeto Calendar contendo a data de nascimento do hóspede.
	 * @throws ParametrosInvalidosException
	 * Joga uma excessão caso qualquer um dos atributos estiverem em formato inválido.
	 */
	public Hospede(String nome, String endereco, String cpf, Calendar dataNascimento) throws ParametrosInvalidosException{
		if (nome == null || endereco == null || endereco.isEmpty() || nome.isEmpty() || dataNascimento == null || cpf == null || cpf.equals("   .   .   -  ")){
			throw new ParametrosInvalidosException ("Dados inválidos. Tente novamente");
		}
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.opiniao = null;
		this.endereco = endereco;
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
	 * 
	 * @param nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
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
	 * Modifica o atributo de cpf do hospede.
	 * @param cpf O novo cpf do hospede
	 */
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	/**
	 * Getter da variável referente ao contrato ligado ao hóspede.
	 * @return
	 * O contrato ligado ao hóspede
	 */
	public Contrato getContratoLigado() {
		return contratoLigado;
	}
	/**
	 * Setter da variável referente ao contrato ligado ao hóspede.
	 * @param contratoLigado
	 * O contrato a ser ligado com o cliente
	 */
	public void setContratoLigado(Contrato contratoLigado) {
		this.contratoLigado = contratoLigado;
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
	 * Modifica a data de nascimento do hospede.
	 * @param dataNascimento Uma Calendar com a nova data de nascimento
	 */
	public void setDataNascimento(Calendar dataNascimento) {
		this.dataNascimento = dataNascimento;
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
	 * Uma objeto Opiniao contendo a opinião do hóspede sobre o hotel.
	 */
	public Opiniao getOpiniao() {
		return opiniao;
	}
	/**
	 * O setter do endereço do hóspede.
	 * @param endereco
	 * Uma String contendo o endereço do hóspede.
	 */
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	/**
	 * Getter o endereço do hóspede.
	 * @return
	 * Um String contendo o endereço do hóspede.
	 */
	public String getEndereco() {
		return endereco;
	}

	@Override
	public String toString(){
		return "Nome: " + getNome() + "." + System.getProperty("line.separator") + ""
				+ "CPF: " + getCpf() + "." + System.getProperty("line.separator") + ""
				+ "Endereço: " + getEndereco() + "." + System.getProperty("line.separator") + ""
				+ "Data de Nascimento: " + Main.converteParaString(getDataNascimento()) + "." + System.getProperty("line.separator") + "" 
				+ ((getOpiniao() == null) ? "" : ("Opinião: " + getOpiniao().toString() + "."));

	}
	/**
	 * método compareTo sobrescrito.
	 */
	public int compareTo(Hospede h) {
		return (this.getNome().compareTo(h.getNome()));
	}
	@Override
	public boolean equals(Object obj){
		if (!(obj instanceof Hospede)){
			return false;
		}Hospede outroHospede = (Hospede) obj;
		return toString().equals(outroHospede.toString());
	}

}
