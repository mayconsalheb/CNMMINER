package br.com.cnminer.formularios;

import java.awt.EventQueue;

import javax.swing.JPanel;
import javax.swing.JCheckBox;

public class FormCarregaArquivos extends FormPrincipal {

	private static final long serialVersionUID = 1L;
	
	public FormCarregaArquivos(){
		
	}
	
	@Override
	public JPanel painelEditavel() {
		JPanel painelParaEdicao = new JPanel();
		JCheckBox chckbxNewCheckBox = new JCheckBox("New check box");
		painelParaEdicao.add(chckbxNewCheckBox);
		return painelParaEdicao;
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormPrincipal frame = new FormCarregaArquivos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
