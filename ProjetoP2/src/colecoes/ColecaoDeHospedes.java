package colecoes;

import java.util.ArrayList;
import java.util.List;
import classes.Hospede;

public class ColecaoDeHospedes {
	private List<Hospede> listaHospedes = new ArrayList<Hospede>();
	
	public List<Hospede> getListaHospedes() {
		return this.listaHospedes;
	}
	
	public int getListaHospedeTamanho() {
		return this.getListaHospedes().size();
	}
	
	public void adicionaHospede(Hospede hospede) throws Exception{
		if (hospede == null){
			throw new Exception("O hospede não foi adicionado.");
		}
		listaHospedes.add(hospede);
	}
	
	public void adicionaListaHospedes(List<Hospede> hospedes) throws Exception{
		if (hospedes == null || hospedes.contains(null)){
			throw new Exception("Os hospedes não foram adicionados.");
		}
		listaHospedes.addAll(hospedes);
	}
	
	public boolean removeHospede(Hospede hospede){
		return listaHospedes.remove(hospede);
	}
	
	public boolean removeHospedes(List<Hospede> hospedes) throws Exception{
		boolean removeu = false;
		for (Hospede hospede: hospedes){
			if (listaHospedes.contains(hospede)){
				listaHospedes.remove(hospede);
				removeu = true;
			}
		}
		return removeu;
	}
	
}
