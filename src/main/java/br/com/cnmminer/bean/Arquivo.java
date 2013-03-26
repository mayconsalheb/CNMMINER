/**
 * 
 */
package br.com.cnmminer.bean;

/**
 * Bean do arquivo.
 * @author felipe
 *
 */
public class Arquivo {
	
	//Constantes
	public static final String EXTENSAO_XLS = ".xls";
	public static final String EXTENSAO_XLSX = ".xlsx";
	
	//Atributos
	private String nomeArquivo;
	private String diretorioEntrada;
	private String diretorioSaida;
	private String extensao;
	
	public String getNomeArquivo() {
		return nomeArquivo;
	}
	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}
	public String getDiretorioEntrada() {
		return diretorioEntrada;
	}
	public void setDiretorioEntrada(String diretorioEntrada) {
		this.diretorioEntrada = diretorioEntrada;
	}
	public String getDiretorioSaida() {
		return diretorioSaida;
	}
	public void setDiretorioSaida(String diretorioSaida) {
		this.diretorioSaida = diretorioSaida;
	}
	public String getExtensao() {
		return extensao;
	}
	public void setExtensao(String extensao) {
		this.extensao = extensao;
	}

}
