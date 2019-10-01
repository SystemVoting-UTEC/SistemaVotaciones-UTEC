package utec.voting.system.entities;

public class OptionMenu {
	private Integer optId;
	private String optNombre;
	private String optURL;
	
	/**
	 * @param optId
	 * @param optNombre
	 * @param optURL
	 */
	public OptionMenu(Integer optId, String optNombre, String optURL) {
		this.optId = optId;
		this.optNombre = optNombre;
		this.optURL = optURL;
	}
	
	public OptionMenu() {
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
	
	@Override
	public String toString() {
		return "OptionMenu [optId=" + optId + ", optNombre=" + optNombre + ", optURL=" + optURL + "]";
	}
}
