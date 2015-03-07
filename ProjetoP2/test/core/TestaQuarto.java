package core;

//import java.time.Period;
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
		quarto1 = new QuartoExecutivoTriplo(1);
		quarto2 = new QuartoLuxoTriplo(2);
		quarto3 = new QuartoPresidencial(3);
		checkIn = Calendar.getInstance();
		checkOut = Calendar.getInstance();
		checkOut.setTime(checkIn.getTime());
		checkOut.add(Calendar.DAY_OF_YEAR, 2);
	}
	@Test
	public void testa_criacao(){
		try{
			quarto1 = new QuartoExecutivoTriplo(-1);
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
		quarto2 = new QuartoExecutivoTriplo(1);
		Assert.assertTrue(quarto1.equals(quarto2));		
	}
	@Test
	public void testa_calculaPrecoTotal(){
		Assert.assertEquals(quarto1.calculaPrecoTotal(), 0.0);
		quarto1.setToOcupado(2);
		Assert.assertFalse(quarto1.calculaPrecoTotal() == 0.0);
		Assert.assertEquals(quarto1.calculaPrecoTotal(), 880.0);
		quarto1.setToLivre();
		Assert.assertEquals(quarto1.calculaPrecoTotal(), 0.0);
	}
	@Test
	public void testa_toString(){
		Assert.assertEquals(quarto1.toString(), "Serviço --- Quarto Executivo Triplo ---\nNúmero do quarto -> 1\nNúmero de hóspedes -> 3\nDiárias -> 0\nPreço da diária -> 440.0\nCusto final -> 0.0");
		Assert.assertEquals(quarto2.toString(), "Serviço --- Quarto Executivo triplo ---\nNúmero do quarto -> 2\nNúmero de hóspedes -> 3\nDiárias -> 0\nPreço da diária -> 620.0\nCusto final -> 0.0");
		Assert.assertEquals(quarto3.toString(), "Serviço --- Quarto Presidencial ---\nNúmero do quarto -> 3\nNúmero de hóspedes -> 4\nDiárias -> 0\nPreço da diária -> 1200.0\nCusto final -> 0.0");
		quarto1.setToOcupado(3);
		Assert.assertEquals(quarto1.toString(), "Serviço --- Quarto Executivo Triplo ---\nNúmero do quarto -> 1\nNúmero de hóspedes -> 3\nDiárias -> 3\nPreço da diária -> 440.0\nCusto final -> 1320.0");
	}
}
