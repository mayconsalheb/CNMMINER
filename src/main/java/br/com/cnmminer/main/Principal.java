package br.com.cnmminer.main;

import java.awt.EventQueue;

import br.com.cnmminer.formularios.FormBemVindo;
import br.com.cnmminer.formularios.FormPrincipal;

public class Principal {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormPrincipal frame = new FormBemVindo(null, null, null);
					frame.setFrameAtual(frame);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
