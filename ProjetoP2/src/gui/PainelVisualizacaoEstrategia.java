package gui;

import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;

import core.Estrategia;

public class PainelVisualizacaoEstrategia extends JInternalFrame {

	private static final long serialVersionUID = 7269090620731625395L;
	private JLabel lblModificadorVariavel;
	private JLabel lblDiaFinal;
	private JLabel labelDiaInicio;
	private JLabel lblDescricao;
	public PainelVisualizacaoEstrategia(Estrategia estrategia) {
		setResizable(true);
		setFrameIcon(new ImageIcon(PainelVisualizacaoEstrategia.class.getResource("/resources/calendar146.png")));
		setTitle("Visualizar estratégia");
		setClosable(true);
		setBounds(100, 100, 570, 200);
		
		JLabel lblDescrio = new JLabel("Descrição:");
		lblDescrio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblDiaDeIncio = new JLabel("Dia de Início:");
		lblDiaDeIncio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblDiaDeFinal = new JLabel("Dia de Final:");
		lblDiaDeFinal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblModificador = new JLabel("Modificador:");
		lblModificador.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		lblDescricao = new JLabel(estrategia.getDescricao());
		lblDescricao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		labelDiaInicio = new JLabel(estrategia.getInicioPeriodoString());
		labelDiaInicio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		lblDiaFinal = new JLabel(estrategia.getFinalPeriodoString());
		lblDiaFinal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		lblModificadorVariavel = new JLabel((estrategia.getModificador() * estrategia.getTipoDeEstrategia().getMultiplicador()) + "%");
		lblModificadorVariavel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblDiaDeFinal)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblDiaFinal))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblModificador)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblModificadorVariavel))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblDiaDeIncio)
									.addPreferredGap(ComponentPlacement.RELATED))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblDescrio, GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
									.addGap(4)))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(labelDiaInicio)
									.addGap(180))
								.addComponent(lblDescricao, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE))))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDescrio)
						.addComponent(lblDescricao))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDiaDeIncio)
						.addComponent(labelDiaInicio))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDiaDeFinal)
						.addComponent(lblDiaFinal))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblModificador)
						.addComponent(lblModificadorVariavel))
					.addContainerGap(192, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);

	}
}
