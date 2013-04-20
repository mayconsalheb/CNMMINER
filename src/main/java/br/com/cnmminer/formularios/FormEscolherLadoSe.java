package br.com.cnmminer.formularios;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import br.com.cnmminer.bean.Arquivo;
import br.com.cnmminer.bean.Cnm;
import br.com.cnmminer.bean.PlanilhaExcel;

import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.JCheckBox;

public class FormEscolherLadoSe extends FormPrincipal {

	public FormEscolherLadoSe(Arquivo arquivo, PlanilhaExcel planilhaExcel, Cnm cnm) {
		super(arquivo, planilhaExcel, cnm);
	}

	private static final long serialVersionUID = -374994753484472282L;
	
	private FormPrincipal form;
	
	
	public JPanel painelEditavel() {

		JPanel painelEscolherLadoSe = new JPanel();

		JLabel lblEscolhaDasCausas = DefaultComponentFactory.getInstance().createLabel("Escolha das causas");
		lblEscolhaDasCausas.setBounds(27, 23, 144, 14);
		
		JLabel lblMarqueTodosOs = DefaultComponentFactory.getInstance().createLabel("Marque todos os camos que poder\u00E3o ser usados pelo sistema");
		lblMarqueTodosOs.setBounds(27, 70, 364, 14);
		
		JLabel lblAFim = DefaultComponentFactory.getInstance().createLabel("a fim de descobrir rela\u00E7\u00E0o entre eles e o lado ENT\u00C0O informado");
		lblAFim.setBounds(27, 90, 376, 14);
		
		JLabel lblAnteriormente = DefaultComponentFactory.getInstance().createLabel("anteriormente.");
		lblAnteriormente.setBounds(27, 110, 117, 14);
		
		JLabel lblAtributosDasCausas = DefaultComponentFactory.getInstance().createLabel("Atributos das causas:");
		lblAtributosDasCausas.setBounds(27, 166, 165, 14);
		
		JPanel painelLadoSe = new JPanel();
		JScrollPane scroller = new JScrollPane(painelLadoSe);
		scroller.setBounds(0, 0, 0, 0);
		
		System.out.println(getPlanilha().getColunaLadoEntaoEscolhida());
		painelEscolherLadoSe.setLayout(null);
		painelEscolherLadoSe.add(scroller, BorderLayout.CENTER);
		painelEscolherLadoSe.add(scroller);
		painelEscolherLadoSe.add(lblAnteriormente);
		painelEscolherLadoSe.add(lblAFim);
		painelEscolherLadoSe.add(lblAtributosDasCausas);
		painelEscolherLadoSe.add(lblMarqueTodosOs);
		painelEscolherLadoSe.add(lblEscolhaDasCausas);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 223, 419, 98);
		painelEscolherLadoSe.add(scrollPane);
		
		JList list = new JList();
		scrollPane.setViewportView(list);
		JCheckBox chckbxNewCheckBox;
	   
		
		
		ArrayList<String>colunas=getPlanilha().getColunas();
		
		for (int i = 0; i < colunas.size(); i++) {
			System.out.println("Nome"+colunas.get(i));
			chckbxNewCheckBox= new JCheckBox(colunas.get(i));
			scrollPane.setColumnHeaderView(chckbxNewCheckBox);
			
		}
		
		
		
		
	
		
		
		return painelEscolherLadoSe;
	}
	
	public ActionListener retornaEventoBotaoAvancar(){
		return new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				setVisible(false);
				form = new FormDefinirRegras(getArq(), getPlanilha(), getCnm());
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
