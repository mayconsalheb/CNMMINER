package br.com.cnmminer.formularios;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import br.com.cnmminer.bean.Arquivo;
import br.com.cnmminer.bean.Cnm;
import br.com.cnmminer.bean.PlanilhaExcel;
import br.com.cnmminer.util.ManipularArquivo;

import com.jgoodies.forms.factories.DefaultComponentFactory;

/**
 * Classe responsï¿½vel por apresentar formulï¿½rio de escolha de ï¿½ndice para mineraï¿½ï¿½o de dados.
 * 
 * @author felipe
 *
 */
public class FormEscolherIndice extends FormPrincipal {


	private static final long serialVersionUID = 2869664018981879475L;
	
	private FormPrincipal form;
	private Cnm cnm;
	private ManipularArquivo manipularArquivo;
	private ArrayList<String> colunas;
	private JComboBox comboBox;
	
	public FormEscolherIndice(Arquivo arquivo, PlanilhaExcel planilhaExcel, Cnm cnm) {
		super(arquivo, planilhaExcel, cnm);
	}

	public JPanel painelEditavel(){

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
			JOptionPane.showMessageDialog(form,
				    "N‹o existe coluna para a planilha selecionada!",
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
				setVisible(false);
				
				colunas.remove(comboBox.getSelectedItem().toString());
				getPlanilha().setColunas(colunas);
				
				cnm = new Cnm(null, 
					      null, 
						  null, 
						  comboBox.getSelectedItem().toString());
				
				form = new FormEscolherLadoEntao(getArq(), getPlanilha(), cnm);
				form.setFrameAtual(form);
				form.setFramePai(getFrameAtual());
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
