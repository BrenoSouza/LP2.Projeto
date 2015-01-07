package classes;

import java.util.Calendar;

	public class Restaurante extends Servico {

		private boolean isCobertura;
		private double precoDaConta;
		
		public Restaurante(Calendar data, Calendar horaEntrada,
				Calendar horaSaida, int diarias, boolean isCobertura)
				throws Exception {
			super(data, horaEntrada, horaSaida, diarias);
			
			this.isCobertura = isCobertura;
			
		}
	
	@Override
	public double calculaPrecoTotal() {
		// TODO o método todo.
		return 1.1;
	}
	
	public boolean getIsCobertura() {
		return isCobertura;
	}
	
}
