package classes;

import java.util.Calendar;

	public class Restaurante extends Servico {

		private boolean isCobertura;
		private double precoDaConta;
		
		public Restaurante(Calendar data, boolean isCobertura) throws Exception {
			super(data);
			
			this.isCobertura = isCobertura;
			
		}
	
	@Override
	public double calculaPrecoTotal() {
		// TODO o m�todo todo.
		return 1.1;
	}
	
	public boolean getIsCobertura() {
		return isCobertura;
	}
	
}
