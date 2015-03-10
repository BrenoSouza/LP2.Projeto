package core;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import junit.framework.Assert;

import org.joda.time.Interval;
import org.junit.Before;
import org.junit.Test;


@SuppressWarnings("deprecation")
public class TestaQuarto {

	Quarto quarto1, quarto2, quarto3;
	Calendar checkIn, checkOut;
	
	@Before
	public void antes_testes() throws IllegalArgumentException{
		quarto1 = new Quarto(1, TipoDeQuarto.EXECUTIVOTRIPLO);
		quarto2 = new Quarto(2, TipoDeQuarto.EXECUTIVOTRIPLO);
		quarto3 = new Quarto(3, TipoDeQuarto.PRESIDENCIAL);
		checkIn = Calendar.getInstance();
		checkOut = Calendar.getInstance();
		checkOut.setTime(checkIn.getTime());
		checkOut.add(Calendar.DAY_OF_YEAR, 2);
	}
	@Test
	public void testa_criacao(){
		try{
			quarto1 = new Quarto(-1, TipoDeQuarto.EXECUTIVODUPLO);
			Assert.fail("Número negativo de quarto, esperava excessão");
		}catch (IllegalArgumentException e){
			Assert.assertEquals(e.getMessage(),"O número de hospedes ou diarias ou numero do quarto nao pode ser menor que zero.");
		}
	}
	@Test
	public void testa_isLivre(){
		Assert.assertTrue(quarto1.isLivre());
		Assert.assertEquals(quarto1.getEstado(), "VAGO");
		quarto1.setToOcupado(2);
		Assert.assertFalse(quarto1.isLivre());		
	}
	@Test
	public void testa_reserva(){
		quarto1.adicionaReserva(new Reserva(checkIn, checkOut));
		Interval intervalo = new Interval(checkIn.getTimeInMillis(), checkOut.getTimeInMillis());
		Assert.assertFalse(quarto1.isLivreParaReserva(intervalo));
		intervalo = new Interval(checkIn.getTimeInMillis() + 5000000000000000L, checkOut.getTimeInMillis() + 50000000000000000L);
		Assert.assertTrue(quarto1.isLivreParaReserva(intervalo));
		List<Reserva> listaReservas = new ArrayList<Reserva>();
		listaReservas.add(new Reserva(checkIn, checkOut));
		Assert.assertEquals(quarto1.getListaReservas(), listaReservas);		
	}
	@Test
	public void testa_equals() throws IllegalArgumentException{
		Assert.assertFalse(quarto1.equals(quarto2));
		quarto2 = new Quarto(1, TipoDeQuarto.EXECUTIVOTRIPLO);
		Assert.assertTrue(quarto1.equals(quarto2));		
	}
	@Test
	public void testa_toString(){
		Assert.assertEquals(quarto1.toString(), "Serviço --- Quarto Executivo Triplo ---\nNúmero do quarto -> 1\nNúmero de hóspedes -> 3\nPreço da diária -> 440.0");
		Assert.assertEquals(quarto2.toString(), "Serviço --- Quarto Executivo Triplo ---\nNúmero do quarto -> 2\nNúmero de hóspedes -> 3\nPreço da diária -> 440.0");
		Assert.assertEquals(quarto3.toString(), "Serviço --- Quarto Presidencial ---\nNúmero do quarto -> 3\nNúmero de hóspedes -> 4\nPreço da diária -> 1200.0");
	}
}
