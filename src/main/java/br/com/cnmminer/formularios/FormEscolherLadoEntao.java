/**
 * 
 */
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
import javax.swing.LayoutStyle.ComponentPlacement;

import br.com.cnmminer.bean.Arquivo;
import br.com.cnmminer.bean.Cnm;
import br.com.cnmminer.bean.PlanilhaExcel;
import br.com.cnmminer.util.ManipularArquivo;

import com.jgoodies.forms.factories.DefaultComponentFactory;

/**
 * Classe responsï¿½vel por apresentar formulï¿½rio de escolha do lado ENTï¿½O para mineraï¿½ï¿½o de dados.
 * 
 * @author felipe
 *
 */
public class FormEscolherLadoEntao extends FormPrincipal {

	private static final long serialVersionUID = 5153797266678607223L;
	
	private FormPrincipal form;
	private ManipularArquivo manipularArquivo;
	private JComboBox comboBoxColunaEntao;

	public FormEscolherLadoEntao(Arquivo arquivo, PlanilhaExcel planilha, Cnm cnm) {
		super(arquivo, planilha, cnm);
	}



	public JPanel painelEditavel() {

		JPanel painelLadoEntao = new JPanel();

		manipularArquivo = new ManipularArquivo();
		
		JLabel lblEscolhaDoLado = DefaultComponentFactory.getInstance().createLabel("Escolha do lado ENT\u00C3O");
		
		JLabel lblNewJgoodiesLabel = DefaultComponentFactory.getInstance().createLabel("Selecione neste campo qual coluna da tabela ser\u00E1 o lado ENT\u00C3O");
		
		JLabel lblDasRegrasAssociativas = DefaultComponentFactory.getInstance().createLabel("das regras associativas que o sistema ir\u00E1 gerar:");
		
		comboBoxColunaEntao = new JComboBox();
		
		ArrayList<String> colunas = manipularArquivo.recuperarColunas(super.getArq(), super.getPlanilha());
		
		if(colunas.size() != 0){
			comboBoxColunaEntao.removeAllItems();
			for (String planilhaCombo : colunas) {
				comboBoxColunaEntao.addItem(planilhaCombo);
			}
		}else{
			JOptionPane.showMessageDialog(form,
				    "N‹o existe coluna para a planilha selecionada!",
				    "AVISO", JOptionPane.WARNING_MESSAGE);
			//TODO: voltar para o formulario anterior
			retornaEventoBotaoVoltar();
		}
		
		GroupLayout gl_painelEditavel = new GroupLayout(painelLadoEntao);
		gl_painelEditavel.setHorizontalGroup(
			gl_painelEditavel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_painelEditavel.createSequentialGroup()
					.addGap(17)
					.addGroup(gl_painelEditavel.createParallelGroup(Alignment.LEADING)
						.addComponent(comboBoxColunaEntao, GroupLayout.PREFERRED_SIZE, 231, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewJgoodiesLabel)
						.addComponent(lblDasRegrasAssociativas)
						.addComponent(lblEscolhaDoLado))
					.addContainerGap(37, Short.MAX_VALUE))
		);
		gl_painelEditavel.setVerticalGroup(
			gl_painelEditavel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_painelEditavel.createSequentialGroup()
					.addGap(32)
					.addComponent(lblEscolhaDoLado)
					.addGap(53)
					.addComponent(lblNewJgoodiesLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblDasRegrasAssociativas)
					.addGap(29)
					.addComponent(comboBoxColunaEntao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(164, Short.MAX_VALUE))
		);
		painelLadoEntao.setLayout(gl_painelEditavel);
		
		
		return painelLadoEntao;
	}
	
	public ActionListener retornaEventoBotaoAvancar(){
		return new ActionListener() {
			
			public void actionPerformed(ActionEvent event) {
			
				setVisible(false);
				getPlanilha().setColunaLadoEntaoEscolhida(comboBoxColunaEntao.getSelectedItem().toString());
				form = new FormEscolherLadoSe(getArq(), getPlanilha(), getCnm());
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
}
