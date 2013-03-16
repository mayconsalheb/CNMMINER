package br.com.cnminer.formularios;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class FormPrincipal extends JFrame {

	private JPanel contentPane;
	private BufferedImage imagem;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormPrincipal frame = new FormPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

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
		painelRodape.add(panel, BorderLayout.SOUTH);

		
		JButton button = new JButton("Cancelar");
		
		JButton button_1 = new JButton("Voltar");
		
		JButton button_2 = new JButton("Avan\u00E7ar");
		
		JButton button_3 = new JButton("Concluir");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 649, Short.MAX_VALUE)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(button)
					.addGap(212)
					.addComponent(button_1)
					.addGap(18)
					.addComponent(button_2)
					.addGap(18, 36, Short.MAX_VALUE)
					.addComponent(button_3)
					.addGap(11))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 37, Short.MAX_VALUE)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(button)
						.addComponent(button_1)
						.addComponent(button_2)
						.addComponent(button_3))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		JPanel painelImagem = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(painelRodape, GroupLayout.PREFERRED_SIZE, 649, GroupLayout.PREFERRED_SIZE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(6)
					.addComponent(painelImagem, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE))
		);
		//Adicionando imagem
		try {
			imagem = ImageIO.read(new File("src/main/resources/imagens/banco-de-dados-web.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		JLabel labelImagem = new JLabel(new ImageIcon(imagem));
		
		painelImagem.add(labelImagem);
		
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(painelImagem, GroupLayout.PREFERRED_SIZE, 377, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(painelRodape, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
