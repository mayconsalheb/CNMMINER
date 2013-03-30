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

	
}
