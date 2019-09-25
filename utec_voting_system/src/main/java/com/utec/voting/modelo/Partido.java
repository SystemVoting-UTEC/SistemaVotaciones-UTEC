package com.utec.voting.modelo;

/**
 * @author Kevin Orellana
 * @version 1.0 Date: September 2019
 */
public class Partido {
	private Integer parId;
	private String parNombre;
	
	public Partido() {
		
	}
	
	/**
	 * @param parId
	 * @param parNombre
	 */
	public Partido(Integer parId, String parNombre) {
		super();
		this.parId = parId;
		this.parNombre = parNombre;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		return "Partido [parId=" + parId + ", parNombre=" + parNombre + "]";
	}
}
