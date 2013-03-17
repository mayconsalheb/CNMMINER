package br.com.cnminer.formularios;

import java.awt.EventQueue;

import javax.swing.JPanel;

public class FormBemVindo extends FormPrincipal{
	public FormBemVindo() {
	}

	private static final long serialVersionUID = 3318317450049633366L;

	@Override
	public JPanel painelEditavel() {
		// TODO Auto-generated method stub
		return new JPanel();
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormPrincipal frame = new FormBemVindo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
