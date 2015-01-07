package classes;

import java.util.Calendar;

public class QuartoExecutivoTriplo extends Quarto {

	public QuartoExecutivoTriplo(int numero, int diarias, Calendar data) throws Exception {
		super(numero, 3, diarias, data, 440.0);
	}

}
