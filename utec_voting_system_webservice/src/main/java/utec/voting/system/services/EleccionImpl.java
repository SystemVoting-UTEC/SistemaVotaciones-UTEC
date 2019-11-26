/**
 * 
 */
package utec.voting.system.services;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Timestamp;

import org.apache.log4j.Logger;

import utec.voting.system.entities.Eleccion;
import utec.voting.system.entities.Genero;
import utec.voting.system.jdbc.Conexion;

/**
 * @author Kevin Orellana
 *
 */
public class EleccionImpl extends Conexion implements Serializable{
	
	/**
	 *  
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Variable de logueo para errores.
	 */
	static final Logger logger = Logger.getLogger(EleccionImpl.class);
	
	public Eleccion eleccionIsActive(String dui) {
		CallableStatement stmt = null;
		Eleccion elec = null;
		try {
			String query = "{CALL SP_READ_ELECCION(?)}";
			stmt = getConnection().prepareCall(query);
			stmt.setString(1, dui);
			setRs(stmt.executeQuery());
			if (getRs().next()) {
				getRs().beforeFirst();
				while (getRs().next()) {
					elec = new Eleccion(getRs().getInt(1), getRs().getString(2), getRs().getTimestamp(3), getRs().getTimestamp(4),getRs().getInt(5));
				}
			}
		} catch (Exception e) {
			logger.error("Error al consultar selección: ",e);
		}
		return elec;
	}
	
	public Eleccion eleccionIsActive() {
		CallableStatement stmt = null;
		Eleccion elec = null;
		try {
			String query = "{CALL SP_READ_ELECCION_ACTIVE()}";
			stmt = getConnection().prepareCall(query);
			setRs(stmt.executeQuery());
			if (getRs().next()) {
				getRs().beforeFirst();
				while (getRs().next()) {
					elec = new Eleccion(getRs().getInt(1), getRs().getString(2), getRs().getTimestamp(3), getRs().getTimestamp(4),getRs().getInt(5));
				}
			}
		} catch (Exception e) {
			logger.error("Error al consultar selección: ",e);
		}
		return elec;
	}
}
