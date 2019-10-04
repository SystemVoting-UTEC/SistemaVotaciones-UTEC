package utec.voting.system.ws;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import utec.voting.system.entities.Usuario;
import utec.voting.system.services.Service;

@Path("/usuario")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioService implements Service<Usuario>, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public ArrayList<Usuario> getAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean save(Usuario t) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean update(Usuario t) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean delete(Usuario t) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario finById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario finById(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
