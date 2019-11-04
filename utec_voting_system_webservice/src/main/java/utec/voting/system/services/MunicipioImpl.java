/**
 * 
 */
package utec.voting.system.services;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import utec.voting.system.entities.Municipio;
import utec.voting.system.jdbc.Conexion;

/**
 * @author Manuel Cardona
 *
 */
public class MunicipioImpl extends Conexion implements Service<Municipio>, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public ArrayList<Municipio> getAll() throws SQLException {
		Municipio g;
		ArrayList<Municipio> l1 = new ArrayList<>();
		CallableStatement stmt = null;
		try {
			String query = "{CALL SP_READ_ALL_MUNICIPIO()}";
			stmt = getConnection().prepareCall(query);
			setRs(stmt.executeQuery());
			if(getRs().next()) {
				getRs().beforeFirst();
				while (getRs().next()) {
					g = new Municipio();
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
	public Municipio save(Municipio t) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Municipio update(Municipio t) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean delete(Municipio t) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Municipio finById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Municipio finById(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
