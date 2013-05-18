package br.com.cnmminer.bean;

import java.util.ArrayList;

/**
 * @author felipe
 *
 */
public class No {

	private ArrayList<LadoSe> evidencias;
	private Integer acumulador;
	private String hipotese;

	public Integer getAcumulador() {
		return acumulador;
	}
	public void setAcumulador(Integer acumulador) {
		this.acumulador = acumulador;
	}
	public String getHipotese() {
		return hipotese;
	}
	public void setHipotese(String hipotese) {
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
		No other = (No) obj;
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
