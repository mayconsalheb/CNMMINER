/**
 * 
 */
package br.com.cnmminer.util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.eventusermodel.examples.FromHowTo;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import br.com.cnmminer.bean.Arquivo;
import br.com.cnmminer.bean.LadoSe;
import br.com.cnmminer.bean.Neuronio;
import br.com.cnmminer.bean.PlanilhaExcel;

/**
 * @author felipe
 *
 */
public class Aprendizado{
	
	private PlanilhaExcel planilha = null;
	private Arquivo arquivo = null;
	private Row linha;
	
	private ArrayList<Integer> indicesColunaLadoSe = null;
	private Integer indiceColunaLadoEntao;
	private ManipularArquivo manipularArquivo;
	private ArrayList<TreeSet<Object>> objetosCombinados;
	private ArrayList<LadoSe> evidencias;
	
	public Aprendizado(PlanilhaExcel planilhaExcel, Arquivo arq) {
	  	
		setPlanilha(planilhaExcel);
	  	setArquivo(arq);
	}
	
	/**
	 * MŽtodo respons‡vel por gerar a rede neural
	 * 
	 * @return
	 */
	public List<Neuronio> gerarRedeNeural(){
		
		List<Neuronio> neuronios = new ArrayList<Neuronio>();
		ArrayList<Object> objCombinatorios = null;
		
		
		if(manipularArquivo == null)
			manipularArquivo = new ManipularArquivo();
		
		if(getArquivo().getExtensao().equals(Arquivo.EXTENSAO_XLS)){
			
			HSSFSheet sheet = planilha.getWorkbookXls().getSheet(planilha.getPlanilhaEscolhida());
			
			indicesColunaLadoSe = manipularArquivo.recuperarIndicesColunasLadoSe(sheet, planilha);
			indiceColunaLadoEntao = manipularArquivo.recuperarIndiceColunaLadoEntao(sheet, planilha);
			
			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				
				objCombinatorios = new ArrayList<Object>();
				linha = sheet.getRow(i);
			
				for (Integer indice : indicesColunaLadoSe) {
					
					LadoSe lado = new LadoSe();
					lado.setEvidencia(recuperarObjetoTipo(linha.getCell(indice)));
					lado.setCabecalho(manipularArquivo.recuperarCabecalhoLadoSe(indice, sheet));
					
					if(isObjectvalido(lado)){
						objCombinatorios.add(lado); 
						
					}
							
				}
				
				objetosCombinados = obterCombinacoes(objCombinatorios.toArray());
				
				for (TreeSet<Object> objetoComb : objetosCombinados) {
					
					Neuronio neuronio = new Neuronio();
					evidencias = new ArrayList<LadoSe>();
					
					for (Iterator iterator = objetoComb.iterator(); iterator.hasNext();) {
						
						LadoSe ladoSe = new LadoSe();
						Object object = (Object) iterator.next();

						ladoSe.setEvidencia(object);
						
						evidencias.add(ladoSe);
						
					}
					
					neuronio.setEvidencias(evidencias);
					Object valor =  recuperarObjetoTipo(linha.getCell(indiceColunaLadoEntao));
					if(isObjectvalido(valor))
						neuronio.setHipotese(valor);
					
					if(!neuronios.contains(neuronio)){

						neuronio.setAcumulador(1);
						neuronios.add(neuronio);
					}else if(neuronios.contains(neuronio)){
						Neuronio neuronioaux = new Neuronio();
						neuronioaux = neuronios.get(neuronios.indexOf(neuronio));
						neuronioaux.setAcumulador(neuronioaux.getAcumulador()+1);
					}
						
					
				}
				
			}
			
		}else if(getArquivo().getExtensao().equals(Arquivo.EXTENSAO_XLSX)){
			
			XSSFSheet sheet = planilha.getWorkbookXlsx().getSheet(planilha.getPlanilhaEscolhida());
			
			//TODO: FAZER O MESMO DE CIMA
			Row linha = sheet.getRow(0); 
			for (String planilhaEscolhida : planilha.getColunasLadoSeEscolhida()) {
				
				for(int i=0; i<linha.getLastCellNum();i++){
					
					linha = sheet.getRow(i);
					if(linha.getCell(i).getStringCellValue().equals(planilhaEscolhida))
						indicesColunaLadoSe.add(i);
					
				}
			}
			
			for (int i = 0; i < sheet.getLastRowNum(); i++) {
				
			}			
		}

