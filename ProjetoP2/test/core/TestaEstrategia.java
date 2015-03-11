package core;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestaEstrategia {
  private DateTime inicioPeriodo;
  private DateTime finalPeriodo;
  private Estrategia estrategia1, estrategia2;

  @Before
  public void criaObjetos(){
    inicioPeriodo = new DateTime();
    finalPeriodo = new DateTime();
    finalPeriodo.plusDays(1);
    estrategia1 = new Estrategia(inicioPeriodo, finalPeriodo, 50, Estrategia.ACRESCIMO, "Qualquer coisa");
    estrategia2 = new Estrategia(inicioPeriodo.plusDays(2), finalPeriodo.plusDays(2), 20, Estrategia.DECRESCIMO, "Outra coisa");
  }
  
  @Test
  public void testa_equals(){
    Assert.assertFalse(estrategia1.equals(estrategia2));
    
    estrategia2 = new Estrategia(inicioPeriodo, finalPeriodo, 50, Estrategia.ACRESCIMO, "Qualquer coisa");
    
    Assert.assertTrue(estrategia1.equals(estrategia2));
    
    estrategia2 = new Estrategia(inicioPeriodo, finalPeriodo, 50, Estrategia.ACRESCIMO, "Agora quebra");
    
    Assert.assertFalse(estrategia1.equals(estrategia2));
    
  }
  @Test
  public void testa_excessoes(){
    try{
      estrategia1 = new Estrategia(inicioPeriodo, finalPeriodo, -1, Estrategia.ACRESCIMO, "Será que vai cair na excessão?");
      Assert.fail("Uma excessão deveria ter sido jogada, pois o modificador foi -1");
    }catch (ParametrosInvalidosException e){
      Assert.assertEquals("Modificador em formato inválido (menor que 0 ou maior que 100).", e.getMessage());
    }try{
      estrategia1 = new Estrategia(inicioPeriodo, finalPeriodo, 50, Estrategia.ACRESCIMO, "");
      Assert.fail("Uma excessão deveria ter sido jogada, pois a descrição estava vazia.");
    }catch (ParametrosInvalidosException e){
      Assert.assertEquals("Insira alguma descrição para a estratégia.", e.getMessage());
    }
  }
  @Test
  public void testa_toString(){
    Assert.assertEquals("Qualquer coisa" + "\nDe 10/03 até 10/03" + "\nAcréscimo de 50.0%", estrategia1.toString());
  }
  @Test
  public void testa_compareTo(){
    estrategia1 = new Estrategia(inicioPeriodo, finalPeriodo.plusWeeks(2), 50, Estrategia.ACRESCIMO, "Recesso entre o segundo e o terceiro período que vai durar só duas semanas parece.");
    Assert.assertEquals(-1, estrategia1.compareTo(estrategia2));
    estrategia1 = new Estrategia(inicioPeriodo.plusDays(2), finalPeriodo.plusDays(2), 20, Estrategia.DECRESCIMO, "Outra coisa");
    Assert.assertEquals(0, estrategia1.compareTo(estrategia2));

  }

}
