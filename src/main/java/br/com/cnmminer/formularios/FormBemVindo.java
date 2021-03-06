package br.com.cnmminer.formularios;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;

import br.com.cnmminer.bean.Arquivo;
import br.com.cnmminer.bean.Cnm;
import br.com.cnmminer.bean.PlanilhaExcel;

import com.jgoodies.forms.factories.DefaultComponentFactory;

public class FormBemVindo extends FormPrincipal{


	private FormPrincipal formCarregarArq;
	private Arquivo arq;
	private PlanilhaExcel planilha;
	
	public FormBemVindo(Arquivo arquivo, PlanilhaExcel planilha, Cnm cnm, JFrame frame) {
		super(arquivo, planilha, cnm,frame);
	}
	
	@Override
	public JPanel obterPainelEditavel(){

		JPanel painelBemVindo = new JPanel();
		
		JLabel lblBemVindoAo = DefaultComponentFactory.getInstance().createLabel("Bem vindo ao CNM Miner");
		
		JLabel lblNewJgoodiesLabel = DefaultComponentFactory.getInstance().createLabel("Assistente para an\u00E1lise de dados");
		
		JLabel lblNewJgoodiesLabel_1 = DefaultComponentFactory.getInstance().createLabel("O Assistente o guiar\u00E1 pelos passos necess\u00E1rios para ");
		
		JLabel lblAnalisarUmNovo = DefaultComponentFactory.getInstance().createLabel("analisar\num novo arquivo do Microsoft Excel.");
		GroupLayout gl_painelEditavel = new GroupLayout(painelBemVindo);
		gl_painelEditavel.setHorizontalGroup(
			gl_painelEditavel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_painelEditavel.createSequentialGroup()
					.addContainerGap(171, Short.MAX_VALUE)
					.addComponent(lblBemVindoAo)
					.addGap(163))
				.addGroup(gl_painelEditavel.createSequentialGroup()
					.addGap(15)
					.addGroup(gl_painelEditavel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblAnalisarUmNovo)
						.addComponent(lblNewJgoodiesLabel_1)
						.addComponent(lblNewJgoodiesLabel))
					.addContainerGap(105, Short.MAX_VALUE))
		);
		gl_painelEditavel.setVerticalGroup(
			gl_painelEditavel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_painelEditavel.createSequentialGroup()
					.addGap(18)
					.addComponent(lblBemVindoAo)
					.addGap(39)
					.addComponent(lblNewJgoodiesLabel)
					.addGap(35)
					.addComponent(lblNewJgoodiesLabel_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblAnalisarUmNovo)
					.addContainerGap(197, Short.MAX_VALUE))
		);
		painelBemVindo.setLayout(gl_painelEditavel);
		
		
		
		return painelBemVindo;
	}
	
	public ActionListener retornaEventoBotaoAvancar(){
		return new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				formCarregarArq = new FormCarregarArquivo(arq, planilha, getCnm(),getFrame());
				formCarregarArq.setFormAtual(formCarregarArq);
				formCarregarArq.setFormPai(getFormAtual());
				formCarregarArq.obterConfiguracoesTela();
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
