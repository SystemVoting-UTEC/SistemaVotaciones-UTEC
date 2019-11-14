package com.utec.voting.modelo;

/**
 * @author Kevin Orellana
 * @version 1.0 Date: September 2019
 */
public class Genero {
	private Integer genId;
	private String genGenero;
	private String genNombre;
	
	public Genero() {
	}

	/**
	 * @param genId
	 * @param genGenero
	 * @param genNombre
	 */
	public Genero(Integer genId, String genGenero, String genNombre) {
		this.genId = genId;
		this.genGenero = genGenero;
		this.genNombre = genNombre;
	}

	/**
	 * @return the genId
	 */
	public Integer getGenId() {
		return genId;
	}

	/**
	 * @param genId the genId to set
	 */
	public void setGenId(Integer genId) {
		this.genId = genId;
	}

	/**
	 * @return the genGenero
	 */
	public String getGenGenero() {
		return genGenero;
	}

	/**
	 * @param genGenero the genGenero to set
	 */
	public void setGenGenero(String genGenero) {
		this.genGenero = genGenero;
	}

	/**
	 * @return the genNombre
	 */
	public String getGenNombre() {
		return genNombre;
	}

	/**
	 * @param genNombre the genNombre to set
	 */
	public void setGenNombre(String genNombre) {
		this.genNombre = genNombre;
	}

	@Override
	public String toString() {
		return "Genero [genId=" + genId + ", genGenero=" + genGenero + ", genNombre=" + genNombre + "]";
	}
}
