package classes;

import java.util.Calendar;

public class QuartoExecutivoSimples extends Quarto {

	public QuartoExecutivoSimples(int numero, int diarias, Calendar data) throws Exception {
		super(numero, 3, diarias, data, 360.0);
	}

}
