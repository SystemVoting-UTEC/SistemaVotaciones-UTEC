package utec.voting.system.services;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import com.utec.voting.jdbc.Conexion;

import utec.voting.system.entities.Genero;
import utec.voting.system.entities.Persona;

public class GeneroImpl extends Conexion implements Service<Genero>, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public ArrayList<Genero> getAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean save(Genero t) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean update(Genero t) throws SQLException {
		// TODO Auto-generated method stub
		return null;
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
