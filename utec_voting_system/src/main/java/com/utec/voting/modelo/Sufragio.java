package com.utec.voting.modelo;

/**
 * @author Kevin Orellana
 * @version 1.0 Date: September 2019
 */
public class Sufragio {
	private Integer sufId;
	private Persona sufPerDui;
	private Candidato sufCanId;
	private Double sufSufragio;
	
	/**
	 * @param sufId
	 * @param sufPerDui
	 * @param sufCanId
	 * @param sufSufragio
	 */
	public Sufragio(Integer sufId, Persona sufPerDui, Candidato sufCanId, Double sufSufragio) {
		super();
		this.sufId = sufId;
		this.sufPerDui = sufPerDui;
		this.sufCanId = sufCanId;
		this.sufSufragio = sufSufragio;
	}

	/**
	 * @return the sufId
	 */
	public Integer getSufId() {
		return sufId;
	}

	/**
	 * @param sufId the sufId to set
	 */
	public void setSufId(Integer sufId) {
		this.sufId = sufId;
	}

	/**
	 * @return the sufPerDui
	 */
	public Persona getSufPerDui() {
		return sufPerDui;
	}

	/**
	 * @param sufPerDui the sufPerDui to set
	 */
	public void setSufPerDui(Persona sufPerDui) {
		this.sufPerDui = sufPerDui;
	}

	/**
	 * @return the sufCanId
	 */
	public Candidato getSufCanId() {
		return sufCanId;
	}

	/**
	 * @param sufCanId the sufCanId to set
	 */
	public void setSufCanId(Candidato sufCanId) {
		this.sufCanId = sufCanId;
	}

	/**
	 * @return the sufSufragio
	 */
	public Double getSufSufragio() {
		return sufSufragio;
	}

	/**
	 * @param sufSufragio the sufSufragio to set
	 */
	public void setSufSufragio(Double sufSufragio) {
		this.sufSufragio = sufSufragio;
	}

	@Override
	public String toString() {
		return "Sufragio [sufId=" + sufId + ", sufPerDui=" + sufPerDui + ", sufCanId=" + sufCanId + ", sufSufragio=" + sufSufragio + "]";
	}
}