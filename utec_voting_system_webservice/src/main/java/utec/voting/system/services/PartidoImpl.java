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

import utec.voting.system.entities.Partido;
import utec.voting.system.jdbc.Conexion;

/**
 * @author Manuel Cardona
 *
 */
public class PartidoImpl extends Conexion implements Service<Partido>, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Variable de logueo para errores.
	 */
	static final Logger logger = Logger.getLogger(GeneroImpl.class);

	@Override
	public ArrayList<Partido> getAll() throws SQLException {
		Partido g;
		ArrayList<Partido> l1 = new ArrayList<>();
		CallableStatement stmt = null;
		try {
			String query = "{CALL SP_READ_PARTIDOS()}";
			stmt = getConnection().prepareCall(query);
			setRs(stmt.executeQuery());
			if(getRs().next()) {
				getRs().beforeFirst();
				while (getRs().next()) {
					g = new Partido(getRs().getInt(1), getRs().getString(2),getRs().getInt(3));
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
	public Boolean save(Partido t) throws SQLException {
		CallableStatement stmt = null;
		try {
			String query = "{CALL SP_CREATE_PARTIDO(?,?,?)}";
			stmt = getConnection().prepareCall(query);
			stmt.setString(1, t.getParNombre());
			stmt.setInt(2, t.getEstado());
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
	public Boolean update(Partido t) throws SQLException {
		CallableStatement stmt = null;
		try {
			String query = "{CALL SP_UPDATE_PARTIDO(?,?,?)}";
			stmt = getConnection().prepareCall(query);
			stmt.setString(1, t.getParNombre());
			stmt.setInt(2, t.getParId());
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
	public Boolean delete(Partido t) throws SQLException {
		// TODO Auto-generated method stub
		//SP_DELETE_PARTIDO
		return null;
	}

	@Override
	public Partido finById(Integer id) throws SQLException {
		CallableStatement stmt = null;
		Partido g =  null;
		try {
			String query = "{CALL SP_READ_ONE_PARTIDO(?)}";
			stmt = getConnection().prepareCall(query);
			stmt.setInt(1, id);
			setRs(stmt.executeQuery());
			if (getRs().next()) {
				getRs().beforeFirst();
				while (getRs().next()) {
					g = new Partido(getRs().getInt(1), getRs().getString(2),getRs().getInt(3));
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
	public Partido finById(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
