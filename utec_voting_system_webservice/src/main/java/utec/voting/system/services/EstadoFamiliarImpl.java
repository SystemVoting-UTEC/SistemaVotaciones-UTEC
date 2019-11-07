package utec.voting.system.services;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import utec.voting.system.entities.EstadoFamiliar;
import utec.voting.system.jdbc.Conexion;

public class EstadoFamiliarImpl extends Conexion implements Service<EstadoFamiliar>, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Variable de logueo para errores.
	 */
	static final Logger logger = Logger.getLogger(EstadoFamiliarImpl.class);

	@Override
	public ArrayList<EstadoFamiliar> getAll() throws SQLException {
		EstadoFamiliar g;
		ArrayList<EstadoFamiliar> l1 = new ArrayList<>();
		CallableStatement stmt = null;
		try {
			String query = "{CALL SP_READ_ALL_ESTADO_FAMILIAR()}";
			stmt = getConnection().prepareCall(query);
			setRs(stmt.executeQuery());
			if(getRs().next()) {
				getRs().beforeFirst();
				while (getRs().next()) {
					g = new EstadoFamiliar(getRs().getInt(1), getRs().getString(2));
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
	public EstadoFamiliar save(EstadoFamiliar t) throws SQLException {
		CallableStatement stmt = null;
		try {
			String query = "{CALL SP_CREATE_ESTADO_FAMILIAR(?,?)}";
			stmt = getConnection().prepareCall(query);
			stmt.setString(1, t.getEstEstado());
			stmt.registerOutParameter(2, Types.INTEGER);
			stmt.execute();
			if (stmt.getInt(2) > 0) {
				t.setEstId(stmt.getInt(2));
			}
		} catch (Exception e) {
			logger.error("Error" + e);
		}finally {
			stmt.close();			
		}
		return t;
	}

	@Override
	public Boolean update(EstadoFamiliar t) throws SQLException {
		CallableStatement stmt = null;
		try {
			String query = "{CALL SP_UPDATE_GENERO(?,?,?)}";
			stmt = getConnection().prepareCall(query);
			stmt.setString(1, t.getEstEstado());
			stmt.setInt(2, t.getEstId());
			stmt.registerOutParameter(3, Types.INTEGER);
			stmt.execute();
			if (stmt.getInt(3) >= 1) {
				return Boolean.TRUE;
			}
		} catch (Exception e) {
			logger.error("Error" + e);
		}finally {
			stmt.close();			
		}
		return Boolean.FALSE;
	}

	@Override
	public Boolean delete(EstadoFamiliar t) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EstadoFamiliar finById(Integer id) throws SQLException {
		EstadoFamiliar g =  null;
		CallableStatement stmt = null;
		try {
			String query = "{CALL SP_READ_ONE_ESTADOF(?)}";
			stmt = getConnection().prepareCall(query);
			stmt.setInt(1, id);
			setRs(stmt.executeQuery());
			if (getRs().next()) {
				getRs().beforeFirst();
				while (getRs().next()) {
					g = new EstadoFamiliar(getRs().getInt(1), getRs().getString(2));
				}
			}
		} catch (Exception e) {
			logger.error("Error: ",e);
		}finally {
			stmt.close();
		}
		return g;
	}

	@Override
	public EstadoFamiliar finById(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
