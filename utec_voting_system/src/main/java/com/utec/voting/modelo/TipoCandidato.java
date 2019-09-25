package com.utec.voting.modelo;

/**
 * @author Kevin Orellana
 * @version 1.0 Date: September 2019
 */
public class TipoCandidato {
	private Integer tcaId;
	private String tcaTipo;
	
	/**
	 * @param tcaId
	 * @param tcaTipo
	 */
	public TipoCandidato(Integer tcaId, String tcaTipo) {
		super();
		this.tcaId = tcaId;
		this.tcaTipo = tcaTipo;
	}

	/**
	 * @return the tcaId
	 */
	public Integer getTcaId() {
		return tcaId;
	}

	/**
	 * @param tcaId the tcaId to set
	 */
	public void setTcaId(Integer tcaId) {
		this.tcaId = tcaId;
	}

	/**
	 * @return the tcaTipo
	 */
	public String getTcaTipo() {
		return tcaTipo;
	}

	/**
	 * @param tcaTipo the tcaTipo to set
	 */
	public void setTcaTipo(String tcaTipo) {
		this.tcaTipo = tcaTipo;
	}

	@Override
	public String toString() {
		return "TipoCandidato [tcaId=" + tcaId + ", tcaTipo=" + tcaTipo + "]";
	}
}
