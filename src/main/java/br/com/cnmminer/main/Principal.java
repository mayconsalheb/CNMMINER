package br.com.cnmminer.main;

import java.awt.EventQueue;

import javax.swing.JFrame;

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
					
					JFrame frameInicial = new JFrame();
					frameInicial.setResizable(false);
					frameInicial.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frameInicial.setBounds(100, 100, 659, 462);
					
					FormPrincipal frame = new FormBemVindo(null, null, null,frameInicial);
					frame.setFormAtual(frame);
					frame.obterConfiguracoesTela();
					frame.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
