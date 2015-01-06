package classes;

public class Opiniao {
	private String comentario;
	private int nota;
	
	public Opiniao(String comentario, int nota){
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