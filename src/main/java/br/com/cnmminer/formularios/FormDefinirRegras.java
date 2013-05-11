package br.com.cnmminer.formularios;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.LayoutStyle.ComponentPlacement;

import br.com.cnmminer.bean.Arquivo;
import br.com.cnmminer.bean.Cnm;
import br.com.cnmminer.bean.PlanilhaExcel;

import com.jgoodies.forms.factories.DefaultComponentFactory;

/**
 * Classe responsavel por apresentar formulario de definicao de regras de mineracao.
 * 
 * @author felipe
 *
 */
public class FormDefinirRegras extends FormPrincipal {

	public FormDefinirRegras(Arquivo arquivo, PlanilhaExcel planilhaExcel, Cnm nmc) {
		super(arquivo, planilhaExcel, nmc);
	}

	private static final long serialVersionUID = 464135877308267958L;
	
	private FormPrincipal form;
	private JSpinner spinnerOrdem;
	private JSpinner spinnerSuporte;
	private JSpinner spinnerNumCasos;
	

	public JPanel painelEditavel() {

		JPanel painelDefinirRegras = new JPanel();
		
		JLabel lblCaractersticasDaMinerao = DefaultComponentFactory.getInstance().createLabel("Caracter\u00EDsticas da descoberta de regras");
		
		JLabel lblConfigureAquiAs = DefaultComponentFactory.getInstance().createLabel("Configure aqui as caracter\u00EDsticas das regras a serem descobertas:");
		
		JLabel lblOrdemMxima = DefaultComponentFactory.getInstance().createLabel("Ordem m\u00E1xima:");
		
		JLabel lblSuporteMnimo = DefaultComponentFactory.getInstance().createLabel("Suporte m\u00EDnimo(%):");
		
		JLabel lblNmeroDeCasos = DefaultComponentFactory.getInstance().createLabel("N\u00FAmero de casos:");
		
		spinnerOrdem = new JSpinner();
		
		spinnerSuporte = new JSpinner();
		
		spinnerNumCasos = new JSpinner();
		
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
							.addGap(18)
							.addGroup(gl_painelEditavel.createParallelGroup(Alignment.LEADING)
								.addComponent(spinnerOrdem, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
								.addComponent(spinnerSuporte, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
								.addComponent(spinnerNumCasos, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))))
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
						.addComponent(lblOrdemMxima)
						.addComponent(spinnerOrdem, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_painelEditavel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblSuporteMnimo)
						.addComponent(spinnerSuporte, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_painelEditavel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNmeroDeCasos)
						.addComponent(spinnerNumCasos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(120, Short.MAX_VALUE))
		);
		painelDefinirRegras.setLayout(gl_painelEditavel);
		
		
		return painelDefinirRegras;
	}
	
	public ActionListener retornaEventoBotaoAvancar(){
		return new ActionListener() {

			public void actionPerformed(ActionEvent event) {

				if(validarSpinners(spinnerOrdem, spinnerNumCasos, spinnerSuporte)){

					//Settar cnm
					getCnm().setNumeroCasos(Integer.parseInt(spinnerNumCasos.getValue().toString()));
					getCnm().setOrderMaxima(Integer.parseInt(spinnerOrdem.getValue().toString()));
					getCnm().setSuporteMinimo(Integer.parseInt(spinnerSuporte.getValue().toString()));
					
					setVisible(false);
					form = new FormConcluir(getArq(), getPlanilha(), getCnm());
					form.setFrameAtual(form);
					form.setFramePai(getFrameAtual());
					form.setVisible(true);
				}else{
					JOptionPane.showMessageDialog(form, "Apenas valores inteiros e acima de 0 s‹o permitidos!", 
												  "AVISO",JOptionPane.INFORMATION_MESSAGE);
				}
				
				
			}
		};
	}
	
	/**
	 * MŽtodo responsavel por validar os spinners de suporte
	 * 
	 * @param spinOrdem
	 * @param spinNumCasos
	 * @param spinSuporte
	 * @return
	 */
	protected boolean validarSpinners(JSpinner spinOrdem, JSpinner spinNumCasos, JSpinner spinSuporte) {
		
		Integer valor;
		
		try {
			valor = Integer.parseInt(spinOrdem.getValue().toString());
			if(valor <= 0)
				return false;
			valor = Integer.parseInt(spinSuporte.getValue().toString());
			if(valor <= 0 || valor > 100)
				return false;
			valor = Integer.parseInt(spinNumCasos.getValue().toString());
			if(valor < getPlanilha().getColunasLadoSeEscolhida().size())
				return false;
		} catch (Exception e) {
			
			return false;
		}
			
		return true;
	}

	public ActionListener retornaEventoBotaoConcluir(){
		return new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				
			}
		};
	}
}
