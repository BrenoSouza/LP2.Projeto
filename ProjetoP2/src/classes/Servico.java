package classes;

import java.util.Calendar;

public class Servico {
	
	private Calendar data;
	private Calendar horaEntrada;
	private Calendar horaSaida;
	private int diarias;
	
	public Servico(Calendar data, Calendar horaEntrada, Calendar horaSaida,
					int diarias) throws Exception {
		
		if (diarias <= 0) { 			//Faltam mais casos
			throw new Exception("");
		}
		
		this.data = data;
		this.horaEntrada = horaEntrada;
		this.horaSaida = horaSaida;
		this.diarias = diarias;
	
	}
	
	public Calendar getHoraEntrada() {
		return horaEntrada;	
	}
	
	public Calendar getHoraSaida() {
		return horaSaida;
	}
	
	public Calendar data() {
		return data;
	}
	
	public int getNumeroDiarias() {
		return diarias;
	}
	
	public void setHoraEntrada(Calendar horaEntrada) {
		this.horaEntrada = horaEntrada;
	}
	
	public void setHoraSaida(Calendar horaSaida) {
		this.horaSaida = horaSaida;
	}
	
	public void setData(Calendar data) {
		this.data = data;
	}

	public void setDiarias(int diarias) {
		this.diarias = diarias;
	}

}
