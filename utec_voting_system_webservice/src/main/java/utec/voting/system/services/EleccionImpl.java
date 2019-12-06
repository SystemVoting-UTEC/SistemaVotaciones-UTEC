/**
 * 
 */
package utec.voting.system.services;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import utec.voting.system.entities.Eleccion;
import utec.voting.system.jdbc.Conexion;

/**
 * @author Kevin Orellana
 *
 */
public class EleccionImpl extends Conexion implements Service<Eleccion>, Serializable{
	
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
					elec = new Eleccion(getRs().getInt(1), getRs().getString(2), getRs().getString(3), getRs().getString(4),getRs().getInt(5));
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
					elec = new Eleccion(getRs().getInt(1), getRs().getString(2), getRs().getString(3), getRs().getString(4),getRs().getInt(5));
				}
			}
		} catch (Exception e) {
			logger.error("Error al consultar selección: ",e);
		}
		return elec;
	}

	@Override
	public ArrayList<Eleccion> getAll() throws SQLException {
		Eleccion g;
		ArrayList<Eleccion> l1 = new ArrayList<>();
		CallableStatement stmt = null;
		try {
			String query = "{CALL SP_READ_ALL_ELECCION()}";
			stmt = getConnection().prepareCall(query);
			setRs(stmt.executeQuery());
			if(getRs().next()) {
				getRs().beforeFirst();
				while (getRs().next()) {
					g = new Eleccion(getRs().getInt(1), getRs().getString(2),getRs().getString(3),getRs().getString(4),getRs().getInt(5));
					l1.add(g);
				}
			}
		} catch (Exception e) {
			logger.error("Error: " + e);
		}finally {
			stmt.close();			
		}
		return l1;
	}

	@Override
	public Boolean save(Eleccion t) throws SQLException {
		CallableStatement stmt = null;
		try {
			Timestamp ini = Timestamp.valueOf(t.getElcFechaInicio()+" 08:00:00");
			Timestamp fin = Timestamp.valueOf(t.getElcFechaFin()+" 18:00:00");
			String query = "{CALL SP_CREATE_ELECCION(?,?,?,?,?)}";
			stmt = getConnection().prepareCall(query);
			stmt.setString(1, t.getElcDescripcion());
			stmt.setTimestamp(2, ini);
			stmt.setTimestamp(3, fin);
			stmt.setInt(4, t.getElcEstado());
			stmt.registerOutParameter(5, Types.INTEGER);
			stmt.execute();
			if (stmt.getInt(5) > 0) {
				return Boolean.TRUE;
			}
		} catch (Exception e) {
			logger.error("Error" + e);
		}
		return Boolean.FALSE;
	}

	@Override
	public Boolean update(Eleccion t) throws SQLException {
		CallableStatement stmt = null;
		try {
			Timestamp ini = Timestamp.valueOf(t.getElcFechaInicio()+" 08:00:00");
			Timestamp fin = Timestamp.valueOf(t.getElcFechaFin()+" 18:00:00");
			String query = "{CALL SP_UPDATE_ELECCION(?,?,?,?,?,?)}";
			stmt = getConnection().prepareCall(query);
			stmt.setInt(1, t.getElcId());
			stmt.setTimestamp(2, ini);
			stmt.setTimestamp(3, fin);
			stmt.setString(4, t.getElcFechaFin());
			stmt.setInt(5, t.getElcEstado());
			stmt.registerOutParameter(6, Types.INTEGER);
			stmt.execute();
			if (stmt.getInt(6) > 0) {
				return Boolean.TRUE;
			}
		} catch (Exception e) {
			logger.error("Error" + e);
		}
		return Boolean.FALSE;
	}

	@Override
	public Boolean delete(Eleccion t) throws SQLException {
		return null;
	}

	@Override
	public Eleccion finById(Integer id) throws SQLException {
		return null;
	}

	@Override
	public Eleccion finById(String id) throws SQLException {
		return null;
	}
}
