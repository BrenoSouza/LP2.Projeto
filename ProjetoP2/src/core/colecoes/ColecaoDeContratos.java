package core.colecoes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import core.Contrato;

public class ColecaoDeContratos implements Serializable {

	private static final long serialVersionUID = 2971770745024962292L;
	
	private List<Contrato> listaContratos = new ArrayList<Contrato>();
	/**
	 * Retorna a lista de contratos da colecao.
	 * @return Uma lista de contratos
	 */
	public List<Contrato> getListaContratos() {
		return this.listaContratos;
	}
	/**
	 * Informa o tamanho da lista da colecao/numero de contratos.
	 * @return Um int com o tamanho da lista
	 */
	public int getListaContratoTamanho() {
		return listaContratos.size();
	}
	/**
	 * Adiciona um contrato na colecao.
	 * @param contrato O contrato a ser adicionado
	 * @throws IllegalArgumentException Caso atinja as exceções da classe Contrato
	 */
	public void adicionaContrato(Contrato contrato) throws IllegalArgumentException{
		if (contrato == null || listaContratos.contains(contrato)){
			throw new IllegalArgumentException("O contrato não foi adicionado.");
		}
		listaContratos.add(contrato);
	}
	/**
	 * Adiciona uma lista de contratos a colecao.
	 * @param contratos Uma lista de contratos
	 * @throws IllegalArgumentException Caso atinga as exceções da classe Contrato
	 */
	public void adicionaContratos(List<Contrato> contratos) throws IllegalArgumentException {
		if (contratos == null || contratos.contains(null) || listaContratos.containsAll(contratos)){
			throw new IllegalArgumentException("Os contratos não foram adicionados.");
		}
		listaContratos.addAll(contratos);
	}
	/**
	 * Remove um contrato da colecao.
	 * @param contrato O contrato a ser removido
	 * @return True - se foi removido / False - se houve algum problema
	 */
	public boolean removeContrato(Contrato contrato) {
		return listaContratos.remove(contrato);
	}
	/**
	 * Remove uma lista de contratos da colecao.
	 * @param contratos uma lista de contratos
	 * @return True - se removeu algum contrato da colecao / False - se não removeu nenhum contrato
	 */
	public boolean removeHospedes(List<Contrato> contratos){
		boolean removeu = false;
		for (Contrato contrato: contratos){
			if (listaContratos.contains(contrato)){
				listaContratos.remove(contrato);
				removeu = true;
			}
		}
		return removeu;
	}

/*
 * Os métodos abaixos só seriam necessários se, quando criasse ou removesse uma nova estrategia, os contratos seriam modificados.
 * Como decidimos não ser necessário isso, comentei o código.
 * Se for necessário o uso depois, algumas correções precisarão ser feitas (não apenas descomentar).	
 */
	
//	/**
//	 * Método que adiciona uma estratégia nova nos contratos que sobrepoem o período da estratégia.
//	 * @param estrategia
//	 * A estratégia
//	 * @return
//	 * O número de contratos que foram modificados setando a estratégia.
//	 */
//	public int adicionaEstrategia(Estrategia estrategia){
//		int contratosModificados = 0;
//		for (Contrato contrato: listaContratos){
//			if (estrategia.contratoSobrepoe(contrato)){
//				contrato.setEstrategiaDoContrato(estrategia);
//				contratosModificados += 1;
//			}
//		}return contratosModificados;
//	}
//	/**
//	 * Método que remove estratégias dos contratos que a possuem.
//	 * @param estrategia
//	 * A estratégia a ser removida.
//	 * @return
//	 * O número de contratos que foram modificados.
//	 */
//	public int removeEstrategia(Estrategia estrategia){
//		int contratosModificados = 0;
//		for (Contrato contrato: listaContratos){
//			if (contrato.getEstrategiaDoContrato().equals(estrategia)){
//				contrato.setEstrategiaDoContrato(null);
//				contratosModificados += 1;
//			}
//		}return contratosModificados;
//	}
	
	/**
	 * Método que retorna uma ArrayList com os contratos cujo status são o mesmo da entrada.
	 * @param status
	 * O status a ser pesquisado.
	 * @return
	 * Uma ArrayList com os contratos cujo status são o mesmo da entrada.
	 */
	public List<Contrato> pesquisaStatusContrato(String status) {
		List<Contrato> pesquisados = new ArrayList<Contrato>();
		if (status != null) {
			for (Contrato c: listaContratos) {
				if (c.getStatus().equals(status)) {
					pesquisados.add(c);
				}
			}
		}
		return pesquisados;
	}
	
	public List<Contrato> pesquisaContratoCheckOut(int mes) {
	  Calendar anoAtual = Calendar.getInstance();
	  List<Contrato> pesquisados = new ArrayList<Contrato>();
	  if (mes >= 0 && mes <= 11) {
	    for (Contrato c: listaContratos) {
	      if (c.getDataCheckOut().get(Calendar.MONTH) == mes && c.getDataCheckOut().get(Calendar.YEAR) == anoAtual.get(Calendar.YEAR)) {
	        pesquisados.add(c);
	      }
	    }
	  }
	  return pesquisados;
	}

}
