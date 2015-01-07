package classes;

import java.util.Calendar;

public class Babysitter extends Servico {
	
	static private int quantidadeCriancas;	
	
	public Babysitter(Calendar data, Calendar dataNascimento) throws Exception {
		super(data);
		
		if (!idadeEhValida(dataNascimento)) {
			throw new Exception("");
		}
		
		quantidadeCriancas++;
		
	}
	
	private boolean idadeEhValida(Calendar dataNascimento) {
		// TODO o m�todo todo
		return true;
	}
	
	@Override
	public double calculaPrecoTotal() {
		// TODO o m�todo todo
		return 1.1;
	}

}
