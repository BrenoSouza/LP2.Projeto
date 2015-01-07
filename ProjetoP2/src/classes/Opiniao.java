package classes;

public class Opiniao {
	private String comentario;
	private int nota;
	/**
	 * Construtor de Opiniao.
	 * @param comentario O comentario do hospede sobre o hotel.
	 * @param nota A nota que o hospede deu ao hotel.
	 * @throws Exception Caso o comentario tenha mais que 140 caracteres ou seja vazio e caso a nota seja menor que zero ou maior que 5.
	 */
	public Opiniao(String comentario, int nota) throws Exception{
		if (comentario.length() > 140 || comentario.isEmpty() || comentario == null){
			throw new Exception("O comentario não pode ser vazio e deve possuir no maximo 140 caracteres.");
		}
		if (nota < 0 || nota > 5){
			throw new Exception("A nota deve ser entre 0 e 5.");
		}
		this.comentario = comentario;
		this.nota = nota;
	}
	/**
	 * Retorna o comentario do hospede sobre o hotel.
	 * @return O comentario do hospede sobre o hotel.
	 */
	public String getComentario(){
		return this.comentario;
	}
	/**
	 * Retorna A nota que o hospede deu para o hotel.
	 * @return A nota que hospede deu para o hotel
	 */
	public int getNota(){
		return this.nota;
	}
	
	@Override
	public String toString() {
		return "Comentario: " + getComentario() + "\nNota: " + getNota();
	}
}