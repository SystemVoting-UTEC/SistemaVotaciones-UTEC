package com.utec.voting.modelo;

public class OptionMenu {
	private Integer optId;
	private String optNombre;
	private String optURL;
	private String optIcono;
	
	public OptionMenu() {
	}
	
	

	/**
	 * @param optId
	 * @param optNombre
	 * @param optURL
	 * @param optIcono
	 */
	public OptionMenu(Integer optId, String optNombre, String optURL, String optIcono) {
		this.optId = optId;
		this.optNombre = optNombre;
		this.optURL = optURL;
		this.optIcono = optIcono;
	}



	/**
	 * @return the optId
	 */
	public Integer getOptId() {
		return optId;
	}

	/**
	 * @param optId the optId to set
	 */
	public void setOptId(Integer optId) {
		this.optId = optId;
	}

	/**
	 * @return the optNombre
	 */
	public String getOptNombre() {
		return optNombre;
	}

	/**
	 * @param optNombre the optNombre to set
	 */
	public void setOptNombre(String optNombre) {
		this.optNombre = optNombre;
	}

	/**
	 * @return the optURL
	 */
	public String getOptURL() {
		return optURL;
	}

	/**
	 * @param optURL the optURL to set
	 */
	public void setOptURL(String optURL) {
		this.optURL = optURL;
	}

	/**
	 * @return the optIcono
	 */
	public String getOptIcono() {
		return optIcono;
	}

	/**
	 * @param optIcono the optIcono to set
	 */
	public void setOptIcono(String optIcono) {
		this.optIcono = optIcono;
	}

	@Override
	public String toString() {
		return "OptionMenu [optId=" + optId + ", optNombre=" + optNombre + ", optURL=" + optURL + ", optIcono="
				+ optIcono + "]";
	}
}
