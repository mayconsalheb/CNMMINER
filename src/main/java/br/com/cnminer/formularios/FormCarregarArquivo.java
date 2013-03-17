package br.com.cnminer.formularios;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.jgoodies.forms.factories.DefaultComponentFactory;

public class FormCarregarArquivo extends FormPrincipal {
	public FormCarregarArquivo() {
	}

	private static final long serialVersionUID = 2033196326835124700L;

	/**
	 * Create the panel.
	 */
	
	public static void main(String[] args) {
		
		FormCarregarArquivo form = new FormCarregarArquivo();
		form.setVisible(true);
		
	}

	@Override
	public JPanel painelEditavel() {

		JPanel painelCarregarArquivo = new JPanel();

		JLabel lblDadosDeOrigem = DefaultComponentFactory.getInstance().createLabel("Dados de origem");
		
		JLabel lblLocalizaoDoArquivo = DefaultComponentFactory.getInstance().createLabel("Localiza\u00E7\u00E3o do arquivo Microsoft Excel:");
		
		JEditorPane editorLocalArquivoExcel = new JEditorPane();
		
		JButton button_4 = new JButton("...");
		
		JLabel lblAgoraEscolhaA = DefaultComponentFactory.getInstance().createLabel("Agora escolha a planilha que deseja analisar:");
		
		JComboBox comboBox = new JComboBox();
		GroupLayout gl_painelEditavel = new GroupLayout(painelCarregarArquivo);
		gl_painelEditavel.setHorizontalGroup(
			gl_painelEditavel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_painelEditavel.createSequentialGroup()
					.addGroup(gl_painelEditavel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_painelEditavel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_painelEditavel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_painelEditavel.createSequentialGroup()
									.addComponent(editorLocalArquivoExcel, GroupLayout.PREFERRED_SIZE, 381, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(button_4, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblDadosDeOrigem)
								.addComponent(lblLocalizaoDoArquivo)
								.addComponent(lblAgoraEscolhaA)))
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 246, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(8, Short.MAX_VALUE))
		);
		gl_painelEditavel.setVerticalGroup(
			gl_painelEditavel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_painelEditavel.createSequentialGroup()
					.addGap(28)
					.addComponent(lblDadosDeOrigem)
					.addGap(44)
					.addComponent(lblLocalizaoDoArquivo)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_painelEditavel.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(button_4, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
						.addComponent(editorLocalArquivoExcel, Alignment.LEADING))
					.addGap(44)
					.addComponent(lblAgoraEscolhaA)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(134, Short.MAX_VALUE))
		);
		painelCarregarArquivo.setLayout(gl_painelEditavel);
		
		return painelCarregarArquivo;
		
	}
	
	
	

}
