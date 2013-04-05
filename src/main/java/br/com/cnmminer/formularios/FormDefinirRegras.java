package br.com.cnmminer.formularios;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.jgoodies.forms.factories.DefaultComponentFactory;

/**
 * Classe respons�vel por apresentar formul�rio de defini��o de regras de minera��o.
 * 
 * @author felipe
 *
 */
public class FormDefinirRegras extends FormPrincipal {

	private static final long serialVersionUID = 464135877308267958L;
	
	private FormPrincipal form;
	
	public FormDefinirRegras() {

	}

	public JPanel painelEditavel() {

		JPanel painelDefinirRegras = new JPanel();
		
		JLabel lblCaractersticasDaMinerao = DefaultComponentFactory.getInstance().createLabel("Caracter\u00EDsticas da descoberta de regras");
		
		JLabel lblConfigureAquiAs = DefaultComponentFactory.getInstance().createLabel("Configure aqui as caracter\u00EDsticas das regras a serem descobertas:");
		
		JLabel lblOrdemMxima = DefaultComponentFactory.getInstance().createLabel("Ordem m\u00E1xima:");
		
		JLabel lblSuporteMnimo = DefaultComponentFactory.getInstance().createLabel("Suporte m\u00EDnimo(%):");
		
		JLabel lblNmeroDeCasos = DefaultComponentFactory.getInstance().createLabel("N\u00FAmero de casos:");
		
		JSpinner spinner = new JSpinner();
		
		JSpinner spinner_1 = new JSpinner();
		
		JSpinner spinner_2 = new JSpinner();
		GroupLayout gl_painelEditavel = new GroupLayout(painelDefinirRegras);
		gl_painelEditavel.setHorizontalGroup(
			gl_painelEditavel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_painelEditavel.createSequentialGroup()
					.addGroup(gl_painelEditavel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_painelEditavel.createSequentialGroup()
							.addGap(21)
							.addGroup(gl_painelEditavel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblConfigureAquiAs)
								.addComponent(lblCaractersticasDaMinerao)))
						.addGroup(gl_painelEditavel.createSequentialGroup()
							.addGap(42)
							.addGroup(gl_painelEditavel.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblSuporteMnimo)
								.addComponent(lblNmeroDeCasos)
								.addComponent(lblOrdemMxima))
							.addGap(31)
							.addGroup(gl_painelEditavel.createParallelGroup(Alignment.LEADING)
								.addComponent(spinner_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(spinner_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(23, Short.MAX_VALUE))
		);
		gl_painelEditavel.setVerticalGroup(
			gl_painelEditavel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_painelEditavel.createSequentialGroup()
					.addGap(28)
					.addComponent(lblCaractersticasDaMinerao)
					.addGap(39)
					.addComponent(lblConfigureAquiAs)
					.addGap(44)
					.addGroup(gl_painelEditavel.createParallelGroup(Alignment.TRAILING)
						.addComponent(spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblOrdemMxima))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_painelEditavel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblSuporteMnimo)
						.addComponent(spinner_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_painelEditavel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNmeroDeCasos)
						.addComponent(spinner_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(120, Short.MAX_VALUE))
		);
		painelDefinirRegras.setLayout(gl_painelEditavel);
		
		
		return painelDefinirRegras;
	}
	
	public ActionListener retornaEventoBotaoAvancar(){
		return new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				setVisible(false);
				form = new FormEscolherIndice();
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
