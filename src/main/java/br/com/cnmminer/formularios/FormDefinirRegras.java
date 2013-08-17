package br.com.cnmminer.formularios;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
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

	public FormDefinirRegras(Arquivo arquivo, PlanilhaExcel planilhaExcel, Cnm nmc, JFrame frame) {
		super(arquivo, planilhaExcel, nmc, frame);
	}

	private FormPrincipal form;
	private JSpinner spinnerOrdem;
	private JSpinner spinnerSuporte;
	private JSpinner spinnerConfianca;
	

	public JPanel obterPainelEditavel() {

		JPanel painelDefinirRegras = new JPanel();
		
		JLabel lblCaractersticasDaMinerao = DefaultComponentFactory.getInstance().createLabel("Caracter\u00EDsticas da descoberta de regras");
		
		JLabel lblConfigureAquiAs = DefaultComponentFactory.getInstance().createLabel("Configure aqui as caracter\u00EDsticas das regras a serem descobertas:");
		
		JLabel lblOrdemMxima = DefaultComponentFactory.getInstance().createLabel("Ordem m\u00E1xima:");
		
		JLabel lblSuporteMnimo = DefaultComponentFactory.getInstance().createLabel("Suporte m\u00EDnimo(%):");
		
		JLabel lblNmeroDeCasos = DefaultComponentFactory.getInstance().createLabel("Confian\u00e7a(%):");
		
		spinnerOrdem = new JSpinner();
		
		spinnerSuporte = new JSpinner();
		
		spinnerConfianca = new JSpinner();
		
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
								.addComponent(spinnerConfianca, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))))
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
						.addComponent(spinnerConfianca, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(120, Short.MAX_VALUE))
		);
		painelDefinirRegras.setLayout(gl_painelEditavel);
		
		
		return painelDefinirRegras;
	}
	
	public ActionListener retornaEventoBotaoAvancar(){
		return new ActionListener() {

			public void actionPerformed(ActionEvent event) {

				if(validarSpinners(spinnerOrdem, spinnerConfianca, spinnerSuporte)){

					getCnm().setConfianca(Integer.parseInt(spinnerConfianca.getValue().toString()));
					getCnm().setOrderMaxima(Integer.parseInt(spinnerOrdem.getValue().toString()));
					getCnm().setSuporteMinimo(Integer.parseInt(spinnerSuporte.getValue().toString()));
					
					form = new FormDiretorioSaida(getArq(), getPlanilha(), getCnm(), getFrame());
					form.setFormAtual(form);
					form.setFormPai(getFormAtual());
					form.obterConfiguracoesTela();
				}else{
					
				}
				
				
			}
		};
	}
	
	/**
	 * Metodo responsavel por validar os spinners de suporte
	 * 
	 * @param spinOrdem
	 * @param spinConfianca
	 * @param spinSuporte
	 * @return
	 */
	protected boolean validarSpinners(JSpinner spinOrdem, JSpinner spinConfianca, JSpinner spinSuporte) {
		
		Integer valor;
		
		try {
			valor = Integer.parseInt(spinOrdem.getValue().toString());
			if(valor > getPlanilha().getColunasLadoSeEscolhida().size() || valor <= 0){
				JOptionPane.showMessageDialog(getFrame(), "Ordem invalida! Erro: Valor menor que 0 ou maior que quantidade de causas", 
						  "AVISO",JOptionPane.INFORMATION_MESSAGE);
				return false;
			}
			valor = Integer.parseInt(spinSuporte.getValue().toString());
			if(valor < 0 || valor > 100){
				JOptionPane.showMessageDialog(getFrame(), "Suporte invalido! Erro: Valor menor ou igual a 0 ou maior que 100", 
						  "AVISO",JOptionPane.INFORMATION_MESSAGE);
				return false;
			}
			valor = Integer.parseInt(spinConfianca.getValue().toString());
			if(valor <= 0 || valor > 100){
				JOptionPane.showMessageDialog(getFrame(), "Confian\u00e7a invalida! Erro: Valor menor ou igual a 0 ou maior que 100", 
						  "AVISO",JOptionPane.INFORMATION_MESSAGE);
				return false;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(getFrame(), "Excecao: "+e.getMessage(), 
					  "AVISO",JOptionPane.INFORMATION_MESSAGE);
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
