package gui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;

import core.Login;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PainelEditarUsuario extends JInternalFrame {
  private Login loginSelecionado;
  private JPasswordField passwordFieldAtual;
  private JPasswordField passwordFieldNovo;
  private JTextField textFieldDica;
  private JButton btnSalvar;
  private JButton btnCancelar;

  
  public PainelEditarUsuario(Login loginSelecionado) {
    setTitle("Editar Usuário");
    setFrameIcon(new ImageIcon(PainelEditarUsuario.class.getResource("/resources/padlock.png")));
    setClosable(true);
    setBounds(100, 100, 264, 190);
    this.loginSelecionado = loginSelecionado;
    
    JLabel lblUsurio = new JLabel("Usuário: ");
    lblUsurio.setFont(new Font("Tahoma", Font.PLAIN, 14));
    
    JLabel lblNewLabel = new JLabel("New label");
    lblNewLabel.setText(loginSelecionado.getNome());
    lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
    
    JLabel lblSenhaAtual = new JLabel("Senha Atual: ");
    lblSenhaAtual.setFont(new Font("Tahoma", Font.PLAIN, 14));
    
    passwordFieldAtual = new JPasswordField();
    
    JLabel lblNovaSenha = new JLabel("Nova Senha: ");
    lblNovaSenha.setFont(new Font("Tahoma", Font.PLAIN, 14));
    
    passwordFieldNovo = new JPasswordField();
    
    JLabel lblDicaDeSenha = new JLabel("Dica de Senha: ");
    lblDicaDeSenha.setFont(new Font("Tahoma", Font.PLAIN, 14));
    
    textFieldDica = new JTextField();
    textFieldDica.setText(loginSelecionado.getDica());
    textFieldDica.setColumns(10);
    
    btnSalvar = new JButton("Salvar");
    btnSalvar.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
       String senhaAtual = new String(passwordFieldAtual.getPassword());
       String senhaNova = new String(passwordFieldNovo.getPassword());
       if (senhaAtual.equals(PainelEditarUsuario.this.loginSelecionado.getSenha())){
         PainelEditarUsuario.this.loginSelecionado.setSenha(senhaNova);
         PainelEditarUsuario.this.loginSelecionado.setDica(textFieldDica.getText());
         JOptionPane.showMessageDialog(null, "Senha alterada.");
       }else{
         JOptionPane.showMessageDialog(null, "Senha atual incorreta.");
       }
      }
    });
    
    btnCancelar = new JButton("Cancelar");
    btnCancelar.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        dispose();
      }
    });
    GroupLayout groupLayout = new GroupLayout(getContentPane());
    groupLayout.setHorizontalGroup(
      groupLayout.createParallelGroup(Alignment.LEADING)
        .addGroup(groupLayout.createSequentialGroup()
          .addContainerGap()
          .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(groupLayout.createSequentialGroup()
              .addComponent(btnSalvar)
              .addPreferredGap(ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
              .addComponent(btnCancelar))
            .addGroup(groupLayout.createSequentialGroup()
              .addComponent(lblDicaDeSenha)
              .addPreferredGap(ComponentPlacement.RELATED)
              .addComponent(textFieldDica, GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE))
            .addGroup(groupLayout.createSequentialGroup()
              .addComponent(lblUsurio)
              .addPreferredGap(ComponentPlacement.RELATED)
              .addComponent(lblNewLabel))
            .addGroup(groupLayout.createSequentialGroup()
              .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                .addComponent(lblSenhaAtual)
                .addComponent(lblNovaSenha))
              .addPreferredGap(ComponentPlacement.RELATED)
              .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                .addComponent(passwordFieldNovo)
                .addComponent(passwordFieldAtual, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE))))
          .addGap(200))
    );
    groupLayout.setVerticalGroup(
      groupLayout.createParallelGroup(Alignment.LEADING)
        .addGroup(groupLayout.createSequentialGroup()
          .addContainerGap()
          .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
            .addComponent(lblUsurio)
            .addComponent(lblNewLabel))
          .addPreferredGap(ComponentPlacement.RELATED)
          .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
            .addComponent(lblSenhaAtual)
            .addComponent(passwordFieldAtual, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
          .addPreferredGap(ComponentPlacement.RELATED)
          .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
            .addComponent(lblNovaSenha)
            .addComponent(passwordFieldNovo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
          .addPreferredGap(ComponentPlacement.RELATED)
          .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
            .addComponent(lblDicaDeSenha)
            .addComponent(textFieldDica, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
          .addPreferredGap(ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
          .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
            .addComponent(btnSalvar)
            .addComponent(btnCancelar))
          .addContainerGap())
    );
    getContentPane().setLayout(groupLayout);

  }
}
