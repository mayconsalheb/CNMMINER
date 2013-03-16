/**
 * 
 */
package br.com.cnminer.formularios;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author felipe
 *
 */
public class FrmPrincipal extends JFrame{
	
	JPanel panel;
	JLabel msg;
	private JButton buttonNext;
	private JButton buttonBack; 
	private Dimension d;
	
	public FrmPrincipal()
	{
		super("Java!");
	}
	
	public void criaJanela(){
		
		panel = new JPanel();
		msg = new JLabel("Projeto Final de merda");
		buttonNext = new JButton("Proximo");
		buttonBack = new JButton("Voltar");
		panel.add(msg);
		panel.add(buttonBack);
		panel.add(buttonNext);
		
		getContentPane().add(panel, BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Dimension d = new Dimension(200, 200);
		getContentPane().setPreferredSize(d);
		pack();
		setVisible(true);
	}
	
	public static void main(String args[])
	{
		FrmPrincipal s = new FrmPrincipal();
		s.criaJanela();
	}

}
