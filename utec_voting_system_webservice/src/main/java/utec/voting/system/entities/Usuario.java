package utec.voting.system.entities;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Kevin Orellana
 * @version 1.0 Date: September 2019
 */
@XmlRootElement
public class Usuario {
	private Persona usPerDui; //string
	private String usPassword;//string
	private TipoUsuario usTusId;//int
	
	public Usuario() {
		
	}
	
	/**
	 * @param usPerDui
	 * @param usPassword
	 * @param usTusId
	 */
	public Usuario(Persona usPerDui, String usPassword, TipoUsuario usTusId) {
		super();
		this.usPerDui = usPerDui;
		this.usPassword = usPassword;
		this.usTusId = usTusId;
	}

	/**
	 * @return the usPerDui
	 */
	public Persona getUsPerDui() {
		return usPerDui;
	}

	/**
	 * @param usPerDui the usPerDui to set
	 */
	public void setUsPerDui(Persona usPerDui) {
		this.usPerDui = usPerDui;
	}

	/**
	 * @return the usPassword
	 */
	public String getUsPassword() {
		return usPassword;
	}

	/**
	 * @param usPassword the usPassword to set
	 */
	public void setUsPassword(String usPassword) {
		this.usPassword = usPassword;
	}

	/**
	 * @return the usTusId
	 */
	public TipoUsuario getUsTusId() {
		return usTusId;
	}

	/**
	 * @param usTusId the usTusId to set
	 */
	public void setUsTusId(TipoUsuario usTusId) {
		this.usTusId = usTusId;
	}

	@Override
	public String toString() {
		return "Usuario [usPerDui=" + usPerDui + ", usPassword=" + usPassword + ", usTusId=" + usTusId + "]";
	}
}
