/**
 * 
 */
package utec.voting.system.services;

import java.io.Serializable;
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
		// TODO Auto-generated method stub
		return null;
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
