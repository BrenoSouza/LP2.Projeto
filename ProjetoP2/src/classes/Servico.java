package classes;

import java.util.Calendar;

public abstract class Servico {
	
	private Calendar data;
	
	public Servico(Calendar data) throws Exception {
		this.data = Calendar.getInstance();
	}
	
	public abstract double calculaPrecoTotal();
	
	public Calendar getData() {
		return data;
	}
	
	
}
