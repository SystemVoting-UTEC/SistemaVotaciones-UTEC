package utec.voting.system.services;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.SQLException;
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
		// TODO Auto-generated method stub
		return null;
	}
	
	public ArrayList<OptionMenu> getAll(Integer dui) throws SQLException {
		OptionMenu g;
		ArrayList<OptionMenu> l1 = new ArrayList<>();
		try {
			String query = "{CALL SP_READ_OPTION_MENU(?)}";
			CallableStatement stmt = getConnection().prepareCall(query);
			stmt.setInt(1, dui);
			setRs(stmt.executeQuery());
			if(getRs().next()) {
				getRs().beforeFirst();
				while (getRs().next()) {
					g = new OptionMenu(getRs().getInt(1), getRs().getString(2),getRs().getString(3));
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
	public OptionMenu save(OptionMenu t) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OptionMenu update(OptionMenu t) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean delete(OptionMenu t) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OptionMenu finById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OptionMenu finById(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
