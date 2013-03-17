package br.com.cnminer.formularios;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.image.BufferedImage;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;

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

		
		JButton button = new JButton("Cancelar");
		
		JButton button_1 = new JButton("Voltar");
		
		JButton button_2 = new JButton("Avan\u00E7ar");
		
		JButton button_3 = new JButton("Concluir");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(button)
					.addGap(202)
					.addComponent(button_1)
					.addGap(18)
					.addComponent(button_2)
					.addGap(18, 126, Short.MAX_VALUE)
					.addComponent(button_3)
					.addGap(11))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(button_1)
						.addComponent(button_2)
						.addComponent(button_3)
						.addComponent(button))
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
							.addComponent(painelEditavel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addGap(4))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(painelImagem, GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)
						.addComponent(painelEditavel, GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE))
					.addGap(30)
					.addComponent(painelRodape, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
//		//Adicionando imagem
//		try {
//			imagem = ImageIO.read(new File("src/main/resources/imagens/banco-de-dados-web.png"));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		JLabel labelImagem = new JLabel(new ImageIcon(imagem));
//		
//		painelImagem.add(labelImagem);
		contentPane.setLayout(gl_contentPane);
	}
	
	public abstract JPanel painelEditavel();
		
}
