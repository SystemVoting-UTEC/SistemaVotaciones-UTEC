package com.utec.voting.modelo;

/**
 * @author Kevin Orellana
 * @version 1.0 Date: September 2019
 */
public class Municipio {
	private Integer munId;
	private String munNombre;
	private Departamento munDepId;

	/**
	 * @param munId
	 * @param munNombre
	 * @param munDepId
	 */
	public Municipio(Integer munId, String munNombre, Departamento munDepId) {
		super();
		this.munId = munId;
		this.munNombre = munNombre;
		this.munDepId = munDepId;
	}

	/**
	 * @return the munId
	 */
	public Integer getMunId() {
		return munId;
	}

	/**
	 * @param munId the munId to set
	 */
	public void setMunId(Integer munId) {
		this.munId = munId;
	}

	/**
	 * @return the munNombre
	 */
	public String getMunNombre() {
		return munNombre;
	}

	/**
	 * @param munNombre the munNombre to set
	 */
	public void setMunNombre(String munNombre) {
		this.munNombre = munNombre;
	}

	/**
	 * @return the munDepId
	 */
	public Departamento getMunDepId() {
		return munDepId;
	}

	/**
	 * @param munDepId the munDepId to set
	 */
	public void setMunDepId(Departamento munDepId) {
		this.munDepId = munDepId;
	}

	@Override
	public String toString() {
		return "Municipio [munId=" + munId + ", munNombre=" + munNombre + ", munDepId=" + munDepId + "]";
	}

}
