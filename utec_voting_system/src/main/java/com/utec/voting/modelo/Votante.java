package com.utec.voting.modelo;

import java.sql.Date;

/**
 * @author Kevin Orellana
 * @version 1.0 Date: September 2019
 */
public class Votante {
	private Persona votPerDui;
	private Date votFechaVence;
	private Date votFechaExp;
	
	/**
	 * @param votPerDui
	 * @param votFechaVence
	 * @param votFechaExp
	 */
	public Votante(Persona votPerDui, Date votFechaVence, Date votFechaExp) {
		super();
		this.votPerDui = votPerDui;
		this.votFechaVence = votFechaVence;
		this.votFechaExp = votFechaExp;
	}

	/**
	 * @return the votPerDui
	 */
	public Persona getVotPerDui() {
		return votPerDui;
	}

	/**
	 * @param votPerDui the votPerDui to set
	 */
	public void setVotPerDui(Persona votPerDui) {
		this.votPerDui = votPerDui;
	}

	/**
	 * @return the votFechaVence
	 */
	public Date getVotFechaVence() {
		return votFechaVence;
	}

	/**
	 * @param votFechaVence the votFechaVence to set
	 */
	public void setVotFechaVence(Date votFechaVence) {
		this.votFechaVence = votFechaVence;
	}

	/**
	 * @return the votFechaExp
	 */
	public Date getVotFechaExp() {
		return votFechaExp;
	}

	/**
	 * @param votFechaExp the votFechaExp to set
	 */
	public void setVotFechaExp(Date votFechaExp) {
		this.votFechaExp = votFechaExp;
	}

	@Override
	public String toString() {
		return "Votante [votPerDui=" + votPerDui + ", votFechaVence=" + votFechaVence + ", votFechaExp=" + votFechaExp + "]";
	}
}
