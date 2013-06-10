package br.com.cnmminer.bean;

import java.util.ArrayList;

/**
 * @author felipe
 *
 */
public class Neuronio {

	private ArrayList<LadoSe> evidencias;
	private Integer acumulador;
	private Object hipotese;
	private Double suporte;
	private Double confianca;
	private Integer forca;

	public Integer getAcumulador() {
		return acumulador;
	}
	public void setAcumulador(Integer acumulador) {
		this.acumulador = acumulador;
	}
	public Object getHipotese() {
		return hipotese;
	}
	public void setHipotese(Object hipotese) {
		this.hipotese = hipotese;
	}
	/**
	 * @return the evidencias
	 */
	public ArrayList<LadoSe> getEvidencias() {
		return evidencias;
	}
	/**
	 * @param evidencias the evidencias to set
	 */
	public void setEvidencias(ArrayList<LadoSe> evidencias) {
		this.evidencias = evidencias;
	}
	
	/**
	 * @return the suporte
	 */
	public Double getSuporte() {
		return suporte;
	}
	/**
	 * @param suporte the suporte to set
	 */
	public void setSuporte(Double suporte) {
		this.suporte = suporte;
	}
	/**
	 * @return the confianca
	 */
	public Double getConfianca() {
		return confianca;
	}
	/**
	 * @param confianca the confianca to set
	 */
	public void setConfianca(Double confianca) {
		this.confianca = confianca;
	}
	/**
	 * @return the forca
	 */
	public Integer getForca() {
		return forca;
	}
	/**
	 * @param forca the forca to set
	 */
	public void setForca(Integer forca) {
		this.forca = forca;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((hipotese == null) ? 0 : hipotese.hashCode());
		result = prime * result + ((evidencias == null) ? 0 : evidencias.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Neuronio other = (Neuronio) obj;
		if (hipotese == null) {
			if (other.hipotese != null)
				return false;
		} else if (!hipotese.equals(other.hipotese))
			return false;
		if (evidencias == null) {
			if (other.evidencias != null)
				return false;
		} else if (evidencias.size() != other.evidencias.size() || !evidencias.containsAll(other.evidencias))
			return false;
		return true;
	}
	
}
