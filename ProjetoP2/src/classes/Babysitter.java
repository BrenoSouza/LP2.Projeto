package classes;

import java.util.Calendar;

public class Babysitter extends Servico {
	
	static private int quantidadeCriancas;	
	private int quantidadeHoras;
	private int quantidadeHorasDobradas;
	
	
	public Babysitter(Calendar data, int quantidadeHoras, int quantidadeHorasDobradas , Calendar dataNascimento) throws Exception {
		super(data);
		
		if (!idadeEhValida(dataNascimento) || quantidadeHoras < 1 || quantidadeHorasDobradas < 1) {
			throw new Exception("Parametros invalidos!");
		}
		
		this.quantidadeHoras = quantidadeHoras;
		this.quantidadeHorasDobradas = quantidadeHorasDobradas;
		quantidadeCriancas++;
		
	}
	
	
	private boolean idadeEhValida(Calendar dataNascimento) {
		// TODO o m�todo todo
		return true;
	}
	
	private int getQuantidadeHoras() {
		return quantidadeHoras;
	}
	
	private int getQuantidadeHorasDobradas() {
		return quantidadeHorasDobradas;
	}
	
	private void setQuantidadeHoras(int quantidadeHoras) {
		this.quantidadeHoras = quantidadeHoras;
	}
	
	private void setQuantidadeHorasDobradas(int quantidadeHorasDobradas) {
		this.quantidadeHorasDobradas = quantidadeHorasDobradas;
	}
	
	@Override
	public double calculaPrecoTotal() {
		// TODO o m�todo todo
		return 1.1;
	}

}
