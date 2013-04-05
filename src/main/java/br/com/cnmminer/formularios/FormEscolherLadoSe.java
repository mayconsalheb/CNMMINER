package br.com.cnmminer.formularios;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.jgoodies.forms.factories.DefaultComponentFactory;

public class FormEscolherLadoSe extends FormPrincipal {

	private static final long serialVersionUID = -374994753484472282L;
	
	private FormPrincipal form;

	public JPanel painelEditavel() {

		JPanel painelEscolherLadoSe = new JPanel();

		JLabel lblEscolhaDasCausas = DefaultComponentFactory.getInstance().createLabel("Escolha das causas");
		
		JLabel lblMarqueTodosOs = DefaultComponentFactory.getInstance().createLabel("Marque todos os camos que poder\u00E3o ser usados pelo sistema");
		
		JLabel lblAFim = DefaultComponentFactory.getInstance().createLabel("a fim de descobrir rela\u00E7\u00E0o entre eles e o lado ENT\u00C0O informado");
		
		JLabel lblAnteriormente = DefaultComponentFactory.getInstance().createLabel("anteriormente.");
		
		JLabel lblAtributosDasCausas = DefaultComponentFactory.getInstance().createLabel("Atributos das causas:");
		
		JPanel painelLadoSe = new JPanel();
		JScrollPane scroller = new JScrollPane(painelLadoSe);
		painelEscolherLadoSe.add(scroller, BorderLayout.CENTER);

		
		GroupLayout gl_painelEditavel = new GroupLayout(painelEscolherLadoSe);

		gl_painelEditavel.setHorizontalGroup(
			gl_painelEditavel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_painelEditavel.createSequentialGroup()
					.addGap(27)
					.addGroup(gl_painelEditavel.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(painelLadoSe, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblAnteriormente, Alignment.LEADING)
						.addComponent(lblAFim, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblAtributosDasCausas, Alignment.LEADING)
						.addComponent(lblMarqueTodosOs, Alignment.LEADING)
						.addComponent(lblEscolhaDasCausas, Alignment.LEADING))
					.addContainerGap(30, Short.MAX_VALUE))
		);
		gl_painelEditavel.setVerticalGroup(
			gl_painelEditavel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_painelEditavel.createSequentialGroup()
					.addGap(23)
					.addComponent(lblEscolhaDasCausas)
					.addGap(33)
					.addComponent(lblMarqueTodosOs)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblAFim)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblAnteriormente)
					.addGap(42)
					.addComponent(lblAtributosDasCausas)
					.addGap(18)
					.addComponent(painelLadoSe, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(15, Short.MAX_VALUE))
		);
		painelEscolherLadoSe.setLayout(gl_painelEditavel);
		
		return painelEscolherLadoSe;
	}
	
	public ActionListener retornaEventoBotaoAvancar(){
		return new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				setVisible(false);
				form = new FormDefinirRegras();
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
