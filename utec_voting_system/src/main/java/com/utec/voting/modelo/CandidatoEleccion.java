/**
 * 
 */
package com.utec.voting.modelo;

/**
 * @author Kevin Orellana
 *
 */
public class CandidatoEleccion {
	private Integer celId;
	private Eleccion elcId;
	private Candidato canId;
	
	public CandidatoEleccion() {
		
	}

	/**
	 * @param celId
	 * @param elcId
	 * @param canId
	 */
	public CandidatoEleccion(Integer celId, Eleccion elcId, Candidato canId) {
		this.celId = celId;
		this.elcId = elcId;
		this.canId = canId;
	}

	/**
	 * @return the celId
	 */
	public Integer getCelId() {
		return celId;
	}

	/**
	 * @param celId the celId to set
	 */
	public void setCelId(Integer celId) {
		this.celId = celId;
	}

	/**
	 * @return the elcId
	 */
	public Eleccion getElcId() {
		return elcId;
	}

	/**
	 * @param elcId the elcId to set
	 */
	public void setElcId(Eleccion elcId) {
		this.elcId = elcId;
	}

	/**
	 * @return the canId
	 */
	public Candidato getCanId() {
		return canId;
	}

	/**
	 * @param canId the canId to set
	 */
	public void setCanId(Candidato canId) {
		this.canId = canId;
	}

	@Override
	public String toString() {
		return "CandidatoEleccion [celId=" + celId + ", elcId=" + elcId + ", canId=" + canId + "]";
	}
}
