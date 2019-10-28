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
	private Municipio canMunId;
	private TipoCandidato canTcaId;
	
	public Candidato() {
	}
	
	/**
	 * @param canId
	 * @param canPerDui
	 * @param canParId
	 * @param canDepId
	 * @param canMunId
	 * @param canTcaId
	 */
	public Candidato(Integer canId, Persona canPerDui, Partido canParId, Departamento canDepId, Municipio canMunId,
			TipoCandidato canTcaId) {
		super();
		this.canId = canId;
		this.canPerDui = canPerDui;
		this.canParId = canParId;
		this.canDepId = canDepId;
		this.canMunId = canMunId;
		this.canTcaId = canTcaId;
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

	@Override
	public String toString() {
		return "Candidato [canId=" + canId + ", canPerDui=" + canPerDui + ", canParId=" + canParId + ", canDepId="+ canDepId + ", canMunId=" + canMunId + ", canTcaId=" + canTcaId + "]";
	}
}
