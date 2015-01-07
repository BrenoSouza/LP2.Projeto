package classes;

import java.util.Calendar;

public class Babysitter extends Servico {
	private Calendar horaEntrada;
	private Calendar horaSaida;
	private int horasPrecoNormal;
	private int horasPrecoExtra;
	
	public Babysitter(Calendar horaEntrada, int horaSaida) throws Exception {
		super(horaEntrada);
		this.horaEntrada = horaEntrada;
		this.horaSaida = Calendar.getInstance();
		this.horaSaida.set(Calendar.HOUR_OF_DAY, horaSaida);
	}
	
	public void horasTrabalhadas(){
		int inicio = horaEntrada.get(Calendar.HOUR_OF_DAY);
		int fim = horaSaida.get(Calendar.HOUR_OF_DAY);
		while (inicio != fim){
			if (inicio < 24){
				inicio ++;
			}else{
				inicio = 1;
			}
			if (inicio > 18 || inicio < 7){
				horasPrecoExtra++;
			}else{
				horasPrecoNormal++;
			}
		}
	}
	
	@Override
	public double calculaPrecoTotal() {
		return (horasPrecoExtra * 50) + (horasPrecoNormal * 25);
	}

}