package classes;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public abstract class Quarto extends Servico{
	private int numero, numeroHospedes, diarias;
	private double precoDiaria;
	private List<Hospede> listaHospedes = new ArrayList<Hospede>();
	private boolean camaExtra = false;
	
	public Quarto(int numero, int numeroHospedes, int diarias, Calendar data, double precoDiaria) throws Exception{
		super(data);
		this.numero = numero;
		this.numeroHospedes = numeroHospedes;
		this.diarias = diarias;
	}

	public int getNumero() {
		return numero;
	}

	public int getNumeroHospedes() {
		if (isCamaExtra()){
			return numeroHospedes + 1;
		}else{
			return numeroHospedes;
		}
	}

	public int getDiarias() {
		return diarias;
	}

	public List<Hospede> getListaHospedes() {
		return listaHospedes;
	}

	public boolean isCamaExtra() {
		return camaExtra;
	}

	public void setCamaExtra(boolean camaExtra){
		this.camaExtra = camaExtra;
	}
	
	public boolean adicionaHospede(Hospede hospede){
		//TODO excessões
		return listaHospedes.add(hospede);
	}
	public double getPrecoDiaria() {
		return precoDiaria;
	}

	public boolean removeHospede(Hospede hospede){
		//TODO excessões
		return listaHospedes.remove(hospede);
	}
	
	
	
	
}
