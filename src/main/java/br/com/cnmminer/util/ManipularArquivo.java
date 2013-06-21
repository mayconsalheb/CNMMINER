/**
 * 
 */
package br.com.cnmminer.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import br.com.cnmminer.bean.Arquivo;
import br.com.cnmminer.bean.LadoSe;
import br.com.cnmminer.bean.Neuronio;
import br.com.cnmminer.bean.PlanilhaExcel;

/**
 * Classe responsavel por manipular o arquivo Excel.
 * 
 * @author felipe
 *
 */
public class ManipularArquivo {

	private File file;
	private FileInputStream fis;
	private Row linhaPlanilha;
	private Sheet sheet;
	private String EXTENSAO_ARQ = ".xls";
	private HSSFWorkbook workbookOut;
	
	
	/**
	 * Metodo responsavel por recuperar nome do arquivo.
	 * 
	 * @param caminhoArquivo
	 * @return
	 */
	public String recuperarNomeArquivo(String caminhoArquivo) {

		StringTokenizer st = new StringTokenizer(caminhoArquivo, "/");
		String nomeArquivo = null;
		while(st.hasMoreTokens()){
			nomeArquivo = st.nextToken();
		}
		return nomeArquivo;
	}

	/**
	 * Metodo responsavel por recuperar diretorio de entrada do arquivo.
	 * 
	 * @param caminhoArquivo
	 * @param nomeArquivo 
	 * @return
	 */
	public String recuperarDiretorioEntrada(String caminhoArquivo, String nomeArquivo) {
		
		caminhoArquivo = caminhoArquivo.replace(nomeArquivo, "").trim();
		return caminhoArquivo;
	}

	/**
	 * Metodo responsavel por recuperar extensao do arquivo.
	 * 
	 * @param nomeArquivo
	 * @return
	 */
	public String recuperarExtensaoArquivo(String nomeArquivo) {
		
		if(nomeArquivo.endsWith(Arquivo.EXTENSAO_XLS))
			return Arquivo.EXTENSAO_XLS;
		else if(nomeArquivo.endsWith(Arquivo.EXTENSAO_XLSX))
			return Arquivo.EXTENSAO_XLSX;
		else
			return null;

	}

	/**
	 * Metodo responsavel por recuperar as planilhas do arquivo.
	 * 
	 * @return
	 */
	public ArrayList<String> recuperarPlanilhasArquivo(Arquivo arquivo, PlanilhaExcel planilhaExcel) {
		
		ArrayList<String> planilhas = new ArrayList<String>();
		
		if(arquivo.getExtensao().equals(Arquivo.EXTENSAO_XLS)){
			
			for(int i=0; i<planilhaExcel.getWorkbookXls().getNumberOfSheets();i++){
				
				planilhas.add(planilhaExcel.getWorkbookXls().getSheetName(i));
			}
			
		}else if(arquivo.getExtensao().equals(Arquivo.EXTENSAO_XLSX)){
			
			for(int i=0; i<planilhaExcel.getWorkbookXlsx().getNumberOfSheets();i++){
				
				planilhas.add(planilhaExcel.getWorkbookXlsx().getSheetName(i));
			}
			
		}

		planilhaExcel.setPlanilhas(planilhas);

		return planilhas;
	}
	
	/**
	 * Metodo responsavel por abrir o arquivo.
	 * 
	 * @param planilhaExcel 
	 * @param caminhoArquivo 
	 * 
	 * @return
	 */
	public boolean abrirArquivo(Arquivo arquivo, PlanilhaExcel planilhaExcel){
		
		if(arquivo != null){
			if(arquivo.getExtensao().equals(Arquivo.EXTENSAO_XLS)){
			
				try {
					file = new File(arquivo.getDiretorioEntrada()+arquivo.getNomeArquivo());
					fis = new FileInputStream(file);
					planilhaExcel.setWorkbookXls(new HSSFWorkbook(fis));					
					
					return true;
					
				} catch (Exception e) {
					e.printStackTrace();
					new JOptionPane();
					JOptionPane.showMessageDialog(null, "Nao foi possivel abrir o arquivo");
				}
				
			}else if(arquivo.getExtensao().equals(Arquivo.EXTENSAO_XLSX)){
				
				try {
					file = new File(arquivo.getDiretorioEntrada()+arquivo.getNomeArquivo());
					fis = new FileInputStream(file);
					planilhaExcel.setWorkbookXlsx(new XSSFWorkbook(fis));
					
					return true;
					
				}catch (Exception e) {
					e.printStackTrace();
					new JOptionPane();
					JOptionPane.showMessageDialog(null, "Nao foi possivel abrir o arquivo");
				}
			}
			
		}
			
		return false;
	}

