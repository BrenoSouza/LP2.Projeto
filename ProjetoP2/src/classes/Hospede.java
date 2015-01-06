package classes;

import java.util.Calendar;

public class Hospede {

	private String nome, cpf;
	private Calendar dataNascimento;
	private Opiniao opiniao;
	
	public Hospede(String nome, String cpf, Calendar dataNascimento){
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.opiniao = null;
	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

	public Calendar getDataNascimento() {
		return dataNascimento;
	}
	public void setOpiniao(Opiniao opiniao){
		this.opiniao = opiniao;
	}
	public Opiniao getOpiniao() {
		return opiniao;
	}
	@Override
	public String toString(){
		return "";
	}
	
}
