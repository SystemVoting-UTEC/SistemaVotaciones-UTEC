package com.utec.voting.modelo;

/**
 * @author Kevin Orellana
 * @version 1.0 Date: September 2019
 */
public class Votante {
	private Persona votPerDui;
	private String votFechaVence;
	private String votFechaExp;
	private int estado;
	
	
	/**
	 * 
	 */
	public Votante() {
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
	public String getVotFechaVence() {
		return votFechaVence;
	}


	/**
	 * @param votFechaVence the votFechaVence to set
	 */
	public void setVotFechaVence(String votFechaVence) {
		this.votFechaVence = votFechaVence;
	}


	/**
	 * @return the votFechaExp
	 */
	public String getVotFechaExp() {
		return votFechaExp;
	}


	/**
	 * @param votFechaExp the votFechaExp to set
	 */
	public void setVotFechaExp(String votFechaExp) {
		this.votFechaExp = votFechaExp;
	}


	/**
	 * @return the estado
	 */
	public int getEstado() {
		return estado;
	}


	/**
	 * @param estado the estado to set
	 */
	public void setEstado(int estado) {
		this.estado = estado;
	}


	@Override
	public String toString() {
		return "Votante [votPerDui=" + votPerDui + ", votFechaVence=" + votFechaVence + ", votFechaExp=" + votFechaExp
				+ ", estado=" + estado + "]";
	}
}
