package classes;

import java.util.Calendar;

public class Reserva {
	
	private Calendar dataCheckIn;
	private Calendar dataCheckOut;
	private Contrato contrato;
	
	public Reserva(Contrato contrato){
		this.contrato = contrato;
		this.dataCheckIn = contrato.getDataCheckIn();
		this.dataCheckOut = contrato.getDataCheckOut();
	}

	public Calendar getDataCheckIn() {
		return dataCheckIn;
	}

	public Calendar getDataCheckOut() {
		return dataCheckOut;
	}

	public Contrato getContrato() {
		return contrato;
	}

}
