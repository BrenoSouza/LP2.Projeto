package gui;

public interface Atualizador {

  /*
   * Interface feita pra se usar com JInternalFrames que abrem outros JInternalFrames.
   * Quando determinada ação acontece, o JInternalFrame filho chamará a função atualiza do pai, que lidará do seu próprio jeito.
   */
  
  /**
   * Método que atualiza a classe chamada, cada uma de seu jeito.
   */
  public void atualiza();
  
  
}
