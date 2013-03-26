package br.com.cnmminer.formularios;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JTextPane;
import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JCheckBox;
import java.awt.Cursor;

public abstract  class FormPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private BufferedImage imagem;

	/**
	 * Create the frame.
	 */
	public FormPrincipal() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 659, 462);
		contentPane = new JPanel();
		setContentPane(contentPane);
		
		JPanel painelRodape = new JPanel();
		painelRodape.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		painelRodape.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		painelRodape.add(panel, BorderLayout.CENTER);

		
		JButton botaoCancelar = new JButton("Cancelar");
		botaoCancelar.addActionListener(retornaEventoBotaoCancelar());
		
		JButton botaoVoltar = new JButton("Voltar");
		botaoVoltar.addActionListener(retornaEventoBotaoVoltar());
		
		JButton botaoAvancar = new JButton("Avan\u00E7ar");
		botaoAvancar.addActionListener(retornaEventoBotaoAvancar());
		
		JButton botaoConcluir = new JButton("Concluir");
		botaoConcluir.addActionListener(retornaEventoBotaoConcluir());
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(botaoCancelar)
					.addGap(202)
					.addComponent(botaoVoltar)
					.addGap(18)
					.addComponent(botaoAvancar)
					.addGap(18, 126, Short.MAX_VALUE)
					.addComponent(botaoConcluir)
					.addGap(11))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(botaoVoltar)
						.addComponent(botaoAvancar)
						.addComponent(botaoConcluir)
						.addComponent(botaoCancelar))
					.addContainerGap(14, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		JPanel painelImagem = new JPanel();
		
		JPanel painelEditavel = painelEditavel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(painelRodape, GroupLayout.PREFERRED_SIZE, 649, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(6)
							.addComponent(painelImagem, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(painelEditavel, 0, 0, Short.MAX_VALUE)))
					.addGap(9))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(painelEditavel, GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE)
						.addComponent(painelImagem, GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE))
					.addGap(30)
					.addComponent(painelRodape, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		
//		JLabel lblEscolhaDasCausas = DefaultComponentFactory.getInstance().createLabel("Escolha das causas");
//		
//		JLabel lblMarqueTodosOs = DefaultComponentFactory.getInstance().createLabel("Marque todos os camos que poder\u00E3o ser usados pelo sistema");
//		
//		JLabel lblAFim = DefaultComponentFactory.getInstance().createLabel("a fim de descobrir rela\u00E7\u00E0o entre eles e o lado ENT\u00C0O informado");
//		
//		JLabel lblAnteriormente = DefaultComponentFactory.getInstance().createLabel("anteriormente.");
//		
//		JLabel lblAtributosDasCausas = DefaultComponentFactory.getInstance().createLabel("Atributos das causas:");
//		
//		JPanel painelLadoSe = new JPanel();
		
//		GroupLayout gl_painelEditavel = new GroupLayout(painelEditavel);
//		gl_painelEditavel.setHorizontalGroup(
//			gl_painelEditavel.createParallelGroup(Alignment.LEADING)
//				.addGroup(gl_painelEditavel.createSequentialGroup()
//					.addGap(27)
//					.addGroup(gl_painelEditavel.createParallelGroup(Alignment.TRAILING)
//						.addComponent(painelLadoSe, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//						.addGroup(Alignment.LEADING, gl_painelEditavel.createParallelGroup(Alignment.TRAILING, false)
//							.addComponent(lblAnteriormente, Alignment.LEADING)
//							.addComponent(lblAFim, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//							.addComponent(lblAtributosDasCausas, Alignment.LEADING)
//							.addComponent(lblMarqueTodosOs, Alignment.LEADING)
//							.addComponent(lblEscolhaDasCausas, Alignment.LEADING)))
//					.addContainerGap(30, Short.MAX_VALUE))
//		);
//		gl_painelEditavel.setVerticalGroup(
//			gl_painelEditavel.createParallelGroup(Alignment.LEADING)
//				.addGroup(gl_painelEditavel.createSequentialGroup()
//					.addGap(23)
//					.addComponent(lblEscolhaDasCausas)
//					.addGap(33)
//					.addComponent(lblMarqueTodosOs)
//					.addPreferredGap(ComponentPlacement.RELATED)
//					.addComponent(lblAFim)
//					.addPreferredGap(ComponentPlacement.RELATED)
//					.addComponent(lblAnteriormente)
//					.addGap(42)
//					.addComponent(lblAtributosDasCausas)
//					.addGap(18)
//					.addComponent(painelLadoSe, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
//					.addContainerGap(85, Short.MAX_VALUE))
//		);
//		
//		JCheckBox chckbxNewCheckBox = new JCheckBox("New check box");
//		
//		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("New check box");
//		
//		JCheckBox chckbxNewCheckBox_2 = new JCheckBox("New check box");
//		GroupLayout gl_painelLadoSe = new GroupLayout(painelLadoSe);
//		gl_painelLadoSe.setHorizontalGroup(
//			gl_painelLadoSe.createParallelGroup(Alignment.LEADING)
//				.addGroup(gl_painelLadoSe.createSequentialGroup()
//					.addGroup(gl_painelLadoSe.createParallelGroup(Alignment.LEADING)
//						.addGroup(gl_painelLadoSe.createSequentialGroup()
//							.addGap(135)
//							.addComponent(chckbxNewCheckBox))
//						.addGroup(gl_painelLadoSe.createSequentialGroup()
//							.addGap(69)
//							.addComponent(chckbxNewCheckBox_1))
//						.addGroup(gl_painelLadoSe.createSequentialGroup()
//							.addGap(104)
//							.addComponent(chckbxNewCheckBox_2)))
//					.addContainerGap(136, Short.MAX_VALUE))
//		);
//		gl_painelLadoSe.setVerticalGroup(
//			gl_painelLadoSe.createParallelGroup(Alignment.LEADING)
//				.addGroup(gl_painelLadoSe.createSequentialGroup()
//					.addGap(5)
//					.addComponent(chckbxNewCheckBox)
//					.addGap(18)
//					.addComponent(chckbxNewCheckBox_1)
//					.addPreferredGap(ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
//					.addComponent(chckbxNewCheckBox_2))
//		);
//		painelLadoSe.setLayout(gl_painelLadoSe);
//		painelEditavel.setLayout(gl_painelEditavel);
		
		//Adicionando imagem
		try {
			imagem = ImageIO.read(new File("src/main/resources/imagens/banco-de-dados-web.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		JLabel labelImagem = new JLabel(new ImageIcon(imagem));
		
		painelImagem.add(labelImagem);
		contentPane.setLayout(gl_contentPane);
	}
	
	//Método deve ser transformando para abstract
	public ActionListener retornaEventoBotaoAvancar(){
		return new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				
			}
		};
	}
	
	public ActionListener retornaEventoBotaoCancelar(){
		
		return new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		};
	}
	
	//Método deve ser transformando para abstract
	public ActionListener retornaEventoBotaoVoltar(){
		return new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				
			}
		};
	}
	
	//Método deve ser transformando para abstract
	public ActionListener retornaEventoBotaoConcluir(){
		return new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				
			}
		};
	}
		
	//Método deve ser transformando para abstract
	public abstract JPanel painelEditavel();
//	public JPanel painelEditavel(){
//		return new JPanel();
//	}
}