		for (Neuronio object : neuronios) {
			System.out.println("--------------------------------");
			for (LadoSe evidencia : object.getEvidencias()) {
				System.out.println("EVIDENCIA: " + evidencia.getEvidencia().toString());
			}
			System.out.println("HIPOTESE: " + object.getHipotese());
			System.out.println("ACUMULADOR: " + object.getAcumulador());
			
			System.out.println("-------------------------------");
		}
		return neuronios;
		
		
		
	}
	
	private Object recuperarObjetoTipo(Cell cell) {
		
		Object valor = null;
		if(cell != null && !cell.toString().trim().isEmpty()){
			
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_STRING:
				valor = cell.getStringCellValue();
				break;
			case Cell.CELL_TYPE_BOOLEAN:
				valor = cell.getBooleanCellValue();
				break;
			case Cell.CELL_TYPE_NUMERIC:
				valor = cell.getNumericCellValue();
				break;
			default:
				break;
			}
		}
		
		return valor;
	}

	/**
	 * MŽtodo responsavel por validar objeto.
	 * 
	 * @param valor
	 * @return
	 */
	private boolean isObjectvalido(Object valor) {

		if(valor!=null && !valor.toString().trim().isEmpty())
			return true;
		
		return false;
	}

	/**
	 * MŽtodo responsavel por obter combina›es das evidencias.
	 * 
	 * @param status
	 * @return
	 */
	private ArrayList<TreeSet<Object>> obterCombinacoes(Object[] status){
	
		SortedSet<SortedSet<Comparable>> allCombList = new TreeSet<SortedSet<Comparable>>(new Comparator<SortedSet<Comparable>>() {
			
			public int compare(SortedSet<Comparable> o1, SortedSet<Comparable> o2) {
				int sizeComp = o1.size() - o2.size();
				if (sizeComp == 0) {
					Iterator<Comparable> o1iIterator = o1.iterator();
					Iterator<Comparable> o2iIterator = o2.iterator();
					while (sizeComp == 0 && o1iIterator.hasNext() ) {
						sizeComp = o1iIterator.next().compareTo(o2iIterator.next());
					}
				}
				
				return sizeComp;
			}
		});
		
		for (Object nstatus : status) {
			allCombList.add(new TreeSet<Comparable>(Arrays.asList(nstatus.toString())));
		}
		
		for (int nivel = 1; nivel < status.length; nivel++) {
			List<SortedSet<Comparable>> statusAntes = new ArrayList<SortedSet<Comparable>>(allCombList);
			for (Set<Comparable> antes : statusAntes) {
				SortedSet<Comparable> novo = new TreeSet<Comparable>(antes);
				novo.add(status[nivel].toString());
				allCombList.add(novo);
			}
		}
		
		System.out.println(allCombList);
		ArrayList<TreeSet<Object>> comboList = new ArrayList<TreeSet<Object>>();
		
		Object[] arrayObjetos = allCombList.toArray();
		for (int i = 0; i < arrayObjetos.length; i++) {
			comboList.add((TreeSet<Object>) arrayObjetos[i]);
		}
		
		return comboList;
	}

	/**
	 * @return the planilha
	 */
	public PlanilhaExcel getPlanilha() {
		return planilha;
	}


	/**
	 * @param planilha the planilha to set
	 */
	public void setPlanilha(PlanilhaExcel planilha) {
		this.planilha = planilha;
	}

	public Arquivo getArquivo() {
		return arquivo;
	}

	public void setArquivo(Arquivo arquivo) {
		this.arquivo = arquivo;
	}

}
