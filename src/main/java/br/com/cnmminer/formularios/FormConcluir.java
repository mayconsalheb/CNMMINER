package br.com.cnmminer.formularios;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;

import br.com.cnmminer.bean.Arquivo;
import br.com.cnmminer.bean.Cnm;
import br.com.cnmminer.bean.PlanilhaExcel;
import br.com.cnmminer.util.ManipularArquivo;

import com.jgoodies.forms.factories.DefaultComponentFactory;

/**
 * Classe respons�vel por apresentar o formul�riorio de conclus�o.
 * 
 * @author felipe
 *
 */
public class FormConcluir extends FormPrincipal {

	public FormConcluir(Arquivo arquivo, PlanilhaExcel planilhaExcel, Cnm cnm, JFrame frame) {
		super(arquivo, planilhaExcel, cnm, frame);
	}

	private ManipularArquivo manipularArquivo;

	public JPanel obterPainelEditavel() {

		JPanel painelConcluir = new JPanel();
		
		JLabel lblPronto = DefaultComponentFactory.getInstance().createLabel("PRONTO!");
		
		JLabel lblCliqueEmConcluir = DefaultComponentFactory.getInstance().createLabel("Clique em concluir para exibir os resultados da minera\u00E7\u00E3o");
		
		JLabel lblDosDados = DefaultComponentFactory.getInstance().createLabel("dos dados!");
		
		JLabel lblObrigadoPorUsar = DefaultComponentFactory.getInstance().createLabel("Obrigado por usar o CNM MINER!");
		GroupLayout gl_painelEditavel = new GroupLayout(painelConcluir);
		gl_painelEditavel.setHorizontalGroup(
			gl_painelEditavel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_painelEditavel.createSequentialGroup()
					.addGap(22)
					.addGroup(gl_painelEditavel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblObrigadoPorUsar)
						.addComponent(lblCliqueEmConcluir)
						.addComponent(lblDosDados)
						.addComponent(lblPronto))
					.addGap(68))
		);
		gl_painelEditavel.setVerticalGroup(
			gl_painelEditavel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_painelEditavel.createSequentialGroup()
					.addGap(33)
					.addComponent(lblPronto)
					.addGap(53)
					.addComponent(lblCliqueEmConcluir)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblDosDados)
					.addGap(48)
					.addComponent(lblObrigadoPorUsar)
					.addContainerGap(155, Short.MAX_VALUE))
		);
		painelConcluir.setLayout(gl_painelEditavel);
		
		
		return painelConcluir;
	}
	
	public ActionListener retornaEventoBotaoAvancar(){
		return new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				
			}
		};
	}
	
	public ActionListener retornaEventoBotaoConcluir(){
		return new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				
				if(manipularArquivo == null){
					manipularArquivo = new ManipularArquivo();
				}
				
				try {
					Desktop desktop = Desktop.getDesktop();  
					desktop.open(new File(getArq().getDiretorioSaida()));  
				} catch (Exception e) {
					e.printStackTrace();
				}
					
					
			}
		};
	}

}
