package core.colecoes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import core.Contrato;
import core.Estrategia;
import core.ParametrosInvalidosException;

public class ColecaoDeEstrategias implements Serializable{
	

	private static final long serialVersionUID = -1785850243417643058L;
	private List<Estrategia> listaEstrategias = new ArrayList<Estrategia>();
	/**
	 * Método que retorna o tamanho da lista de estratégias.
	 * @return
	 * O tamanho da lista de estratégias.
	 */
	public int getListaEstrategiasTamanho(){
		return listaEstrategias.size();
	}
	/**
	 * Método para adicionar uma estratégia a lista de estratégias.
	 * @param estrategia
	 * Uma estratégia a ser adicionada.
	 * @throws ParametrosInvalidosException
	 * Se a estratégia for null, já existir na lista de estratégias ou sobrepoe um intervalo de outra estratégia.
	 */
	public void adicionaEstrategia(Estrategia estrategia) throws ParametrosInvalidosException{
		if (estrategia == null || listaEstrategias.contains(estrategia)){
			throw new ParametrosInvalidosException("A estratégia não foi adicionado");
		}
		for (Estrategia outraEstrategia: listaEstrategias){
			if (outraEstrategia.sobrepoe(estrategia)){
				throw new ParametrosInvalidosException("A estratégia inclui um período referente a outra estratégia existente");
			}
		}
		
		listaEstrategias.add(estrategia);
	}
	/**
	 * Método de remoção de estratégias.
	 * @param estrategia
	 * A estratégia a ser removida.
	 * @return
	 * true se a estratégia tiver sido removida
	 */
	public boolean removeEstrategia(Estrategia estrategia){
		return listaEstrategias.remove(estrategia);
	}
	/**
	 * Método para checar se existe alguma estratégia em vigor.
	 * @return
	 * Um objeto Estrategia contendo a estratégia em vigor, null se não houver nenhuma estratégia em vigor.
	 */
	public Estrategia estrategiaAtual(){
		for (Estrategia estrategia: listaEstrategias){
			if (estrategia.periodoContemPresente()){
				return estrategia;
			}
		}return null;
	}
	public Estrategia get(int i){
		return listaEstrategias.get(i);
	}
	public List<Estrategia> checaContratoComEstrategia(Contrato contrato){
	  List<Estrategia> listaEstrategiasContrato = new ArrayList<Estrategia>();
		for (Estrategia estrategia: listaEstrategias){
			if (estrategia.contratoSobrepoe(contrato)){
			  listaEstrategiasContrato.add(estrategia);
			}
		}return listaEstrategiasContrato;
	}
}
