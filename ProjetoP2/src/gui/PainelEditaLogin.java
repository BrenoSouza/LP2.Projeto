package gui;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;

import java.awt.CardLayout;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;

import javax.swing.border.EtchedBorder;

import core.colecoes.ColecaoDeLogins;


public class PainelEditaLogin extends JInternalFrame {
  
  private static final long serialVersionUID = 58746164L;
  private CardLayout layout = new CardLayout();
  private JPanel panelExterno = new JPanel();
  private JButton btnCadastrarFuncionrio;
  private ColecaoDeLogins listaDeLogins;
  private PanelEditarLogin editaLogin = new PanelEditarLogin();
  private PanelCadastrarFuncionario cadastraFuncionario;
  
  
  /**
   * Create the frame.
   */
  public PainelEditaLogin(ColecaoDeLogins listaDeLogins) {
    this.listaDeLogins = listaDeLogins;
    setClosable(true);
    cadastraFuncionario = new PanelCadastrarFuncionario(PainelEditaLogin.this.listaDeLogins);

    cadastraFuncionario.setSize(new Dimension(500, 330));
    editaLogin.setSize(new Dimension(470, 320));
    setBounds(100, 100, 782, 505);
    panelExterno.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
    panelExterno.setLayout(layout);
    panelExterno.add(editaLogin, "editaLogin");
    panelExterno.add(cadastraFuncionario, "cadastrarLogin");
        
    JButton btnEditarDados = new JButton("Editar Dados");
    btnEditarDados.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        
        layout.show(panelExterno, "editaLogin");
        
      }
    });
    
    btnCadastrarFuncionrio = new JButton("Cadastrar Funcionário");
    btnCadastrarFuncionrio.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        layout.show(panelExterno, "cadastrarLogin");
        
      }
    });
    
    
    GroupLayout groupLayout = new GroupLayout(getContentPane());
    groupLayout.setHorizontalGroup(
      groupLayout.createParallelGroup(Alignment.TRAILING)
        .addGroup(groupLayout.createSequentialGroup()
          .addContainerGap(174, Short.MAX_VALUE)
          .addComponent(btnEditarDados)
          .addGap(129)
          .addComponent(btnCadastrarFuncionrio)
          .addGap(152))
        .addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
          .addGap(60)
          .addComponent(panelExterno, GroupLayout.PREFERRED_SIZE, 673, GroupLayout.PREFERRED_SIZE)
          .addContainerGap(39, Short.MAX_VALUE))
    );
    groupLayout.setVerticalGroup(
      groupLayout.createParallelGroup(Alignment.LEADING)
        .addGroup(groupLayout.createSequentialGroup()
          .addGap(26)
          .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
            .addComponent(btnCadastrarFuncionrio)
            .addComponent(btnEditarDados))
          .addGap(18)
          .addComponent(panelExterno, GroupLayout.PREFERRED_SIZE, 352, GroupLayout.PREFERRED_SIZE)
          .addContainerGap(52, Short.MAX_VALUE))
    );
    GroupLayout gl_panelExterno = new GroupLayout(panelExterno);
    gl_panelExterno.setHorizontalGroup(
      gl_panelExterno.createParallelGroup(Alignment.LEADING)
        .addGap(0, 775, Short.MAX_VALUE)
    );
    gl_panelExterno.setVerticalGroup(
      gl_panelExterno.createParallelGroup(Alignment.LEADING)
        .addGap(0, 387, Short.MAX_VALUE)
    );
    getContentPane().setLayout(groupLayout);

  }
}
