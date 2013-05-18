package br.com.cnmminer.formularios;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import br.com.cnmminer.bean.Arquivo;
import br.com.cnmminer.bean.Cnm;
import br.com.cnmminer.bean.PlanilhaExcel;

import com.jgoodies.forms.factories.DefaultComponentFactory;

public class FormEscolherLadoSe extends FormPrincipal {

	public FormEscolherLadoSe(Arquivo arquivo, PlanilhaExcel planilhaExcel, Cnm cnm) {
		super(arquivo, planilhaExcel, cnm);
	}

	private static final long serialVersionUID = -374994753484472282L;
	
	private FormPrincipal form;
	private JList list;
	
	
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
		
		list = new JList();
		
		Integer cont = 0;
		
		ArrayList<String>colunas=getPlanilha().getColunas();
		Object[] colunasLista = new Object[colunas.size()];
		
		for (String col : colunas) {
			
			colunasLista[cont] = new JCheckBox(col);
			cont++;
		}
		
		list.setListData(colunasLista);
		
		list.setCellRenderer(new CheckBoxCellRenderer());

		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setBounds(27, 223, 419, 98);
		
		//Listener que permite que os botoes sejam marcados
		list.addMouseListener(new MouseAdapter(){ 
			public void mousePressed(MouseEvent e){
				int index = list.locationToIndex(e.getPoint());         
				if(index != -1){
					JCheckBox checkbox = (JCheckBox) list.getModel().getElementAt(index);
					checkbox.setSelected(!checkbox.isSelected());           
					repaint();
					}
				}
			});
		
		painelEscolherLadoSe.add(scrollPane);
		
		return painelEscolherLadoSe;
	}
	
	public ActionListener retornaEventoBotaoAvancar(){
		return new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				setVisible(false);
				
				ArrayList<String> itens = new ArrayList<String>();
				itens = recuperarItensMarcados();
				if(itens.size() <= 0){
					setVisible(true);
					new JOptionPane().showMessageDialog(null, "Escolha pelo menos uma opcao");
				}else{
					getPlanilha().setColunasLadoSeEscolhida(itens);
					
					form = new FormDefinirRegras(getArq(), getPlanilha(), getCnm());
					form.setFrameAtual(form);
					form.setFramePai(getFrameAtual());
					form.setVisible(true);
				}
			}
		};
	}
	
	/**
	 * MŽtodo responsavel por recuperar os itens que foram marcados no Box
	 * 
	 * @return
	 */
	protected ArrayList<String> recuperarItensMarcados() {

		  ArrayList<String> itens = new ArrayList<String>();
		 
		  for(int i = 0; i < list.getModel().getSize(); i++){             
			  JCheckBox checkbox = (JCheckBox) list.getModel().getElementAt(i);               
			  if(checkbox.isSelected())                 
				  itens.add(checkbox.getText());
		  }
		  
		return itens;
	}

	public ActionListener retornaEventoBotaoConcluir(){
		return new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				
			}
		};
	}

	
	/**
	 * Classe interna que permite expor os objetos recuperados na lista
	 * 
	 * @author felipe
	 *
	 */
	private class CheckBoxCellRenderer implements ListCellRenderer {

		 Border noFocusBorder = new EmptyBorder(1, 1, 1, 1);  
		 
		 public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus){     
			 JCheckBox checkbox = (JCheckBox) value;
			 checkbox.setBackground(isSelected ? list.getSelectionBackground() : list.getBackground());     
			 checkbox.setForeground(isSelected ? list.getSelectionForeground() : list.getForeground());     
			 checkbox.setEnabled(list.isEnabled());     
			 checkbox.setFont(list.getFont());     
			 checkbox.setFocusPainted(false);
			 checkbox.setBorderPainted(true);     
			 checkbox.setBorder(isSelected ? UIManager.getBorder("List.focusCellHighlightBorder") : noFocusBorder);     
			 
			 return checkbox;
		 }
		
	}

	
}
