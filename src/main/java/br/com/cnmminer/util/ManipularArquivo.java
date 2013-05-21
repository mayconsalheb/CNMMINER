/**
 * 
 */
package br.com.cnmminer.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import br.com.cnmminer.bean.Arquivo;
import br.com.cnmminer.bean.PlanilhaExcel;

/**
 * Classe respons�vel por manipular o arquivo Excel.
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
	
	
	/**
	 * M�todo respons�vel por recuperar nome do arquivo.
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
	 * M�todo respons�vel por recuperar diret�rio de entrada do arquivo.
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
	 * M�todo respons�vel por recuperar extens�o do arquivo.
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
	 * M�todo respons�vel por recuperar as planilhas do arquivo.
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
	 * M�todo respons�vel por abrir o arquivo.
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
					JOptionPane.showMessageDialog(null, "N�o foi poss�vel abrir o arquivo");
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
					JOptionPane.showMessageDialog(null, "N�o foi poss�vel abrir o arquivo");
				}
			}
			
		}
			
		return false;
	}

	/**
	 * M�todo respons�vel por recuperar as colunas do arquivo.
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
	 * M�todo respons�vel por popular as colunas recuperadas da planilha
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
	 * M�todo respons�vel por criar o arquivo de saida contendo os dados com a an�lise do CNM
	 * 
	 * @param caminho
	 */
	public boolean criarArquivoSaida(String caminho) {

		FileOutputStream fos = null;
		try {
			HSSFWorkbook workbook = new HSSFWorkbook();
			fos = new FileOutputStream(new File(caminho+EXTENSAO_ARQ));
			
			HSSFSheet sheet = workbook.createSheet("An�lise");
			
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
	 * M�todo respons�vel por recuperar os indices das colunas escolhidas como Lado Se
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
	 * M�todo respons�vel por recuperar o indice do Lado Entao escolhido
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
	 * M�todo repsonsavel por recuperar o cabecalho de um Lado Se especifico
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
			JOptionPane.showMessageDialog(null, "Cabe�alho n�o pode ser num�rico ou nulo");
			System.exit(1);
		}
		 
		return valor;
	}
	
}
