package utec.voting.system.services;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import com.utec.voting.jdbc.Conexion;

import utec.voting.system.entities.EstadoFamiliar;
import utec.voting.system.entities.Genero;

public class EstadoFamiliarImpl extends Conexion implements Service<EstadoFamiliar>, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public ArrayList<EstadoFamiliar> getAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean save(EstadoFamiliar t) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean update(EstadoFamiliar t) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean delete(EstadoFamiliar t) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EstadoFamiliar finById(Integer id) throws SQLException {
		EstadoFamiliar g =  null;
		try {
			String query = "{CALL SP_READ_ONE_ESTADOF(?)}";
			CallableStatement stmt = getConnection().prepareCall(query);
			stmt.setInt(1, id);
			setRs(stmt.executeQuery());
			if (getRs().next()) {
				getRs().beforeFirst();
				while (getRs().next()) {
					g = new EstadoFamiliar(getRs().getInt(1), getRs().getString(2));
				}
			}
		} catch (Exception e) {
			System.out.println("Error: "+e);
		}
		return g;
	}

	@Override
	public EstadoFamiliar finById(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
