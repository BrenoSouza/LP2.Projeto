package classes;

import java.util.Calendar;

	public class Restaurante extends Servico {

		private boolean isCobertura;
		private double precoComida;
		
		public Restaurante(Calendar data, boolean isCobertura, double precoComida) throws Exception {
			super(data);
			this.isCobertura = isCobertura;
			this.precoComida = precoComida;
		}
	
	@Override
	public double calculaPrecoTotal() {
		return precoComida;
	}
	
	public boolean getIsCobertura() {
		return isCobertura;
	}
	
}
