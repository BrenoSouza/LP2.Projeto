package classes;

import java.util.Calendar;

public class Babysitter extends Servico {
	private Calendar horaSaida;
	private int horasPrecoNormal;
	private int horasPrecoExtra;
	/**
	 * Construtor do servico de babysitter.
	 * @param horaEntrada A hora de entrada da babysitter.
	 * @param horaSaida A hora de saida da babysitter.
	 */
	public Babysitter(Calendar horaEntrada, int horaSaida) {
		super(horaEntrada);
		this.horaSaida = Calendar.getInstance();
		this.horaSaida.set(Calendar.HOUR_OF_DAY, horaSaida);
	}
	/**
	 * Calcula as horas trabalhadas da babysitter.
	 */
	public void horasTrabalhadas(){
		int inicio = super.getData().get(Calendar.HOUR_OF_DAY);
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
	/**
	 * Retorna a hora de saida da babysitter.
	 * @return A hora de saida da babysitter.
	 */
	public Calendar getHoraSaida() {
		return horaSaida;
	}
	/**
	 * Retorna as horas trabalhadas em horario normal.
	 * @return As horas trabalhadas em horario normal.
	 */
	public int getHorasPrecoNormal() {
		return horasPrecoNormal;
	}
	/**
	 * Retorna as horas trabalhadas em horario com preco dobrado.
	 * @return As horas trabalhadas em horario com preco dobrado.
	 */
	public int getHorasPrecoExtra() {
		return horasPrecoExtra;
	}
	
	@Override
	public double calculaPrecoTotal() {
		horasTrabalhadas();
		return (horasPrecoExtra * 50) + (horasPrecoNormal * 25);
	}

}