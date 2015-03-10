package core;

public enum TipoDeQuarto {
  EXECUTIVOSIMPLES (360.0, 3, "Quarto Executivo Simples"), EXECUTIVODUPLO (385.0, 3, "Quarto Executivo Duplo"), EXECUTIVOTRIPLO(440.0, 3, "Quarto Executivo Triplo"), LUXOSIMPLES (520.0, 3, "Quarto Luxo Simples"), LUXODUPLO(570.0, 3, "Quarto Luxo Duplo"), LUXOTRIPLO (620.0, 3, "Quarto Luxo Triplo"), PRESIDENCIAL(1200.0, 4, "Quarto Presidencial");
  private double precoDiaria;
  private String tipo;
  private int numeroHospedes;
  
  private TipoDeQuarto(double precoDiaria, int numeroHospedes, String tipo){
    this.precoDiaria = precoDiaria;
    this.numeroHospedes = numeroHospedes;
    this.tipo = tipo;
  }
  
  public double getPrecoDiaria(){
    return precoDiaria;
  }

  public String getTipo(){
    return tipo;
  }
  public int getNumeroHospedes(){
    return numeroHospedes;
  }
  @Override
  public String toString(){
    return tipo;
  }
}
