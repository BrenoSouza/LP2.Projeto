package classes;

import java.util.Calendar;

import org.joda.time.DateTime;

public class Reserva {
	
	private DateTime dataCheckIn;
	private DateTime dataCheckOut;
	private Contrato contrato;
	
	public Reserva(Contrato contrato){
		this.contrato = contrato;
		this.dataCheckIn = new DateTime(contrato.getDataCheckIn());
		this.dataCheckOut = new DateTime(contrato.getDataCheckOut());
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

}
