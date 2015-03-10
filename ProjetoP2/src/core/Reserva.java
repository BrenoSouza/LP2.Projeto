package core;

import java.io.Serializable;
import java.util.Calendar;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Interval;

public class Reserva implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6743667760478686254L;
	private DateTime dataCheckIn;
	private DateTime dataCheckOut;
	private Contrato contrato;
	private Interval intervalo;
	/**
	 * Cria uma reserva com base nas datas de CheckIn e CheckOut de um contrato.
	 * @param contrato Um contrato
	 */
	public Reserva(Contrato contrato){
		this.contrato = contrato;
		this.dataCheckIn = new DateTime(contrato.getDataCheckIn()).withTimeAtStartOfDay();
		this.dataCheckOut = new DateTime(contrato.getDataCheckOut()).withTimeAtStartOfDay();
		if (dataCheckOut.isBefore(dataCheckIn)){ //Safeguard para o futuro.
		  this.dataCheckOut = dataCheckOut.plusYears(1);
		}
		intervalo = new Interval(dataCheckIn, dataCheckOut);
	}
	/**
	 * Cria uma reserva com base em datas de CheckIn e CheckOut defidas no construtor.
	 * @param dataCheckIn Um calendar com data de CheckIn
	 * @param dataCheckOut Um calendar com data de CheckOut
	 */
	public Reserva(Calendar dataCheckIn, Calendar dataCheckOut){
		this.dataCheckIn = new DateTime(dataCheckIn).withTimeAtStartOfDay();
		this.dataCheckOut = new DateTime(dataCheckOut).withTimeAtStartOfDay();
		if (this.dataCheckOut.isBefore(this.dataCheckIn)){ //Safeguard para o futuro. Evita ter um possível dataCheckOut antes de dataCheckOut, evitando quebrar na hora de criar o interval abaixo.
		  this.dataCheckOut = this.dataCheckOut.plusYears(1);
		  }
		intervalo = new Interval(this.dataCheckIn, this.dataCheckOut);
	}
	/**
	 * Retorna a data de checkIn da reserva.
	 * @return A data de checkIn
	 */
	public DateTime getDataCheckIn() {
		return dataCheckIn;
	}
	/**
	 * Retorna a data de checkOut da reserva.
	 * @return A data de checkOut
	 */
	public DateTime getDataCheckOut() {
		return dataCheckOut;
	}
	/**
	 * Retorna o contrato ligado a reserva.
	 * @return Um contrato
	 */
	public Contrato getContrato() {
		return contrato;
	}
	/**
	 * Setter do contrato da estratégia.
	 * @param contrato
	 * Um contrato.
	 */
	public void setContrato(Contrato contrato){
	  this.contrato = contrato;
	}
	/**
	 * Retorna o intervalo de tempo onde a reserva está ativa.
	 * @return Um Interval com o intervalo
	 */
	public Interval getIntervalo() {
		return intervalo;
	}
	/**
	 * Método para saber se uma reserva está "sobrepondo" outra.
	 * @param outroIntervalo
	 * Um intervalo de tempo.
	 * @return
	 * True se os intervalos se sobrepoem
	 */
	public boolean getIntervaloSobrepoe (Interval outroIntervalo){
		return (!(outroIntervalo.equals(intervalo)) && intervalo.overlaps(outroIntervalo));
	}
	/**
	 * Método para saber se uma reserva está "sobrepondo" outra.
	 * @param outraReserva
	 * Outra reserva.
	 * @return
	 * True se os intervalos se sobrepoem
	 */
	public boolean getIntervaloSobrepoe(Reserva outraReserva){
		Interval outroIntervalo = outraReserva.getIntervalo();
		return (!(outroIntervalo.equals(intervalo)) && intervalo.overlaps(outroIntervalo));
	}
	public int getNumeroDias(){
	  int dias = Days.daysBetween(getDataCheckIn().withTimeAtStartOfDay() , getDataCheckOut().withTimeAtStartOfDay() ).getDays();
	  if (dias == 0){
	    return 1; //Essa classe Days pode ter certos comportamentos estranhos, principalmente quando se leva em conta horário de verão e afins.
	  }return dias;
	}
	@Override
	public boolean equals(Object obj){
		if (!(obj instanceof Reserva)){
			return false;
		}
		Reserva outraReserva = (Reserva) obj;
		return this.getIntervalo().equals(outraReserva.getIntervalo());
	}

}
