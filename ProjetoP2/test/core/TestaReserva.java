package core;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import core.Reserva;

public class TestaReserva {
	Calendar dataCheckIn;
	Calendar dataCheckOut;
	Reserva reserva;
	Reserva outraReserva;
	
	@Before
	public void cria_objetos(){
		dataCheckIn = Calendar.getInstance();
		dataCheckOut = Calendar.getInstance();
		dataCheckOut.add(Calendar.DAY_OF_YEAR, 5);
		reserva = new Reserva(dataCheckIn, dataCheckOut);
		
		dataCheckIn = Calendar.getInstance();
		dataCheckIn.add(Calendar.DAY_OF_YEAR, 1);
		dataCheckOut = Calendar.getInstance();
		dataCheckOut.add(Calendar.DAY_OF_YEAR, 2);
		
		outraReserva = new Reserva(dataCheckIn, dataCheckOut);
	}
	@Test
	public void testa_equals(){
		Assert.assertFalse(reserva.equals(outraReserva));
		
		dataCheckIn = Calendar.getInstance();
		dataCheckOut = Calendar.getInstance();
		dataCheckOut.add(Calendar.DAY_OF_YEAR, 5);
		reserva = new Reserva(dataCheckIn, dataCheckOut);
		outraReserva = new Reserva(dataCheckIn, dataCheckOut);
		
		Assert.assertTrue(reserva.equals(outraReserva));
	}
	@Test
	public void testa_sobreposicao(){
		Assert.assertTrue(reserva.getIntervaloSobrepoe(outraReserva));
		
		dataCheckIn = Calendar.getInstance();
		dataCheckOut = Calendar.getInstance();
		dataCheckIn.add(Calendar.DAY_OF_YEAR, 70);
		dataCheckOut.add(Calendar.DAY_OF_YEAR, 75);
		outraReserva = new Reserva(dataCheckIn, dataCheckOut);
		
		Assert.assertFalse(reserva.getIntervaloSobrepoe(outraReserva));
		
	}
	
}
