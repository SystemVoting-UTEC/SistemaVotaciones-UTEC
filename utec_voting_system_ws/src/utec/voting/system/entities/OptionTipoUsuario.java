package utec.voting.system.entities;

public class OptionTipoUsuario {
	private OptionMenu optId;
	private TipoUsuario tusId;
	
	public OptionTipoUsuario() {
	}
	
	/**
	 * @param optId
	 * @param tusId
	 */
	public OptionTipoUsuario(OptionMenu optId, TipoUsuario tusId) {
		super();
		this.optId = optId;
		this.tusId = tusId;
	}
	
	/**
	 * @return the optId
	 */
	public OptionMenu getOptId() {
		return optId;
	}
	/**
	 * @param optId the optId to set
	 */
	public void setOptId(OptionMenu optId) {
		this.optId = optId;
	}
	/**
	 * @return the tusId
	 */
	public TipoUsuario getTusId() {
		return tusId;
	}
	/**
	 * @param tusId the tusId to set
	 */
	public void setTusId(TipoUsuario tusId) {
		this.tusId = tusId;
	}
	
	@Override
	public String toString() {
		return "OptionTipoUsuario [optId=" + optId + ", tusId=" + tusId + "]";
	}
}
