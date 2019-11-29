/**
 * 
 */
package utec.voting.system.services;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import org.apache.log4j.Logger;


import utec.voting.system.entities.TipoCandidato;
import utec.voting.system.jdbc.Conexion;

/**
 * @author Manuel Cardona
 *
 */
public class TipoCandidatoImpl extends Conexion implements Service<TipoCandidato>, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Variable de logueo para errores.
	 */
	static final Logger logger = Logger.getLogger(TipoCandidatoImpl.class);

	@Override
	public ArrayList<TipoCandidato> getAll() throws SQLException {
		TipoCandidato g;
		ArrayList<TipoCandidato> l1 = new ArrayList<>();
		try {
			String query = "{CALL SP_READ_TIPOCANDIDATOS()}";
			CallableStatement stmt = getConnection().prepareCall(query);
			setRs(stmt.executeQuery());
			if(getRs().next()) {
				getRs().beforeFirst();
				while (getRs().next()) {
					g = new TipoCandidato(getRs().getInt(1), getRs().getString(2));
					l1.add(g);
				}
			}
			stmt.close();
		} catch (Exception e) {
			logger.error("Error: " + e);
		}
		return l1;
	}

	@Override
	public Boolean save(TipoCandidato t) throws SQLException {
		CallableStatement stmt = null;
		try {
			String query = "{CALL SP_CREATE_TIPOCANDIDATO(?,?)}";
			stmt = getConnection().prepareCall(query);
			stmt.setString(1, t.getTcaTipo());
			stmt.registerOutParameter(2, Types.INTEGER);
			stmt.execute();
			if (stmt.getInt(2) >= 1) {
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
	public Boolean update(TipoCandidato t) throws SQLException {
		
		try {
			String query = "{CALL SP_UPDATE_TIPOCANDIDATO(?,?,?)}";
			CallableStatement stmt = getConnection().prepareCall(query);
			stmt.setString(1, t.getTcaTipo());
			stmt.setInt(2, t.getTcaId());
			stmt.registerOutParameter(3, Types.INTEGER);
			stmt.execute();
			if (stmt.getInt(3) >= 1) {
				return Boolean.TRUE;
			}
		} catch (Exception e) {
			logger.error("Error" + e);
		}
		return Boolean.FALSE;
	}

	@Override
	public Boolean delete(TipoCandidato t) throws SQLException {
		//SP_DELETE_TIPOCANDIDATO
		return null;
	}

	@Override
	public TipoCandidato finById(Integer id) throws SQLException {
		TipoCandidato g =  null;
		try {
			String query = "{CALL SP_READ_ONE_TDC(?)}";
			CallableStatement stmt = getConnection().prepareCall(query);
			stmt.setInt(1, id);
			setRs(stmt.executeQuery());
			if (getRs().next()) {
				getRs().beforeFirst();
				while (getRs().next()) {
					g = new TipoCandidato(getRs().getInt(1), getRs().getString(2));
				}
			}
		} catch (Exception e) {
			logger.error("Error" + e);
		}
		return g;
	}

	@Override
	public TipoCandidato finById(String id) throws SQLException {
		 
		return null;
	}
	
}
