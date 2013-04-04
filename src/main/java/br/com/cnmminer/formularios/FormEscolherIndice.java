package br.com.cnmminer.formularios;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.jgoodies.forms.factories.DefaultComponentFactory;

/**
 * Classe respons�vel por apresentar formul�rio de escolha de �ndice para minera��o de dados.
 * 
 * @author felipe
 *
 */
public class FormEscolherIndice extends FormPrincipal {

	private static final long serialVersionUID = 2869664018981879475L;
	
	private FormPrincipal form;

	public FormEscolherIndice() {

	}

	public JPanel painelEditavel(){

		JPanel painelEscolherIndice = new JPanel();
		
		JLabel lblNewJgoodiesLabel = DefaultComponentFactory.getInstance().createLabel("Escolha do \u00EDndice");
		
		JLabel lblEscolhaOIndice = DefaultComponentFactory.getInstance().createLabel("Escolha o indice a ser usado na descoberta de regras:");
		
		JComboBox comboBox = new JComboBox();
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
				form = new FormConcluir();
				form.setArq(getArq());
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
