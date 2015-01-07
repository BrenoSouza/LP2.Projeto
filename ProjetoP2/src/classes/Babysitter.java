package classes;

import java.util.Calendar;

public class Babysitter extends Servico {
	
	public Babysitter(Calendar data, Calendar horaEntrada, Calendar horaSaida,
			int diarias, Calendar idade) throws Exception {
		super(data, horaEntrada, horaSaida, diarias);
		
	}
	
	@Override
	public double calculaPrecoTotal() {
		return 1.1;
	}

}
