package com.utec.voting.modelo;

/**
 * @author Kevin Orellana
 * @version 1.0 Date: September 2019
 */
public class EstadoFamiliar {
	private Integer estId;
	private String estEstado;
	
	/**
	 * @param estId
	 * @param estEstado
	 */
	public EstadoFamiliar(Integer estId, String estEstado) {
		super();
		this.estId = estId;
		this.estEstado = estEstado;
	}

	/**
	 * @return the estId
	 */
	public Integer getEstId() {
		return estId;
	}

	/**
	 * @param estId the estId to set
	 */
	public void setEstId(Integer estId) {
		this.estId = estId;
	}

	/**
	 * @return the estEstado
	 */
	public String getEstEstado() {
		return estEstado;
	}

	/**
	 * @param estEstado the estEstado to set
	 */
	public void setEstEstado(String estEstado) {
		this.estEstado = estEstado;
	}

	@Override
	public String toString() {
		return "EstadoFamiliar [estId=" + estId + ", estEstado=" + estEstado + "]";
	}
}
