/**
 * 
 */
package utec.voting.system.services;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Types;

import org.apache.log4j.Logger;

import utec.voting.system.entities.Eleccion;
import utec.voting.system.entities.Persona;
import utec.voting.system.entities.Sufragio;
import utec.voting.system.entities.TipoUsuario;
import utec.voting.system.entities.Usuario;
import utec.voting.system.jdbc.Conexion;

/**
 * @author Kevin Orellana
 *
 */
public class SufragioImpl extends Conexion implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Variable de logueo para errores.
	 */
	static final Logger logger = Logger.getLogger(SufragioImpl.class);
	
	private CandidatoImpl CandidatoService =  new CandidatoImpl();
	private PersonaImpl PersonaService =  new PersonaImpl();
	
	
	public Boolean insertSufragio(Sufragio suf) {
		try {
			String query = "{CALL SP_CREATE_SUFRAGIO(?, ?, ?, ?,?)}";
			CallableStatement stmt = getConnection().prepareCall(query);
			stmt.setString(1, suf.getSufPerDui().getPerDui());
			stmt.setInt(2, suf.getSufCanId().getCanId());
			stmt.setDouble(3, suf.getSufSufragio());
			stmt.setInt(4, suf.getElcId().getElcId());
			stmt.registerOutParameter(5, Types.INTEGER);
			stmt.execute();
			if (stmt.getInt(5) >= 1) {
				return Boolean.TRUE;
			}
		} catch (Exception e) {
			logger.error("Error" + e);
		}
		return Boolean.FALSE;
	}
	
	public Boolean sufragioEnabled(String us, Integer elc) {
		CallableStatement stmt = null;
		try {
			String query = "{CALL SP_SUFRAGIO_ENABLE(?,?)}";
			stmt = getConnection().prepareCall(query);
			stmt.setString(1, us);
			stmt.setInt(2, elc);
			setRs(stmt.executeQuery());
			if(getRs().next()) {
				return Boolean.FALSE;
			}else {
				return Boolean.TRUE;
			}
		} catch (Exception e) {
			logger.error("Al sufragioEnabled: ",e);
		}
		return Boolean.FALSE;
	}
}
