package utec.voting.system.services;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import utec.voting.system.entities.TipoUsuario;
import utec.voting.system.jdbc.Conexion;

public class TipoUsuarioImpl extends Conexion implements Service<TipoUsuario>, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Variable de logueo para errores.
	 */
	static final Logger logger = Logger.getLogger(TipoUsuarioImpl.class);
	
	@Override
	public ArrayList<TipoUsuario> getAll() throws SQLException {
		TipoUsuario g;
		ArrayList<TipoUsuario> l1 = new ArrayList<>();
		try {
			String query = "{CALL SP_READ_TIPOUSUARIO()}";
			CallableStatement stmt = getConnection().prepareCall(query);
			setRs(stmt.executeQuery());
			if(getRs().next()) {
				getRs().beforeFirst();
				while (getRs().next()) {
					g = new TipoUsuario(getRs().getInt(1), getRs().getString(2));
					l1.add(g);
				}
			}
			stmt.close();
		} catch (Exception e) {
			logger.error("Error: " + e);
		}
		return l1;
	
		// TODO Auto-generated method stub
	
	}

	@Override
	public Boolean save(TipoUsuario t) throws SQLException {
		CallableStatement stmt=null;
		try {
			//falta sp
			String query = "{CALL SP_CREATE_TIPOUSUARIO(?,?)}";
			 stmt = getConnection().prepareCall(query);
			stmt.setString(1, t.getTusTipo());
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
	public Boolean update(TipoUsuario t) throws SQLException {
		try {
			//falta sp
			String query = "{CALL SP_UPDATE_TIPOUSUARIO(?,?,?)}";
			CallableStatement stmt = getConnection().prepareCall(query);
			stmt.setString(1, t.getTusTipo());
			stmt.setInt(2, t.getTusId());
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
	public Boolean delete(TipoUsuario t) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TipoUsuario finById(Integer id) throws SQLException {
		TipoUsuario g =  null;
		CallableStatement stmt = null;
		try {
			String query = "{CALL SP_READ_ONE_TPUSU(?)}";
			stmt = getConnection().prepareCall(query);
			stmt.setInt(1, id);
			setRs(stmt.executeQuery());
			if (getRs().next()) {
				getRs().beforeFirst();
				while (getRs().next()) {
					g = new TipoUsuario(getRs().getInt(1), getRs().getString(2));
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
	public TipoUsuario finById(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
