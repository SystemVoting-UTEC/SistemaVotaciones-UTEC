package utec.voting.system.services;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.utec.voting.jdbc.Conexion;

import utec.voting.system.entities.Genero;

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
		try {
			String query = "{CALL SP_READ_GENEROS()}";
			CallableStatement stmt = getConnection().prepareCall(query);
			setRs(stmt.executeQuery());
			if(getRs().next()) {
				getRs().beforeFirst();
				while (getRs().next()) {
					g = new Genero(getRs().getInt(1), getRs().getString(2));
					logger.error("Aquí: " + g);
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
	public Genero save(Genero t) throws SQLException {
		Genero bandera = null;
		try {
			String query = "{CALL SP_CREATE_GENERO(?,?)}";
			CallableStatement stmt = getConnection().prepareCall(query);
			stmt.setString(1, t.getGenGenero());
			stmt.registerOutParameter(2, Types.INTEGER);
			stmt.execute();
			if (stmt.getInt(2) == 1) {
				bandera = new Genero();
				bandera.setGenId(stmt.getInt(2));
			}
		} catch (Exception e) {
			logger.error("Error" + e);
		} finally {
			getPs().close();
		}
		return bandera;
	}

	@Override
	public Genero update(Genero t) throws SQLException {
		Genero bandera = null;
		try {
			String query = "{CALL SP_UPDATE_GENERO(?,?)}";
			CallableStatement stmt = getConnection().prepareCall(query);
			stmt.setInt(1, t.getGenId());
			stmt.registerOutParameter(2, Types.INTEGER);
			stmt.execute();
			if (stmt.getInt(2) == 1) {
				bandera = new Genero();
				bandera.setGenId(stmt.getInt(2));
			}
		} catch (Exception e) {
			logger.error("Error" + e);
		} finally {
			getPs().close();
		}
		return bandera;
	}

	@Override
	public Boolean delete(Genero t) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Genero finById(Integer id) throws SQLException {
		Genero g =  null;
		try {
			String query = "{CALL SP_READ_ONE_GEN(?)}";
			CallableStatement stmt = getConnection().prepareCall(query);
			stmt.setInt(1, id);
			setRs(stmt.executeQuery());
			if (getRs().next()) {
				getRs().beforeFirst();
				while (getRs().next()) {
					g = new Genero(getRs().getInt(1), getRs().getString(2));
				}
			}
		} catch (Exception e) {
			System.out.println("Error: "+e);
		}
		return g;
	}

	@Override
	public Genero finById(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
