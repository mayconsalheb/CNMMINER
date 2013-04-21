package br.com.cnmminer.formularios;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JSpinner;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import br.com.cnmminer.bean.Arquivo;
import br.com.cnmminer.bean.Cnm;
import br.com.cnmminer.bean.PlanilhaExcel;
import javax.swing.JCheckBox;

public abstract  class FormPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private BufferedImage imagem;
	private Arquivo arq;
	private PlanilhaExcel planilha;
	private Cnm cnm;
	private JFrame framePai;
	private JFrame FrameAtual;

	/**
	 * Create the frame.
	 */
	public FormPrincipal(Arquivo arquivo, PlanilhaExcel planilhaExcel, Cnm nmc) {
	
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
		
		if(arq == null)
			setArq(arquivo);
		if(planilha == null)
			setPlanilha(planilhaExcel);
		if(cnm == null)
			setCnm(nmc);
		
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
		
		
		//Adicionando imagem
	/*	try {
			imagem = ImageIO.read(new File("src/main/resources/imagens/banco-de-dados-web.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	 */
		//JLabel labelImagem = new JLabel(new ImageIcon(imagem));
		
		//painelImagem.add(labelImagem);
		contentPane.setLayout(gl_contentPane);
	}
	
	public JFrame getFramePai() {
		return framePai;
	}

	public void setFramePai(JFrame framePai) {
		this.framePai = framePai;
	}
	
	public JFrame getFrameAtual() {
		return FrameAtual;
	}

	public void setFrameAtual(JFrame frameAtual) {
		FrameAtual = frameAtual;
	}
	
	public Arquivo getArq() {
		return arq;
	}

	public void setArq(Arquivo arq) {
		this.arq = arq;
	}
	
	//MÔøΩtodo deve ser transformando para abstract
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
	
	//MÔøΩtodo deve ser transformando para abstract
	public ActionListener retornaEventoBotaoVoltar(){
		return new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if(getFramePai() != null){
					setVisible(false);
					getFramePai().setVisible(true);
				}
			}
		};
	}
	
	//MÔøΩtodo deve ser transformando para abstract
	public ActionListener retornaEventoBotaoConcluir(){
		return new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				
			}
		};
	}
		
	//MÔøΩtodo deve ser transformando para abstract
	public abstract JPanel painelEditavel();
//	public JPanel painelEditavel(){
//		return new JPanel();
//	}

	public PlanilhaExcel getPlanilha() {
		return planilha;
	}

	public void setPlanilha(PlanilhaExcel planilha) {
		this.planilha = planilha;
	}
	
	public Cnm getCnm() {
		return cnm;
	}

	public void setCnm(Cnm nmc) {
		this.cnm = nmc;
	}
}
