package br.com.cnmminer.formularios;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;

import br.com.cnmminer.bean.Arquivo;
import br.com.cnmminer.bean.Cnm;
import br.com.cnmminer.bean.PlanilhaExcel;
import br.com.cnmminer.util.ManipularArquivo;

import com.jgoodies.forms.factories.DefaultComponentFactory;

public class FormDiretorioSaida extends FormPrincipal {


	public FormDiretorioSaida(Arquivo arquivo, PlanilhaExcel planilha, Cnm cnm) {
		super(arquivo, planilha, cnm);
	}

	private static final long serialVersionUID = 2033196326835124700L;

	private Arquivo arquivoSaida;
	private JFileChooser chooser;
	private String caminho = "";
	private JEditorPane editorLocalArquivoExcel;
	private JComboBox comboBox;
	private ManipularArquivo manipularArquivo;
	private FormPrincipal form;
	private PlanilhaExcel planilhaExcel;

	@Override
	public JPanel painelEditavel() {

		JPanel painelCarregarArquivo = new JPanel();

		JLabel lblDadosDeOrigem = DefaultComponentFactory.getInstance().createLabel("Exporta��o de an�lise");
		
		JLabel lblLocalizaoDoArquivo = DefaultComponentFactory.getInstance().createLabel("Informe o diret�rio e o nome do arquivo:");
		
		editorLocalArquivoExcel = new JEditorPane();
		editorLocalArquivoExcel.setEditable(Boolean.FALSE);
		
		JButton botaoProcurarArquivo = new JButton("...");
		
		botaoProcurarArquivo.addActionListener(listenerBotaoProcurarArquivo());
		
		GroupLayout gl_painelEditavel = new GroupLayout(painelCarregarArquivo);
		gl_painelEditavel.setHorizontalGroup(
			gl_painelEditavel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_painelEditavel.createSequentialGroup()
					.addGroup(gl_painelEditavel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_painelEditavel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_painelEditavel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_painelEditavel.createSequentialGroup()
									.addComponent(editorLocalArquivoExcel, GroupLayout.PREFERRED_SIZE, 381, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(botaoProcurarArquivo, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblDadosDeOrigem)
								.addComponent(lblLocalizaoDoArquivo)))))
		);
		gl_painelEditavel.setVerticalGroup(
			gl_painelEditavel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_painelEditavel.createSequentialGroup()
					.addGap(28)
					.addComponent(lblDadosDeOrigem)
					.addGap(44)
					.addComponent(lblLocalizaoDoArquivo)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_painelEditavel.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(botaoProcurarArquivo, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
						.addComponent(editorLocalArquivoExcel, Alignment.LEADING))
					.addGap(44)
					.addPreferredGap(ComponentPlacement.RELATED))
		);
		painelCarregarArquivo.setLayout(gl_painelEditavel);
		
		return painelCarregarArquivo;
		
	}
	
	public ActionListener listenerBotaoProcurarArquivo(){
		return new ActionListener(){

			public void actionPerformed(ActionEvent event) {
				
				if(manipularArquivo == null)
					manipularArquivo = new ManipularArquivo();
				
				chooser = new JFileChooser();
				int retorno = chooser.showSaveDialog(null); 
				if (retorno==JFileChooser.APPROVE_OPTION){
				      caminho = chooser.getSelectedFile().getAbsolutePath();
				      System.out.println(caminho);
				      editorLocalArquivoExcel.setText(caminho);
				}
				editorLocalArquivoExcel.setText(caminho);
			}
		};
	}
	
	public ActionListener retornaEventoBotaoAvancar(){
		return new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				
				if(editorLocalArquivoExcel.getText() != null || editorLocalArquivoExcel.getText() != ""){
					
					if(manipularArquivo.criarArquivoSaida(editorLocalArquivoExcel.getText())) {
					
						new JOptionPane();
						JOptionPane.showMessageDialog(null, "Arquivo criado com sucesso!");
					}else{
						
						new JOptionPane();
						JOptionPane.showMessageDialog(null, "N�o foi poss�vel criar o arquivo no diret�rio selecionado!");
					}
					
					setVisible(false);
					form = new FormConcluir(getArq(), getPlanilha(), getCnm());
					form.setFrameAtual(form);
					form.setFramePai(getFrameAtual());
					form.setVisible(true);
				}
			}
		};
	}
	
	public ActionListener retornaEventoBotaoConcluir(){
		return new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				
			}
		};
	}

}
