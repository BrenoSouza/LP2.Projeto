package core;

import java.io.Serializable;

public class Opiniao implements Serializable {

	private static final long serialVersionUID = 1443649888865947750L;
	private String comentario = "";
	private int nota;
	/**
	 * Construtor de Opinião.
	 * @param comentario O comentário do hóspede sobre o hotel.
	 * @param nota A nota que o hóspede deu ao hotel.
	 * @throws ParametrosInvalidosException Caso o comentário tenha mais que 140 caracteres ou seja vazio e caso a nota seja menor que zero ou maior que 5.
	 */
	public Opiniao(String comentario, int nota) throws ParametrosInvalidosException{
		if (comentario.length() > 140 ||comentario.length() < 10 || comentario.isEmpty() || comentario == null){
			throw new ParametrosInvalidosException("O comentário não pode ser vazio e deve possuir no máximo 140 caracteres.");
		}
		if (nota < 0 || nota > 5){
			throw new ParametrosInvalidosException("A nota deve ser entre 0 e 5.");
		}
		this.comentario = comentario;
		this.nota = nota;
	}
	/**
	 * Retorna o comentário do hóspede sobre o hotel.
	 * @return O comentário do hóspede sobre o hotel.
	 */
	public String getComentario(){
		return this.comentario;
	}
	/**
	 * Retorna A nota que o hóspede deu para o hotel.
	 * @return A nota que hóspede deu para o hotel
	 */
	public int getNota(){
		return this.nota;
	}
	
	@Override
	public String toString() {
		return "Comentario: " + getComentario() + System.getProperty("line.separator") + "Nota: " + getNota();
	}
	@Override
	public boolean equals(Object obj){
		if (!(obj instanceof Opiniao)){
			return false;
		}Opiniao outraOpiniao = (Opiniao) obj;
		return toString().equals(outraOpiniao.toString());
	}
}