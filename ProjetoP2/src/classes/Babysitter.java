package classes;

import java.util.Calendar;

public class Babysitter extends Servico {
	
	static private int quantidadeCriancas;	
	
	public Babysitter(Calendar data, Calendar horaEntrada, Calendar horaSaida,
			int diarias, Calendar dataNascimento) throws Exception {
		super(data, horaEntrada, horaSaida, diarias);
		
		if (!idadeEhValida(dataNascimento)) {
			throw new Exception("");
		}
		
		quantidadeCriancas++;
		
	}
	
	private boolean idadeEhValida(Calendar dataNascimento) {
		// TODO o método todo
		return true;
	}
	
	@Override
	public double calculaPrecoTotal() {
		// TODO o método todo
		return 1.1;
	}

}
