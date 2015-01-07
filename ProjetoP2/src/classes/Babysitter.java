package classes;

import java.util.Calendar;

public class Babysitter extends Servico {
	
	static private int quantidadeCriancas;	
	private Calendar horaSaida;
	private Calendar dataNascimento;
	private int quantidadeHoras;
	private int quantidadeHorasDobradas;
	
	public Babysitter(Calendar data, Calendar horaSaida, Calendar dataNascimento) throws Exception {
		super(data);
		
		if (!idadeEhValida(dataNascimento)) {
			throw new Exception("Parametros invalidos!");
		}
		
		this.horaSaida = horaSaida;
		this.dataNascimento = dataNascimento;
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
	
	@Override
	public double calculaPrecoTotal() {
		// TODO o m�todo todo
		return 1.1;
	}

}
