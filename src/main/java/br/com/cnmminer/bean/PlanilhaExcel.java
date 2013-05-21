/**
 * 
 */
package br.com.cnmminer.bean;

import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author felipe
 *
 */
public class PlanilhaExcel {

	private ArrayList<String> planilhas;
	private ArrayList<String> colunas;
	private HSSFWorkbook workbookXls;
	private XSSFWorkbook workbookXlsx;
	private String planilhaEscolhida;
	private String colunaLadoEntaoEscolhida;
	private ArrayList<String> colunasLadoSeEscolhida;

	
	public ArrayList<String> getPlanilhas() {
		return planilhas;
	}

	public void setPlanilhas(ArrayList<String> planilhas) {
		this.planilhas = planilhas;
	}

	public ArrayList<String> getColunas() {
		return colunas;
	}

	public void setColunas(ArrayList<String> colunas) {
		this.colunas = colunas;
	}

	public HSSFWorkbook getWorkbookXls() {
		return workbookXls;
	}

	public void setWorkbookXls(HSSFWorkbook workbookXls) {
		this.workbookXls = workbookXls;
	}

	public XSSFWorkbook getWorkbookXlsx() {
		return workbookXlsx;
	}

	public void setWorkbookXlsx(XSSFWorkbook workbookXlsx) {
		this.workbookXlsx = workbookXlsx;
	}

	public String getPlanilhaEscolhida() {
		return planilhaEscolhida;
	}

	public void setPlanilhaEscolhida(String planilhaEscolhida) {
		this.planilhaEscolhida = planilhaEscolhida;
	}

	public String getColunaLadoEntaoEscolhida() {
		return colunaLadoEntaoEscolhida;
	}

	public void setColunaLadoEntaoEscolhida(String colunaLadoEntaoEscolhida) {
		this.colunaLadoEntaoEscolhida = colunaLadoEntaoEscolhida;
	}

	public ArrayList<String> getColunasLadoSeEscolhida() {
		return colunasLadoSeEscolhida;
	}

	public void setColunasLadoSeEscolhida(ArrayList<String> colunasLadoSeEscolhida) {
		this.colunasLadoSeEscolhida = colunasLadoSeEscolhida;
	}

}
