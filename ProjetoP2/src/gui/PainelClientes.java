package gui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.Font;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JFormattedTextField;
import javax.swing.DropMode;
import javax.swing.text.DateFormatter;
import javax.swing.text.MaskFormatter;

import classes.Hospede;

public class PainelClientes extends JInternalFrame {

	private List<Hospede> listaHospedes = new ArrayList<Hospede>();


	/**
	 * Create the frame.
	 */
	public PainelClientes(List<Hospede> listaHospedes) throws Exception{
		this.listaHospedes.addAll(listaHospedes);
		setResizable(true);
		setFrameIcon(new ImageIcon(PainelClientes.class.getResource("/resources/clientes_icon.png")));
		setTitle("Clientes");
		setClosable(true);
		setBounds(0, 0, 752, 450);
		
		
		DateFormat formatoData = new SimpleDateFormat("dd-MM-yyyy");
        DateFormatter df = new DateFormatter(formatoData);
		
		MaskFormatter mascara = new MaskFormatter("###.###.###-##");
        mascara.setPlaceholderCharacter('_');

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGap(0, 736, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGap(0, 421, Short.MAX_VALUE)
		);
		getContentPane().setLayout(groupLayout);

	}
}
