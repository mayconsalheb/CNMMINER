/**
 * 
 */
package br.com.cnmminer.bean;

/**
 * Classe que representa o Cnm
 * 
 * @author felipe
 *
 */
public class Cnm {

	private Integer numeroCasos;
	private Integer orderMaxima;
	private Integer suporteMinimo;
	private String indice;
	
	/**
	 * Construtor
	 * 
	 * @param numeroCasos
	 * @param ordemMaxima
	 * @param suporteMinimo
	 * @param indice
	 */
	public Cnm(Integer numeroCasos, Integer ordemMaxima, Integer suporteMinimo, String indice) {
		
		setNumeroCasos(numeroCasos);
		setOrderMaxima(ordemMaxima);
		setSuporteMinimo(suporteMinimo);
		setIndice(indice);
	}
	
	public Integer getNumeroCasos() {
		return numeroCasos;
	}
	public void setNumeroCasos(Integer numeroCasos) {
		this.numeroCasos = numeroCasos;
	}
	public Integer getOrderMaxima() {
		return orderMaxima;
	}
	public void setOrderMaxima(Integer orderMaxima) {
		this.orderMaxima = orderMaxima;
	}
	public Integer getSuporteMinimo() {
		return suporteMinimo;
	}
	public void setSuporteMinimo(Integer suporteMinimo) {
		this.suporteMinimo = suporteMinimo;
	}
	public String getIndice() {
		return indice;
	}
	public void setIndice(String indice) {
		this.indice = indice;
	}
}