	/**
	 * Metodo responsavel por recuperar as colunas do arquivo.
	 * 
	 * @return
	 */
	public ArrayList<String> recuperarColunas(Arquivo arquivo, PlanilhaExcel planilhaExcel) {
		
		ArrayList<String> colunas = new ArrayList<String>();
		if(arquivo!=null){
			
			if(arquivo.getExtensao().equals(Arquivo.EXTENSAO_XLS)){
				
				sheet = planilhaExcel.getWorkbookXls().getSheet(planilhaExcel.getPlanilhaEscolhida());

				linhaPlanilha = sheet.getRow(0);
				colunas = popularColunas(linhaPlanilha, colunas);
				
			}else if(arquivo.getExtensao().equals(Arquivo.EXTENSAO_XLSX)){
				
				sheet = planilhaExcel.getWorkbookXlsx().getSheet(planilhaExcel.getPlanilhaEscolhida());

				linhaPlanilha = sheet.getRow(0);
				colunas = popularColunas(linhaPlanilha, colunas);
				
			}
			planilhaExcel.setColunas(colunas);
		}
		
		return colunas;
	}

	/**
	 * Metodo responsavel por popular as colunas recuperadas da planilha
	 * 
	 * @param line
	 * @param colunas
	 * @return 
	 */
	private ArrayList<String> popularColunas(Row line, ArrayList<String> colunas) {
		
		if(line != null)
		{
			for(int i=0; i<linhaPlanilha.getLastCellNum();i++){
				
				colunas.add(linhaPlanilha.getCell(i).getStringCellValue());
				
			}
		}
		return colunas;
		
	}
	
