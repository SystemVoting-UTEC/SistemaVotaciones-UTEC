/**
 * 
 */
package com.utec.voting.modelo;

import java.sql.Timestamp;

/**
 * @author Kevin Orellana
 *
 */
public class Eleccion {
	private	Integer elcId;
	private String elcDescripcion;
	private	Timestamp elcFechaInicio; 
	private	Timestamp elcFechaFin; 
	private	Integer elcEstado;
	
	public Eleccion() {
	}

	/**
	 * @param elcId
	 * @param elcDescripcion
	 * @param elcFechaInicio
	 * @param elcFechaFin
	 * @param elcEstado
	 */
	public Eleccion(Integer elcId, String elcDescripcion, Timestamp elcFechaInicio, Timestamp elcFechaFin,Integer elcEstado) {
		this.elcId = elcId;
		this.elcDescripcion = elcDescripcion;
		this.elcFechaInicio = elcFechaInicio;
		this.elcFechaFin = elcFechaFin;
		this.elcEstado = elcEstado;
	}

	/**
	 * @return the elcId
	 */
	public Integer getElcId() {
		return elcId;
	}

	/**
	 * @param elcId the elcId to set
	 */
	public void setElcId(Integer elcId) {
		this.elcId = elcId;
	}

	/**
	 * @return the elcDescripcion
	 */
	public String getElcDescripcion() {
		return elcDescripcion;
	}

	/**
	 * @param elcDescripcion the elcDescripcion to set
	 */
	public void setElcDescripcion(String elcDescripcion) {
		this.elcDescripcion = elcDescripcion;
	}

	/**
	 * @return the elcFechaInicio
	 */
	public Timestamp getElcFechaInicio() {
		return elcFechaInicio;
	}

	/**
	 * @param elcFechaInicio the elcFechaInicio to set
	 */
	public void setElcFechaInicio(Timestamp elcFechaInicio) {
		this.elcFechaInicio = elcFechaInicio;
	}

	/**
	 * @return the elcFechaFin
	 */
	public Timestamp getElcFechaFin() {
		return elcFechaFin;
	}

	/**
	 * @param elcFechaFin the elcFechaFin to set
	 */
	public void setElcFechaFin(Timestamp elcFechaFin) {
		this.elcFechaFin = elcFechaFin;
	}

	/**
	 * @return the elcEstado
	 */
	public Integer getElcEstado() {
		return elcEstado;
	}

	/**
	 * @param elcEstado the elcEstado to set
	 */
	public void setElcEstado(Integer elcEstado) {
		this.elcEstado = elcEstado;
	}

	@Override
	public String toString() {
		return "Eleccion [elcId=" + elcId + ", elcDescripcion=" + elcDescripcion + ", elcFechaInicio=" + elcFechaInicio
				+ ", elcFechaFin=" + elcFechaFin + ", elcEstado=" + elcEstado + "]";
	}
	
}
