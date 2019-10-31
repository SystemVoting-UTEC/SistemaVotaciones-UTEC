package utec.voting.system.services;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.SQLException;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TipoUsuario save(TipoUsuario t) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TipoUsuario update(TipoUsuario t) throws SQLException {
		// TODO Auto-generated method stub
		return null;
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
