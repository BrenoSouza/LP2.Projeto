package core;

import gui.Main;

import java.io.Serializable;
import java.util.Calendar;

public class Babysitter extends Servico implements Serializable {

	private static final long serialVersionUID = 3158442945646329013L;
	private Calendar horaSaida;
	private int horasPrecoNormal;
	private int horasPrecoExtra;
	/**
	 * Construtor do serviço de babysitter.
	 */
	public Babysitter() {
		super();
		horaSaida = Calendar.getInstance();
		
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
				inicio += 1;
			}else{
				inicio = 1;
			}
			if (inicio > 18 || inicio < 7){
				horasPrecoExtra += 1;
			}else{
				horasPrecoNormal += 1;
			}
		}
	}
	/**
	 * Retorna uma String com a data de saída da babysitter.
	 * @return Uma String com a data de saída da babysitter.
	 */
	public String getFim() {
		String dataFormatada = Main.getFormatodata().format(horaSaida.getTime());
		return dataFormatada;
	}
	/**
	 * Retorna a hora de saída da babysitter.
	 * @return A hora de saída da babysitter.
	 */
	public int getHoraSaida() {
		return horaSaida.get(Calendar.HOUR_OF_DAY);
	}
	/**
	 * Estabelece a hora de saída da babysitter.
	 * @param horaSaida A hora de saída.
	 */
	public void setHoraSaida(int horaSaida) {
		this.horaSaida = Calendar.getInstance();
		this.horaSaida.add(Calendar.HOUR_OF_DAY, horaSaida);
	}
	/**
	 * Retorna as horas trabalhadas em horario normal.
	 * @return As horas trabalhadas em horario normal.
	 */
	public int getHorasPrecoNormal() {
		return horasPrecoNormal;
	}
	/**
	 * Retorna as horas trabalhadas em horario com preço dobrado.
	 * @return As horas trabalhadas em horario com preço dobrado.
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
		return "Serviço --- Babysitter ---" +
				Main.quebraDeLinha + "Início -> " + this.getInicioServico() + " Hora de entrada -> " + this.getHoraEntrada() +
				Main.quebraDeLinha + "Fim -> " + this.getFim() + " Hora de saída -> " + this.getHoraSaida() +
				Main.quebraDeLinha + "Horas -> " + this.getHorasPrecoNormal() + ", preço normal | " + this.getHorasPrecoExtra() + ", preço dobrado" +
				Main.quebraDeLinha + "Custo final: " + this.calculaPrecoTotal();
	}
	@Override
	public String getTipo() {
		return "Babysitter, " + (getHorasPrecoNormal() + getHorasPrecoExtra()) + " horas"; 
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Babysitter)){
			return false;
		}
		Babysitter outraBabysitter = (Babysitter) obj;
		return toString().equals(outraBabysitter.toString());
	}

}