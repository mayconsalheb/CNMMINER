package br.com.cnmminer.formularios;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import br.com.cnmminer.bean.Arquivo;
import br.com.cnmminer.bean.Cnm;
import br.com.cnmminer.bean.PlanilhaExcel;
import br.com.cnmminer.util.ManipularArquivo;

import com.jgoodies.forms.factories.DefaultComponentFactory;

/**
 * Classe respons�vel por apresentar formul�rio de escolha de �ndice para minera��o de dados.
 * 
 * @author felipe
 *
 */
public class FormEscolherIndice extends FormPrincipal {
	
	private FormPrincipal form;
	private Cnm cnm;
	private ManipularArquivo manipularArquivo;
	private ArrayList<String> colunas;
	private JComboBox comboBox;
	
	public FormEscolherIndice(Arquivo arquivo, PlanilhaExcel planilhaExcel, Cnm cnm, JFrame frame) {
		super(arquivo, planilhaExcel, cnm, frame);
	}

	public JPanel obterPainelEditavel(){

		JPanel painelEscolherIndice = new JPanel();
		
		JLabel lblNewJgoodiesLabel = DefaultComponentFactory.getInstance().createLabel("Escolha do \u00EDndice");
		
		JLabel lblEscolhaOIndice = DefaultComponentFactory.getInstance().createLabel("Escolha o indice a ser usado na descoberta de regras:");
		
		comboBox = new JComboBox();
		manipularArquivo = new ManipularArquivo();
		colunas = new ArrayList<String>();
		
		colunas = manipularArquivo.recuperarColunas(super.getArq(), super.getPlanilha());
		
		
		if(colunas.size() != 0){
			comboBox.removeAllItems();
			for (String planilhaCombo : colunas) {
				comboBox.addItem(planilhaCombo);
			}
		}else{
			JOptionPane.showMessageDialog(getFrame(),
				    "N�o existe coluna para a planilha selecionada!",
				    "AVISO", JOptionPane.WARNING_MESSAGE);
			//TODO: voltar para o formulario anterior
			retornaEventoBotaoVoltar();
		}
		
		GroupLayout gl_painelEditavel = new GroupLayout(painelEscolherIndice);
		gl_painelEditavel.setHorizontalGroup(
			gl_painelEditavel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_painelEditavel.createSequentialGroup()
					.addGap(16)
					.addGroup(gl_painelEditavel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblEscolhaOIndice)
						.addComponent(lblNewJgoodiesLabel)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 228, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(104, Short.MAX_VALUE))
		);
		gl_painelEditavel.setVerticalGroup(
			gl_painelEditavel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_painelEditavel.createSequentialGroup()
					.addGap(21)
					.addComponent(lblNewJgoodiesLabel)
					.addGap(37)
					.addComponent(lblEscolhaOIndice)
					.addGap(18)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(224, Short.MAX_VALUE))
		);
		painelEscolherIndice.setLayout(gl_painelEditavel);
		
		return painelEscolherIndice;
		
	}
	
	public ActionListener retornaEventoBotaoAvancar(){
		return new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				colunas.remove(comboBox.getSelectedItem().toString());
				getPlanilha().setColunas(colunas);
				
				cnm = new Cnm(null, 
					      null, 
						  null, 
						  comboBox.getSelectedItem().toString());
				
				form = new FormEscolherLadoEntao(getArq(), getPlanilha(), cnm, getFrame());
				form.setFormAtual(form);
				form.setFormPai(getFormAtual());
				form.obterConfiguracoesTela();
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
