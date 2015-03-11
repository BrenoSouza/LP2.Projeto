package core;

public class ParametrosInvalidosException extends IllegalArgumentException {

  private static final long serialVersionUID = -6448889645817914470L;
  
  public ParametrosInvalidosException(){
    super("Parâmetros inválidos foram passados para o construtor.");
  }
  public ParametrosInvalidosException(String mensagem){
    super(mensagem);
  }

}
