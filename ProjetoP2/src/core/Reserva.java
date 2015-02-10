package core;

import java.util.Calendar;

import org.joda.time.DateTime;
import org.joda.time.Interval;

public class Reserva {
	
	private DateTime dataCheckIn;
	private DateTime dataCheckOut;
	private Contrato contrato;
	private Interval intervalo;
	
	public Reserva(Contrato contrato){
		this.contrato = contrato;
		this.dataCheckIn = new DateTime(contrato.getDataCheckIn());
		this.dataCheckOut = new DateTime(contrato.getDataCheckOut());
		intervalo = new Interval(dataCheckIn, dataCheckOut);
	}
	public Reserva(Calendar dataCheckIn, Calendar dataCheckOut){
		this.dataCheckIn = new DateTime(dataCheckIn);
		this.dataCheckOut = new DateTime(dataCheckOut);
		intervalo = new Interval(this.dataCheckIn, this.dataCheckOut);
	}

	public DateTime getDataCheckIn() {
		return dataCheckIn;
	}

	public DateTime getDataCheckOut() {
		return dataCheckOut;
	}

	public Contrato getContrato() {
		return contrato;
	}

	public Interval getIntervalo() {
		return intervalo;
	}
	/**
	 * Método para saber se uma reserva está "sobrepondo" outra
	 * @param outroIntervalo
	 * Um intervalo de tempo.
	 * @return
	 * True se os intervalos se sobrepoem
	 */
	public boolean getIntervaloSobrepoe (Interval outroIntervalo){
		return (!(outroIntervalo.equals(intervalo)) && intervalo.overlaps(outroIntervalo));
	}
	/**
	 * Método para saber se uma reserva está "sobrepondo" outra
	 * @param outraReserva
	 * Outra reserva.
	 * @return
	 * True se os intervalos se sobrepoem
	 */
	public boolean getIntervaloSobrepoe(Reserva outraReserva){
		Interval outroIntervalo = outraReserva.getIntervalo();
		return (!(outroIntervalo.equals(intervalo)) && intervalo.overlaps(outroIntervalo));
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