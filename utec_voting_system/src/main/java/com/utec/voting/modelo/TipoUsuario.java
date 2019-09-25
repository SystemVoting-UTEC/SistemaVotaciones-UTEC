package com.utec.voting.modelo;

/**
 * @author Kevin Orellana
 * @version 1.0 Date: September 2019
 */
public class TipoUsuario {
	private Integer tusId;
	private String tusTipo;
	
	/**
	 * @param tusId
	 * @param tusTipo
	 */
	public TipoUsuario(Integer tusId, String tusTipo) {
		super();
		this.tusId = tusId;
		this.tusTipo = tusTipo;
	}

	/**
	 * @return the tusId
	 */
	public Integer getTusId() {
		return tusId;
	}

	/**
	 * @param tusId the tusId to set
	 */
	public void setTusId(Integer tusId) {
		this.tusId = tusId;
	}

	/**
	 * @return the tusTipo
	 */
	public String getTusTipo() {
		return tusTipo;
	}

	/**
	 * @param tusTipo the tusTipo to set
	 */
	public void setTusTipo(String tusTipo) {
		this.tusTipo = tusTipo;
	}

	@Override
	public String toString() {
		return "TipoUsuario [tusId=" + tusId + ", tusTipo=" + tusTipo + "]";
	}
}
