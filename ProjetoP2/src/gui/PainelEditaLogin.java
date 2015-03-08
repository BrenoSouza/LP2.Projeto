package gui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import java.awt.CardLayout;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;

public class PainelEditaLogin extends JInternalFrame {
  private JPanel panelExterno = new JPanel();;
  private CardLayout layoutPainel = new CardLayout();  
  private JButton btnCadastrarFuncionrio;
  private PanelEditarLogin editaLogin = new PanelEditarLogin();
  private PanelCadastrarFuncionario cadastraFuncionario = new PanelCadastrarFuncionario();

  /**
   * Create the frame.
   */
  public PainelEditaLogin() {
    cadastraFuncionario.setSize(new Dimension(500, 330));
    editaLogin.setSize(new Dimension(500, 330));
    setBounds(100, 100, 782, 505);
    
    panelExterno.setLayout(layoutPainel);
    
    panelExterno.add(editaLogin, "editaLogin");
    panelExterno.add(cadastraFuncionario, "cadastrarLogin");
    
    JButton btnEditarDados = new JButton("Editar Dados");
    btnEditarDados.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        
        layoutPainel.show(panelExterno, "editaLogin");
        
      }
    });
    
    btnCadastrarFuncionrio = new JButton("Cadastrar Funcionário");
    btnCadastrarFuncionrio.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        
        layoutPainel.show(panelExterno, "cadastrarLogin");
        
      }
    });
    
    
    GroupLayout groupLayout = new GroupLayout(getContentPane());
    groupLayout.setHorizontalGroup(
      groupLayout.createParallelGroup(Alignment.LEADING)
        .addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
          .addContainerGap(189, Short.MAX_VALUE)
          .addComponent(btnEditarDados)
          .addGap(129)
          .addComponent(btnCadastrarFuncionrio)
          .addGap(152))
        .addGroup(groupLayout.createSequentialGroup()
          .addGap(60)
          .addComponent(panelExterno, GroupLayout.PREFERRED_SIZE, 673, GroupLayout.PREFERRED_SIZE)
          .addContainerGap(46, Short.MAX_VALUE))
    );
    groupLayout.setVerticalGroup(
      groupLayout.createParallelGroup(Alignment.LEADING)
        .addGroup(groupLayout.createSequentialGroup()
          .addGap(26)
          .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
            .addComponent(btnCadastrarFuncionrio)
            .addComponent(btnEditarDados))
          .addGap(37)
          .addComponent(panelExterno, GroupLayout.PREFERRED_SIZE, 333, GroupLayout.PREFERRED_SIZE)
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
    panelExterno.setLayout(gl_panelExterno);
    getContentPane().setLayout(groupLayout);

  }
}
