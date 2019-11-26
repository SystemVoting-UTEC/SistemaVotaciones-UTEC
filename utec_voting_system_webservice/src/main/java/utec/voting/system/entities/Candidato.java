package utec.voting.system.entities;

/**
 * @author Kevin Orellana
 * @version 1.0 Date: September 2019
 */
public class Candidato {
	private Integer canId;
	private Persona canPerDui;
	private Partido canParId;
	private Departamento canDepId;
	private TipoCandidato canTcaId;
	private Municipio canMunId;
	private Integer canEstado;
	
	/**
	 * 
	 */
	public Candidato() {
	}

	/**
	 * @param canId
	 * @param canPerDui
	 * @param canParId
	 * @param canDepId
	 * @param canTcaId
	 * @param canMunId
	 * @param canEstado
	 */
	public Candidato(Integer canId, Persona canPerDui, Partido canParId, Departamento canDepId, TipoCandidato canTcaId,
			Municipio canMunId, Integer canEstado) {
		super();
		this.canId = canId;
		this.canPerDui = canPerDui;
		this.canParId = canParId;
		this.canDepId = canDepId;
		this.canTcaId = canTcaId;
		this.canMunId = canMunId;
		this.canEstado = canEstado;
	}



	/**
	 * @return the canId
	 */
	public Integer getCanId() {
		return canId;
	}

	/**
	 * @param canId the canId to set
	 */
	public void setCanId(Integer canId) {
		this.canId = canId;
	}

	/**
	 * @return the canPerDui
	 */
	public Persona getCanPerDui() {
		return canPerDui;
	}

	/**
	 * @param canPerDui the canPerDui to set
	 */
	public void setCanPerDui(Persona canPerDui) {
		this.canPerDui = canPerDui;
	}

	/**
	 * @return the canParId
	 */
	public Partido getCanParId() {
		return canParId;
	}

	/**
	 * @param canParId the canParId to set
	 */
	public void setCanParId(Partido canParId) {
		this.canParId = canParId;
	}

	/**
	 * @return the canDepId
	 */
	public Departamento getCanDepId() {
		return canDepId;
	}

	/**
	 * @param canDepId the canDepId to set
	 */
	public void setCanDepId(Departamento canDepId) {
		this.canDepId = canDepId;
	}

	/**
	 * @return the canTcaId
	 */
	public TipoCandidato getCanTcaId() {
		return canTcaId;
	}

	/**
	 * @param canTcaId the canTcaId to set
	 */
	public void setCanTcaId(TipoCandidato canTcaId) {
		this.canTcaId = canTcaId;
	}

	/**
	 * @return the canMunId
	 */
	public Municipio getCanMunId() {
		return canMunId;
	}

	/**
	 * @param canMunId the canMunId to set
	 */
	public void setCanMunId(Municipio canMunId) {
		this.canMunId = canMunId;
	}

	/**
	 * @return the canEstado
	 */
	public Integer getCanEstado() {
		return canEstado;
	}

	/**
	 * @param canEstado the canEstado to set
	 */
	public void setCanEstado(Integer canEstado) {
		this.canEstado = canEstado;
	}

	@Override
	public String toString() {
		return "Candidato [canId=" + canId + ", canPerDui=" + canPerDui + ", canParId=" + canParId + ", canDepId="
				+ canDepId + ", canTcaId=" + canTcaId + ", canMunId=" + canMunId + ", canEstado=" + canEstado + "]";
	}
}