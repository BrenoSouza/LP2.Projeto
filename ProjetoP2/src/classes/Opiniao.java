package classes;

public class Opiniao {
	private String comentario;
	private int nota;
	
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
	
	public String getComentario(){
		return this.comentario;
	}
	
	public int getNota(){
		return this.nota;
	}
	
	@Override
	public String toString() {
		return "Comentario: " + getComentario() + "\nNota: " + getNota();
	}
}