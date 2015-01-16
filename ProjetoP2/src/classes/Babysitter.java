package classes;

import gui.Main;

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
	public Babysitter() {
		super();
		
	}
	/**
	 * Calcula as horas trabalhadas da babysitter.
	 */
	public void horasTrabalhadas(){
		this.horasPrecoNormal = 0;
		this.horasPrecoExtra = 0;
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
	 * Retorna a data de saida da babysitter.
	 * @return A data de saida da babysitter.
	 */
	public String getFim() {
		String dataFormatada = Main.getFormatodata().format(horaSaida.getTime());
		return dataFormatada;
	}
	/**
	 * Retorna a hora de saida da babysitter.
	 * @return A hora de saida da babysitter.
	 */
	public int getHoraSaida() {
		return horaSaida.get(Calendar.HOUR_OF_DAY);
	}
	/**
	 * Estabelece a hora de saida da babysitter.
	 * @param horaSaida A hora de saida.
	 */
	public void setHoraSaida(int horaSaida) {
		this.horaSaida = Calendar.getInstance();
		this.horaSaida.set(Calendar.HOUR_OF_DAY, horaSaida);
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
	
	@Override
	public String toString(){
		horasTrabalhadas();
		return "Servico --- Babysitter ---" +
				"\nInicio -> " + this.getInicioServico() + " Hora de entrada ->" + this.getHoraEntrada() +
				"\nFim -> " + this.getFim() + " Hora de saida ->" + this.getHoraSaida() +
				"\nHoras ->" + this.getHorasPrecoNormal() + ", preco normal | " + this.getHorasPrecoExtra() + ", preco dobrado" +
				"\nCusto final: " + this.calculaPrecoTotal();
	}

}