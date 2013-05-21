package br.com.cnmminer.bean;

/**
 * @author felipe
 *
 */
public class LadoSe {

	private String cabecalho;
	private Object evidencia;

	public Object getEvidencia() {
		return evidencia;
	}

	public void setEvidencia(Object evidencia) {
		this.evidencia = evidencia;
	}

	public String getCabecalho() {
		return cabecalho;
	}

	public void setCabecalho(String cabecalho) {
		this.cabecalho = cabecalho;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((cabecalho == null) ? 0 : cabecalho.hashCode());
		result = prime * result
				+ ((evidencia == null) ? 0 : evidencia.hashCode());
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
		LadoSe other = (LadoSe) obj;
		if (cabecalho == null) {
			if (other.cabecalho != null)
				return false;
		} else if (!cabecalho.equals(other.cabecalho))
			return false;
		if (evidencia == null) {
			if (other.evidencia != null)
				return false;
		} else if (!evidencia.equals(other.evidencia))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return  evidencia + ";" + cabecalho;
	}

	
}
