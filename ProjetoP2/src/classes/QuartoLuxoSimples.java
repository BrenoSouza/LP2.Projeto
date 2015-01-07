package classes;

import java.util.Calendar;

public class QuartoLuxoSimples extends Quarto {

	public QuartoLuxoSimples(int numero, int numeroHospedes, int diarias, Calendar data) throws Exception {
		super(numero, 3, diarias, data, 520.0);
	}

}
