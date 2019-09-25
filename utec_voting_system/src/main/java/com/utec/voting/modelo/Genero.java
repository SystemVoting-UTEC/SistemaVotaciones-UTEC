package com.utec.voting.modelo;

/**
 * @author Kevin Orellana
 * @version 1.0 Date: September 2019
 */
public class Genero {
	private Integer genId;
	private String genGenero;

	/**
	 * @param genId
	 * @param genGenero
	 */
	public Genero(Integer genId, String genGenero) {
		super();
		this.genId = genId;
		this.genGenero = genGenero;
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

	@Override
	public String toString() {
		return "Genero [genId=" + genId + ", genGenero=" + genGenero + "]";
	}
}
