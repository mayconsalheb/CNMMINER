package br.com.cnmminer.formularios;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;

import br.com.cnmminer.bean.Arquivo;
import br.com.cnmminer.bean.PlanilhaExcel;
import br.com.cnmminer.util.ManipularArquivo;

import com.jgoodies.forms.factories.DefaultComponentFactory;

public class FormCarregarArquivo extends FormPrincipal {

	public FormCarregarArquivo() {
	}

	private static final long serialVersionUID = 2033196326835124700L;

	private Arquivo arquivo;
	private JFileChooser chooser;
	private String caminhoArquivo;
	private JEditorPane editorLocalArquivoExcel;
	private JComboBox comboBox;
	private ManipularArquivo manipularArquivo;
	private FormPrincipal form;
	private PlanilhaExcel planilhaExcel;

	@Override
	public JPanel painelEditavel() {

		JPanel painelCarregarArquivo = new JPanel();

		JLabel lblDadosDeOrigem = DefaultComponentFactory.getInstance().createLabel("Dados de origem");
		
		JLabel lblLocalizaoDoArquivo = DefaultComponentFactory.getInstance().createLabel("Localiza\u00E7\u00E3o do arquivo Microsoft Excel:");
		
		editorLocalArquivoExcel = new JEditorPane();
		
		JButton botaoProcurarArquivo = new JButton("...");
		
		botaoProcurarArquivo.addActionListener(listenerBotaoProcurarArquivo());
		
		JLabel lblAgoraEscolhaA = DefaultComponentFactory.getInstance().createLabel("Agora escolha a planilha que deseja analisar:");
		
		comboBox = new JComboBox();
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
								.addComponent(lblLocalizaoDoArquivo)
								.addComponent(lblAgoraEscolhaA)))
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 246, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(8, Short.MAX_VALUE))
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
					.addComponent(lblAgoraEscolhaA)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(134, Short.MAX_VALUE))
		);
		painelCarregarArquivo.setLayout(gl_painelEditavel);
		
		return painelCarregarArquivo;
		
	}
	
	public ActionListener listenerBotaoProcurarArquivo(){
		return new ActionListener(){

			public void actionPerformed(ActionEvent event) {
				
				chooser = new JFileChooser();

				chooser.setFileFilter(new javax.swing.filechooser.FileFilter(){
					public boolean accept(File f){
						return f.getName().toLowerCase().endsWith(Arquivo.EXTENSAO_XLS) || f.getName().toLowerCase().endsWith(Arquivo.EXTENSAO_XLSX) || f.isDirectory();
					}
					public String getDescription() {
						return "Arquivo do Microsoft Excel (.xls)";
					}
				});
				
				Integer retorno = chooser.showOpenDialog(null);
				
				if(retorno == JFileChooser.APPROVE_OPTION){
				
					caminhoArquivo = chooser.getSelectedFile().getAbsolutePath();

					manipularArquivo = new ManipularArquivo();
					
					arquivo = montarArquivo();
					planilhaExcel = new PlanilhaExcel();
					
					if(manipularArquivo.abrirArquivo(arquivo, planilhaExcel)){
				
						ArrayList<String> planilhas = manipularArquivo.recuperarPlanilhasArquivo(arquivo, planilhaExcel);

						if(caminhoArquivo.endsWith(Arquivo.EXTENSAO_XLS)){
						
							if(planilhas != null){
								comboBox.removeAllItems();
								for (String planilhaCombo : planilhas) {
									comboBox.addItem(planilhaCombo);
								}
							}
							
						}else if(caminhoArquivo.endsWith(Arquivo.EXTENSAO_XLSX)){
							
							if(planilhas != null){
								comboBox.removeAllItems();
								for (String planilhaCombo : planilhas) {
									comboBox.addItem(planilhaCombo);
								}
							}
						}
						
						System.out.println(caminhoArquivo);
						editorLocalArquivoExcel.setText(caminhoArquivo);
					}else{
//						TODO: nao foi possivelinstanciar o arquivo.
						
					}
					
				}else{
					//TODO: JOPTIOn Pane > nao foi possivel abrir o arquivo informado.
					System.out.println("Nao abriu");
				}
			}

			/**
			 * M�todo respons�vel por instanciar arquivo que ser� lido.
			 * 
			 * @return
			 */
			private Arquivo montarArquivo() {
				
				String nomeArquivo = manipularArquivo.recuperarNomeArquivo(caminhoArquivo);
				String diretorioEntrada = manipularArquivo.recuperarDiretorioEntrada(caminhoArquivo, nomeArquivo);
				String diretorioSaida = null;
				String extensaoArquivo = manipularArquivo.recuperarExtensaoArquivo(nomeArquivo);
				
				return new Arquivo(nomeArquivo, diretorioEntrada, diretorioSaida, extensaoArquivo);
			}
		};
	}
	
	public ActionListener retornaEventoBotaoAvancar(){
		return new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				setVisible(false);
				form = new FormEscolherLadoEntao();
				form.setFrameAtual(form);
				form.setFramePai(getFrameAtual());
				form.setArq(arquivo);
				form.setPlanilha(planilhaExcel);
				form.setVisible(true);
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
