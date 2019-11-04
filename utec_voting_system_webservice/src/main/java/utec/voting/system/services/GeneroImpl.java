package utec.voting.system.services;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import utec.voting.system.entities.Genero;
import utec.voting.system.jdbc.Conexion;

/**
 * @author Kevin Orellana
 * @version 1.0 Date: September 2019
 */

public class GeneroImpl extends Conexion implements Service<Genero>, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Variable de logueo para errores.
	 */
	static final Logger logger = Logger.getLogger(GeneroImpl.class);
	
	@Override
	public ArrayList<Genero> getAll() throws SQLException {
		Genero g;
		ArrayList<Genero> l1 = new ArrayList<>();
		CallableStatement stmt = null;
		try {
			String query = "{CALL SP_READ_GENEROS()}";
			stmt = getConnection().prepareCall(query);
			setRs(stmt.executeQuery());
			if(getRs().next()) {
				getRs().beforeFirst();
				while (getRs().next()) {
					g = new Genero(getRs().getInt(1), getRs().getString(2));
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
	public Genero save(Genero t) throws SQLException {
		CallableStatement stmt = null;
		try {
			String query = "{CALL SP_CREATE_GENERO(?,?)}";
			stmt = getConnection().prepareCall(query);
			stmt.setString(1, t.getGenGenero());
			stmt.registerOutParameter(2, Types.INTEGER);
			stmt.execute();
			if (stmt.getInt(2) > 0) {
				t.setGenId(stmt.getInt(2));
			}
		} catch (Exception e) {
			logger.error("Error" + e);
		}finally {
			stmt.close();			
		}
		return t;
	}

	@Override
	public Genero update(Genero t) throws SQLException {
		CallableStatement stmt = null;
		try {
			String query = "{CALL SP_UPDATE_GENERO(?,?,?)}";
			stmt = getConnection().prepareCall(query);
			stmt.setString(1, t.getGenGenero());
			stmt.setInt(2, t.getGenId());
			stmt.registerOutParameter(3, Types.INTEGER);
			stmt.execute();
			if (stmt.getInt(3) >= 1) {
				logger.error("Actualizadoooo.............");
			}
		} catch (Exception e) {
			logger.error("Error" + e);
		}finally {
			stmt.close();			
		}
		return t;
	}

	@Override
	public Boolean delete(Genero t) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Genero finById(Integer id) throws SQLException {
		CallableStatement stmt = null;
		Genero g =  null;
		try {
			String query = "{CALL SP_READ_ONE_GEN(?)}";
			stmt = getConnection().prepareCall(query);
			stmt.setInt(1, id);
			setRs(stmt.executeQuery());
			if (getRs().next()) {
				getRs().beforeFirst();
				while (getRs().next()) {
					g = new Genero(getRs().getInt(1), getRs().getString(2));
				}
			}
		} catch (Exception e) {
			logger.error("Error" + e);
		}finally {
			stmt.close();
		}
		return g;
	}

	@Override
	public Genero finById(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
