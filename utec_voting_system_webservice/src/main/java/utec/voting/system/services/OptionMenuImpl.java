package utec.voting.system.services;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import utec.voting.system.entities.OptionMenu;
import utec.voting.system.jdbc.Conexion;

public class OptionMenuImpl extends Conexion implements Service<OptionMenu>, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Variable de logueo para errores.
	 */
	static final Logger logger = Logger.getLogger(OptionMenuImpl.class);
	
	@Override
	public ArrayList<OptionMenu> getAll() throws SQLException {
		return null;
	}
	
	public ArrayList<OptionMenu> getAll(Integer dui) throws SQLException {
		OptionMenu g;
		CallableStatement stmt = null;
		ArrayList<OptionMenu> l1 = new ArrayList<>();
		try {
			String query = "{CALL SP_READ_OPTION_MENU(?)}";
			stmt = getConnection().prepareCall(query);
			stmt.setInt(1, dui);
			setRs(stmt.executeQuery());
			if(getRs().next()) {
				getRs().beforeFirst();
				while (getRs().next()) {
					g = new OptionMenu(getRs().getInt(1), getRs().getString(2),getRs().getString(3),getRs().getString(4));
					l1.add(g);
				}
			}
			stmt.close();
		} catch (Exception e) {
			logger.error("Error: " + e);
		}finally {
			stmt.close();
		}
		return l1;
	}

	@Override
	public Boolean save(OptionMenu t) throws SQLException {
		CallableStatement stmt = null;
		try {
			String query = "{CALL SP_CREATE_OPTIONMENU(?,?,?,?)}";
			stmt = getConnection().prepareCall(query);
			stmt.setString(1, t.getOptNombre());
			stmt.setString(2, t.getOptIcono());
			stmt.setString(3, t.getOptURL());
			stmt.registerOutParameter(4, Types.INTEGER);
			stmt.execute();
			if (stmt.getInt(4) > 0) {
				return Boolean.TRUE;
			}
		} catch (Exception e) {
			logger.error("Error" + e);
		}
		return Boolean.FALSE;
	}

	@Override
	public Boolean update(OptionMenu t) throws SQLException {
		CallableStatement stmt = null;
		try {
			String query = "{CALL SP_UPDATE_OPTIOMENU(?,?,?,?,?)}";
			stmt = getConnection().prepareCall(query);
			stmt.setInt(1, t.getOptId());
			stmt.setString(2, t.getOptNombre());
			stmt.setString(3, t.getOptURL());
			stmt.setString(4, t.getOptIcono());
			stmt.registerOutParameter(5, Types.INTEGER);
			stmt.execute();
			if (stmt.getInt(5) >= 1) {
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
	public Boolean delete(OptionMenu t) throws SQLException {
		 
		return null;
	}

	@Override
	public OptionMenu finById(Integer id) throws SQLException {
		CallableStatement stmt = null;
		OptionMenu g =  null;
		try {
			String query = "{CALL SP_READ_OPTION_MENU(?)}";
			stmt = getConnection().prepareCall(query);
			stmt.setInt(1, id);
			setRs(stmt.executeQuery());
			if (getRs().next()) {
				getRs().beforeFirst();
				while (getRs().next()) {
					g = new OptionMenu(getRs().getInt(1), getRs().getString(2),getRs().getString(3),getRs().getString(4));
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
	public OptionMenu finById(String id) throws SQLException {
		 
		return null;
	}

}
