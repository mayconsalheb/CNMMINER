/**
 * 
 */
package br.com.cnmminer.formularios;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.jgoodies.forms.factories.DefaultComponentFactory;

/**
 * Classe respons�vel por apresentar formul�rio de escolha do lado ENT�O para minera��o de dados.
 * 
 * @author felipe
 *
 */
public class FormEscolherLadoEntao extends FormPrincipal {

	private static final long serialVersionUID = 5153797266678607223L;
	
	private FormPrincipal form;

	public FormEscolherLadoEntao() {
	}
	
	public JPanel painelEditavel() {

		JPanel painelLadoEntao = new JPanel();
		
		JLabel lblEscolhaDoLado = DefaultComponentFactory.getInstance().createLabel("Escolha do lado ENT\u00C3O");
		
		JLabel lblNewJgoodiesLabel = DefaultComponentFactory.getInstance().createLabel("Selecione neste campo qual coluna da tabela ser\u00E1 o lado ENT\u00C3O");
		
		JLabel lblDasRegrasAssociativas = DefaultComponentFactory.getInstance().createLabel("das regras associativas que o sistema ir\u00E1 gerar:");
		
		JComboBox comboBox = new JComboBox();
		GroupLayout gl_painelEditavel = new GroupLayout(painelLadoEntao);
		gl_painelEditavel.setHorizontalGroup(
			gl_painelEditavel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_painelEditavel.createSequentialGroup()
					.addGap(17)
					.addGroup(gl_painelEditavel.createParallelGroup(Alignment.LEADING)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 231, GroupLayout.PREFERRED_SIZE)
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
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(164, Short.MAX_VALUE))
		);
		painelLadoEntao.setLayout(gl_painelEditavel);
		
		
		return painelLadoEntao;
	}
	
	public ActionListener retornaEventoBotaoAvancar(){
		return new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				setVisible(false);
				form = new FormEscolherLadoSe();
				form.setArq(getArq());
				form.setPlanilha(getPlanilha());
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
