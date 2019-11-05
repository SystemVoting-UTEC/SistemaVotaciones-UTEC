package com.utec.voting.modelo;

/**
 * @author Kevin Orellana
 * @version 1.0 Date: September 2019
 */
public class Partido {
	private Integer parId;
	private String parNombre;
	private Integer estado;
	
	public Partido() {
		
	}
	
	/**
	 * @param parId
	 * @param parNombre
	 */
	public Partido(Integer parId, String parNombre, Integer estado) {
		super();
		this.parId = parId;
		this.parNombre = parNombre;
		this.estado = estado;
	}
	/**
	 * @return the parId
	 */
	public Integer getParId() {
		return parId;
	}
	/**
	 * @param parId the parId to set
	 */
	public void setParId(Integer parId) {
		this.parId = parId;
	}
	/**
	 * @return the parNombre
	 */
	public String getParNombre() {
		return parNombre;
	}
	/**
	 * @param parNombre the parNombre to set
	 */
	public void setParNombre(String parNombre) {
		this.parNombre = parNombre;
	}

	/**
	 * @return the estado
	 */
	public Integer getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((parId == null) ? 0 : parId.hashCode());
		result = prime * result + ((parNombre == null) ? 0 : parNombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Partido other = (Partido) obj;
		if (estado == null) {
			if (other.estado != null)
				return false;
		} else if (!estado.equals(other.estado))
			return false;
		if (parId == null) {
			if (other.parId != null)
				return false;
		} else if (!parId.equals(other.parId))
			return false;
		if (parNombre == null) {
			if (other.parNombre != null)
				return false;
		} else if (!parNombre.equals(other.parNombre))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Partido [parId=" + parId + ", parNombre=" + parNombre + ", estado=" + estado + "]";
	}
	
}
