package br.com.cnmminer.formularios;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;

import br.com.cnmminer.bean.Arquivo;
import br.com.cnmminer.bean.Cnm;
import br.com.cnmminer.bean.Neuronio;
import br.com.cnmminer.bean.PlanilhaExcel;
import br.com.cnmminer.util.Aprendizado;
import br.com.cnmminer.util.ManipularArquivo;

import com.jgoodies.forms.factories.DefaultComponentFactory;

public class FormDiretorioSaida extends FormPrincipal {


	public FormDiretorioSaida(Arquivo arquivo, PlanilhaExcel planilha, Cnm cnm, JFrame frame) {
		super(arquivo, planilha, cnm, frame);
	}

	private static final String EXTENSAO_ARQ = ".xls";
	
	private JFileChooser chooser;
	private String caminho = "";
	private JEditorPane editorLocalArquivoExcel;
	private ManipularArquivo manipularArquivo;
	private FormPrincipal form;
	

	@Override
	public JPanel obterPainelEditavel() {

		JPanel painelCarregarArquivo = new JPanel();

		JLabel lblDadosDeOrigem = DefaultComponentFactory.getInstance().createLabel("Exporta\u00e7\u00e3o de an\u00e1lise");
		
		JLabel lblLocalizaoDoArquivo = DefaultComponentFactory.getInstance().createLabel("Informe o diret\u00f3rio e o nome do arquivo:");
		
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
				      
				      caminho = caminho+EXTENSAO_ARQ;
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
						
						getArq().setDiretorioSaida(editorLocalArquivoExcel.getText());
					}else{
						
						new JOptionPane();
						JOptionPane.showMessageDialog(null, "N\u00e3o foi poss\u00edvel criar o arquivo no diret\u00f3rio selecionado!");
					}
					
					Aprendizado aprendizagem = new Aprendizado(getPlanilha(), getArq(),getCnm());
					List<Neuronio> neuronios = aprendizagem.gerarRedeNeural();
					
					manipularArquivo.escreverRegistrosArquivo(neuronios, getArq().getDiretorioSaida());
					
					form = new FormConcluir(getArq(), getPlanilha(), getCnm(),getFrame());
					form.setFormAtual(form);
					form.setFormPai(getFormAtual());
					form.obterConfiguracoesTela();
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