	/**
	 * Metodo responsavel por criar o arquivo de saida contendo os dados com a analise do CNM
	 * 
	 * @param caminho
	 */
	public boolean criarArquivoSaida(String caminho) {

		FileOutputStream fos = null;
		try {
			HSSFWorkbook workbook = new HSSFWorkbook();
			fos = new FileOutputStream(new File(caminho+EXTENSAO_ARQ));
			
			HSSFSheet sheet = workbook.createSheet("Analise");
			
			workbook.write(fos);
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		finally{
			try {
				fos.flush();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				fos.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return true;
		
	}
	
	/**
	 * Metodo responsavel por recuperar os indices das colunas escolhidas como Lado Se
	 * 
	 * @param sheet
	 * @param planilha
	 * @return
	 */
	public ArrayList<Integer> recuperarIndicesColunasLadoSe(HSSFSheet sheet, PlanilhaExcel planilha){
	
		Row linha = sheet.getRow(0); 
		ArrayList<Integer> indiceColunaLadoSe = new ArrayList<Integer>();
		
		for (String planilhaEscolhida : planilha.getColunasLadoSeEscolhida()) {
			
			for(int i=0; i<linha.getLastCellNum();i++){
				
				if(linha.getCell(i).getStringCellValue().equals(planilhaEscolhida))
					indiceColunaLadoSe.add(i);
				
			}
		}
	
		return indiceColunaLadoSe;
		
	}
	
	/**
	 * Metodo responsavel por recuperar os indices das colunas escolhidas como Lado Se
	 * 
	 * @param sheet
	 * @param planilha
	 * @return
	 */
	public ArrayList<Integer> recuperarIndicesColunasLadoSe(XSSFSheet sheet, PlanilhaExcel planilha){
	
		Row linha = sheet.getRow(0); 
		ArrayList<Integer> indiceColunaLadoSe = new ArrayList<Integer>();
		
		for (String planilhaEscolhida : planilha.getColunasLadoSeEscolhida()) {
			
			for(int i=0; i<linha.getLastCellNum();i++){
				
				if(linha.getCell(i).getStringCellValue().equals(planilhaEscolhida))
					indiceColunaLadoSe.add(i);
				
			}
		}
	
		return indiceColunaLadoSe;
		
	}
	
	/**
	 * Metodo responsavel por recuperar o indice do Lado Entao escolhido
	 * 
	 * @param sheet
	 * @param planilha
	 * @return
	 */
	public Integer recuperarIndiceColunaLadoEntao(HSSFSheet sheet, PlanilhaExcel planilha){
	
		Row linha = sheet.getRow(0); 
		Integer indiceColunaLadoSe = null;
		
		for(int i=0; i<linha.getLastCellNum();i++){
			
			if(linha.getCell(i).getStringCellValue().equals(planilha.getColunaLadoEntaoEscolhida()))
				indiceColunaLadoSe = i;
		}
	
		return indiceColunaLadoSe;
		
	}

	/**
	 * Metodo responsavel por recuperar o indice do Lado Entao escolhido
	 * 
	 * @param sheet
	 * @param planilha
	 * @return
	 */
	public Integer recuperarIndiceColunaLadoEntao(XSSFSheet sheet, PlanilhaExcel planilha){
	
		Row linha = sheet.getRow(0); 
		Integer indiceColunaLadoSe = null;
		
		for(int i=0; i<linha.getLastCellNum();i++){
			
			if(linha.getCell(i).getStringCellValue().equals(planilha.getColunaLadoEntaoEscolhida()))
				indiceColunaLadoSe = i;
		}
	
		return indiceColunaLadoSe;
		
	}
	
	/**
	 * Metodo repsonsavel por recuperar o cabecalho de um Lado Se especifico
	 * 
	 * @param indice
	 * @param plan 
	 * @return
	 */
	public String recuperarCabecalhoLadoSe(Integer indice, HSSFSheet plan) {
		
		Row linha = plan.getRow(0);
		String valor = "";
		try {
			valor = String.valueOf(linha.getCell(indice).getStringCellValue().toString());	
		
		} catch (Exception e) {
			new JOptionPane();
			JOptionPane.showMessageDialog(null, "Cabecalho nao pode ser numerico ou nulo");
			System.exit(1);
		}
		 
		return valor;
	}

	/**
	 * Metodo repsonsavel por recuperar o cabecalho de um Lado Se especifico
	 * 
	 * @param indice
	 * @param plan 
	 * @return
	 */
	public String recuperarCabecalhoLadoSe(Integer indice, XSSFSheet plan) {
		
		Row linha = plan.getRow(0);
		String valor = "";
		try {
			valor = String.valueOf(linha.getCell(indice).getStringCellValue().toString());	
		
		} catch (Exception e) {
			new JOptionPane();
			JOptionPane.showMessageDialog(null, "Cabecalho nao pode ser numerico ou nulo");
			System.exit(1);
		}
		 
		return valor;
	}
	
	/**
	 * Metodo responsavel por escrever analise dos registros no arquivo
	 * 
	 * @param neuronios
	 * @param arquivoSaida
	 */
	public void escreverRegistrosArquivo(List<Neuronio> neuronios, String arquivoSaida) {

		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(new File(arquivoSaida));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		workbookOut = new HSSFWorkbook();
		HSSFSheet planilha = workbookOut.createSheet("Analise");
		HSSFRow linha; 
		HSSFCell celula;
		int contLinha = 0;
		int contCelula = 0;
		String primaria = "SE ";
		String concatenacao = " E ";
		String entao = "ENTAO ";

		try {
			
			linha = planilha.createRow(contLinha);
			
			criarCabecalho(linha, planilha);
			contLinha++;
			
			for (Neuronio object : neuronios) {
				
				contCelula = 0;
				int contAux = 0;
				linha = planilha.createRow(contLinha);
				celula = criarCelula(contCelula, linha);
				
				for (LadoSe evidencia : object.getEvidencias()) {
					
					if(contAux > 0)
						celula = escreverCelula(celula, concatenacao + evidencia.getEvidencia().toString());
					else
						celula = escreverCelula(celula, primaria + evidencia.getEvidencia().toString());
					
					contAux++;
					
				}
				
				DecimalFormat format = new DecimalFormat("0.00");
				
				contCelula++;
				celula = criarCelula(contCelula, linha);
				celula = escreverCelula(celula, entao + (object.getHipotese() == null ? "" : object.getHipotese().toString()));
				
				contCelula++;
				celula = criarCelula(contCelula, linha);
				celula = escreverCelula(celula, object.getAcumulador() == null ? "" : object.getAcumulador().toString());
				
				contCelula++;
				celula = criarCelula(contCelula, linha);
				celula = escreverCelula(celula, object.getConfianca() == null ? "" : format.format(object.getConfianca()).toString());
				
				contCelula++;
				celula = criarCelula(contCelula, linha);
				celula = escreverCelula(celula, object.getSuporte() == null ? "" : format.format(object.getSuporte()).toString());
				
				contLinha++;
			}

			workbookOut.write(fos);
			
		} catch (Exception e) {
		
			e.printStackTrace();
			
		} finally {
			try {
				fos.flush();
				fos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Metodo responsavel por criar cabecalho de arquivo de saida
	 * 
	 * @param linha
	 * @param planilha
	 * @param contLinha 
	 */
	private void criarCabecalho(HSSFRow linha, HSSFSheet planilha) {
		
		HSSFCell celula;
		
		celula = criarCelula(0, linha);
		celula = escreverCelula(celula, "LADO SE");
		celula = criarCelula(1, linha);
		celula = escreverCelula(celula, "LADO ENTAO");
		celula = criarCelula(2, linha);
		celula = escreverCelula(celula, "Num. de Casos");
		celula = criarCelula(3, linha);
		celula = escreverCelula(celula, "CONFIANCA");
		celula = criarCelula(4, linha);
		celula = escreverCelula(celula, "SUPORTE");
		
		
	}

	/**
	 * Metodo responsavel por inserir valor na celula
	 * 
	 * @param celula
	 * @param valor
	 * @return 
	 */
	private HSSFCell escreverCelula(HSSFCell celula, String valor) {
		
		celula.setCellValue(celula.getStringCellValue().toString()+valor);
		
		return celula;
		
	}

	/**
	 * Metodo responsavel por criar celula
	 * 
	 * @param contCelula
	 * @param linha
	 * @return 
	 */
	private HSSFCell criarCelula(int contCelula, HSSFRow linha) {
		
		 return linha.createCell(contCelula);
		
	}
	
}
