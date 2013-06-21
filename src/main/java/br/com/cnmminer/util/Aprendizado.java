/**
 * 
 */
package br.com.cnmminer.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import br.com.cnmminer.bean.Arquivo;
import br.com.cnmminer.bean.Cnm;
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
	private Cnm cnm = null;
	private Row linha;
	
	private ArrayList<Integer> indicesColunaLadoSe = null;
	private Integer indiceColunaLadoEntao;
	private ManipularArquivo manipularArquivo;
	private ArrayList<TreeSet<Object>> objetosCombinados;
	private ArrayList<LadoSe> evidencias;
	
	public Aprendizado(PlanilhaExcel planilhaExcel, Arquivo arq, Cnm cnm) {
	  	
		setPlanilha(planilhaExcel);
	  	setArquivo(arq);
	  	setCnm(cnm);
	}
	
	/**
	 * Metodo responsavel por gerar a rede neural
	 * 
	 * @return
	 */
	public List<Neuronio> gerarRedeNeural(){
		Integer numCasos = 0;
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
				
				numCasos +=  montarRede(neuronios, objCombinatorios);
				
			}
			
		}else if(getArquivo().getExtensao().equals(Arquivo.EXTENSAO_XLSX)){
			
			XSSFSheet sheet = planilha.getWorkbookXlsx().getSheet(planilha.getPlanilhaEscolhida());
			
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
				
				numCasos += montarRede(neuronios, objCombinatorios);
				
			}
						
		}
		
		calcularSuporte(neuronios,numCasos);

		gerarCompensacao(neuronios);
		
		neuronios = podarRedePorSuporteOuForca(neuronios);
		calcularConfianca(neuronios);
		neuronios = podarRedePorConfianca(neuronios);
		
		//TODO: Escrevendo registros
		for (Neuronio object : neuronios) {
			System.out.println("--------------------------------");
			for (LadoSe evidencia : object.getEvidencias()) {
				System.out.println("EVIDENCIA: " + evidencia.getEvidencia().toString());
			}
			System.out.println("HIPOTESE: " + object.getHipotese());
			System.out.println("ACUMULADOR: " + object.getAcumulador());
			System.out.println("SUPORTE: " + object.getSuporte());
			System.out.println("CONFIAN‚A:" + object.getConfianca());
			
			System.out.println("-------------------------------");
		}
		return neuronios;
		
	}

	private List<Neuronio> podarRedePorConfianca(List<Neuronio> neuronios) {
		List<Neuronio> neuroniosAux = new ArrayList<Neuronio>(neuronios);
		
		for (Neuronio neuronio : neuronios) {
			
			if(neuronio.getConfianca() < cnm.getConfianca()){
				neuroniosAux.remove(neuronio);
			}
		}
		
		return neuroniosAux; 
	}

	private void calcularConfianca(List<Neuronio> neuronios) {
		Map<Object, Integer> mapa = obterAcumuladorDasClasses(neuronios);
		
		for (Neuronio neuronio : neuronios) {
			Double confianca = (1.0* neuronio.getAcumulador() / mapa.get(neuronio.getHipotese()) ) * 100;
			neuronio.setConfianca(confianca);
		}
		
	}

	private Map<Object, Integer> obterAcumuladorDasClasses(List<Neuronio> neuronios) {
		Map<Object, Integer> mapa = new HashMap<Object, Integer>();
		
		for (Neuronio neuronio : neuronios) {
				Integer acumulador = mapa.get(neuronio.getHipotese());
				
				if(acumulador == null){
					acumulador = 0;
				}
				
				mapa.put(neuronio.getHipotese(), neuronio.getAcumulador() + acumulador);
		}
		return mapa;
	}

	private List<Neuronio> podarRedePorSuporteOuForca(List<Neuronio> neuronios) {
		List<Neuronio> neuroniosAux = new ArrayList<Neuronio>(neuronios);
		
		for (Neuronio neuronio : neuronios) {
			
			if(neuronio.getForca() <= 0 || neuronio.getSuporte() < cnm.getSuporteMinimo()){
				neuroniosAux.remove(neuronio);
			}
		}
		
		return neuroniosAux; 
	}

	private void calcularSuporte(List<Neuronio> neuronios, Integer numCasos) {
		Integer acumulador=0;
		for (Neuronio neuronio : neuronios) {
			acumulador = neuronio.getAcumulador();
			neuronio.setSuporte(((1.0 * acumulador / numCasos) * 100));
		}
		
	}

	/**
	 * Metodo responsavel por gerar as compensacoes
	 * 
	 * @param neuronios
	 */
	private void gerarCompensacao(List<Neuronio> neuronios) {
		
		for  (Neuronio neuronio : neuronios) {
			Integer soma=0;	
			
			for (Neuronio neuronio2 : neuronios) {
				if(neuronio.getEvidencias().equals(neuronio2.getEvidencias()) 
				   && !neuronio.getHipotese().equals(neuronio2.getHipotese())){
					soma += neuronio2.getAcumulador();
				}
			}
			neuronio.setForca(neuronio.getAcumulador()-soma);
			System.out.println("Forca neuronio: " + neuronio.getForca());
			System.out.println("Evidencia: "+neuronio.getEvidencias());
			System.out.println("Hipotese: "+ neuronio.getHipotese());
		}
		
	}

	/**
	 * Metodo responsavel por montar a rede
	 * 
	 * @param neuronios
	 * @param objCombinatorios
	 */
	private Integer montarRede(List<Neuronio> neuronios, ArrayList<Object> objCombinatorios) {
		Integer numCasos=0;
		objetosCombinados = obterCombinacoes(objCombinatorios.toArray());
		
		for (TreeSet<Object> objetoComb : objetosCombinados) {
			
			Neuronio neuronio = new Neuronio();
			evidencias = new ArrayList<LadoSe>();
			
			for (Iterator iterator = objetoComb.iterator(); iterator.hasNext();) {
				
				LadoSe ladoSe = new LadoSe();
				Object object = (Object) iterator.next();
	
				ladoSe = recuperarLadoSe(object);
				
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
			numCasos++;
		}
		
		return numCasos;
	}

	/**
	 * Metodo responsavel por separar cabecalho da evidencia
	 * 
	 * @param object
	 * @return
	 */
	private LadoSe recuperarLadoSe(Object object) {
		
		LadoSe ladoSe = new LadoSe();

		if(object != null){
			String string[] = object.toString().split(";");
			ladoSe.setEvidencia(string[0]);
			ladoSe.setCabecalho(string[1]);
		}
		
		
		return ladoSe;
	}

	/**
	 * Metodo responsavel por recuperar o tipo do objeto na celula
	 * @param cell
	 * @return
	 */
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
	 * Metodo responsavel por validar objeto.
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
	 * Metodo responsavel por validar objeto.
	 * 
	 * @param valor
	 * @return
	 */
	private boolean isObjectvalido(LadoSe valor) {

		if(valor != null && valor.getEvidencia() != null && !valor.getEvidencia().toString().trim().isEmpty())
			return true;
		
		return false;
	}

	
	/**
	 * Metodo responsavel por obter combinacoes das evidencias.
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
				if(antes.size() == getCnm().getOrderMaxima()){
					break;
				}
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

	public Cnm getCnm() {
		return cnm;
	}

	public void setCnm(Cnm cnm) {
		this.cnm = cnm;
	}

}
