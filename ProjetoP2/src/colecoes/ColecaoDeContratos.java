package colecoes;

import java.util.ArrayList;
import java.util.List;
import classes.Contrato;

public class ColecaoDeContratos {
	private List<Contrato> listaContratos = new ArrayList<Contrato>();
	
	public List<Contrato> getListaContratos() {
		return this.listaContratos;
	}
	
	public int getListaContratoTamanho() {
		return listaContratos.size();
	}
	
	public void adicionaContrato(Contrato contrato) throws Exception{
		if (contrato == null || listaContratos.contains(contrato)){
			throw new Exception("O contrato não foi adicionado.");
		}
		listaContratos.add(contrato);
	}
	
	public void adicionaContratos(List<Contrato> contratos) throws Exception {
		if (contratos == null || contratos.contains(null) || listaContratos.containsAll(contratos)){
			throw new Exception("Os contratos não foram adicionados.");
		}
		listaContratos.addAll(contratos);
	}
	
	public boolean removeContrato(Contrato contrato) {
		return listaContratos.remove(contrato);
	}
	
	public boolean removeHospedes(List<Contrato> contratos) throws Exception{
		boolean removeu = false;
		for (Contrato contrato: contratos){
			if (listaContratos.contains(contrato)){
				listaContratos.remove(contrato);
				removeu = true;
			}
		}
		return removeu;
	}

}
