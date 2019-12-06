package utec.voting.system.entities;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Kevin Orellana
 * @version 1.0 Date: September 2019
 */
public class Usuario {
	private Persona usPerDui; //string
	private String usPassword;//string
	private TipoUsuario usTusId;//int
	private Integer usEstado;
	private String usEmail;
	
	public Usuario() {
		
	}
	
	/**
	 * @param usPerDui
	 * @param usPassword
	 * @param usTusId
	 * @param usEmail
	 * @author kevin_orellana
	 */
	public Usuario(Persona usPerDui, String usPassword, TipoUsuario usTusId, Integer usEstado,String usEmail) {
		super();
		this.usPerDui = usPerDui;
		this.usPassword = usPassword;
		this.usTusId = usTusId;
		this.usEmail = usEmail;
		this.usEstado = usEstado;
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

	public Integer getUsEstado() {
		return usEstado;
	}

	public void setUsEstado(Integer usEstado) {
		this.usEstado = usEstado;
	}

	/**
	 * @return the usEmail
	 */
	public String getUsEmail() {
		return usEmail;
	}

	/**
	 * @param usEmail the usEmail to set
	 */
	public void setUsEmail(String usEmail) {
		this.usEmail = usEmail;
	}

	@Override
	public String toString() {
		return "Usuario [usPerDui=" + usPerDui + ", usPassword=" + usPassword + ", usTusId=" + usTusId + ", usEstado=" + usEstado + ", usEmail=" + usEmail + "]";
	}
}
