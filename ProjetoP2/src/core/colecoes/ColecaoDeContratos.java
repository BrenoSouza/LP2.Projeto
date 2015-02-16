package core.colecoes;

import java.util.ArrayList;
import java.util.List;

import core.Contrato;
import core.Estrategia;

public class ColecaoDeContratos {
	private List<Contrato> listaContratos = new ArrayList<Contrato>();
	/**
	 * Retorna a lista de contratos da colecao
	 * @return Um List<Contrato>
	 */
	public List<Contrato> getListaContratos() {
		return this.listaContratos;
	}
	/**
	 * Informa o tamanho da lista da colecao/numero de contratos
	 * @return Um int com o tamanho da lista
	 */
	public int getListaContratoTamanho() {
		return listaContratos.size();
	}
	/**
	 * Adiciona um contrato na colecao
	 * @param contrato O contrato a ser adicionado
	 * @throws Exception Caso atinga as exceções da classe Contrato
	 */
	public void adicionaContrato(Contrato contrato) throws Exception{
		if (contrato == null || listaContratos.contains(contrato)){
			throw new Exception("O contrato não foi adicionado.");
		}
		listaContratos.add(contrato);
	}
	/**
	 * Adiciona uma lista de contratos a colecao
	 * @param contratos Um List<Contrato>
	 * @throws Exception Caso atinga as exceções da classe Contrato
	 */
	public void adicionaContratos(List<Contrato> contratos) throws Exception {
		if (contratos == null || contratos.contains(null) || listaContratos.containsAll(contratos)){
			throw new Exception("Os contratos não foram adicionados.");
		}
		listaContratos.addAll(contratos);
	}
	/**
	 * Remove um contrato da colecao
	 * @param contrato O contrato a ser removido
	 * @return True - se foi removido / False - se houve algum problema
	 */
	public boolean removeContrato(Contrato contrato) {
		return listaContratos.remove(contrato);
	}
	/**
	 * Remove uma lista de contratos da colecao
	 * @param contratos Um List<Contratos>
	 * @return True - se removeu algum contrato da colecao / False - se não removeu nenhum contrato
	 * @throws Exception Caso atinga as exceções da classe Contrato
	 */
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
	/**
	 * Método que adiciona uma estratégia nova nos contratos que sobrepoem o período da estratégia.
	 * @param estrategia
	 * A estratégia
	 * @return
	 * O número de contratos que foram modificados setando a estratégia.
	 */
	public int adicionaEstrategia(Estrategia estrategia){
		int contratosModificados = 0;
		for (Contrato contrato: listaContratos){
			if (estrategia.contratoSobrepoe(contrato)){
				contrato.setEstrategiaDoContrato(estrategia);
				contratosModificados++;
			}
		}return contratosModificados;
	}
	/**
	 * Método que remove estratégias dos contratos que a possuem.
	 * @param estrategia
	 * A estratégia a ser removida.
	 * @return
	 * O número de contratos que foram modificados.
	 */
	public int removeEstrategia(Estrategia estrategia){
		int contratosModificados = 0;
		for (Contrato contrato: listaContratos){
			if (contrato.getEstrategiaDoContrato().equals(estrategia)){
				contrato.setEstrategiaDoContrato(null);
				contratosModificados++;
			}
		}return contratosModificados;
	}

}
