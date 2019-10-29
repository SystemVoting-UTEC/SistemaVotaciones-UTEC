/**
 * 
 */
package utec.voting.system.services;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;

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

	@Override
	public ArrayList<Partido> getAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Partido save(Partido t) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Partido update(Partido t) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean delete(Partido t) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Partido finById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Partido finById(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
