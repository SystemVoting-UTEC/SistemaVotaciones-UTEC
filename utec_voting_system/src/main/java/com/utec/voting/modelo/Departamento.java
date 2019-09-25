package com.utec.voting.modelo;

/**
 * @author Kevin Orellana
 * @version 1.0 Date: September 2019
 */
public class Departamento {
	private Integer depId;
	private String depNombre;
	
	/**
	 * @param depId
	 * @param depNombre
	 */
	public Departamento(Integer depId, String depNombre) {
		super();
		this.depId = depId;
		this.depNombre = depNombre;
	}

	/**
	 * @return the depId
	 */
	public Integer getDepId() {
		return depId;
	}

	/**
	 * @param depId the depId to set
	 */
	public void setDepId(Integer depId) {
		this.depId = depId;
	}

	/**
	 * @return the depNombre
	 */
	public String getDepNombre() {
		return depNombre;
	}

	/**
	 * @param depNombre the depNombre to set
	 */
	public void setDepNombre(String depNombre) {
		this.depNombre = depNombre;
	}

	@Override
	public String toString() {
		return "Departamento [depId=" + depId + ", depNombre=" + depNombre + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((depId == null) ? 0 : depId.hashCode());
		result = prime * result + ((depNombre == null) ? 0 : depNombre.hashCode());
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
		Departamento other = (Departamento) obj;
		if (depId == null) {
			if (other.depId != null)
				return false;
		} else if (!depId.equals(other.depId))
			return false;
		if (depNombre == null) {
			if (other.depNombre != null)
				return false;
		} else if (!depNombre.equals(other.depNombre))
			return false;
		return true;
	}
}
