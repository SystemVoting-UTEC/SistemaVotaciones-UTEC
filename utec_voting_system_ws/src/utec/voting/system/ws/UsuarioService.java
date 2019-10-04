package utec.voting.system.ws;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.utec.voting.jdbc.Conexion;

import utec.voting.system.entities.Persona;
import utec.voting.system.entities.TipoUsuario;
import utec.voting.system.entities.Usuario;
import utec.voting.system.services.Service;

@Path("/usuario")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioService extends Conexion implements Service<Usuario>, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String TABLE = "USUARIO";

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
	@GET
	@Path("/findId/{id}")
	public Usuario finById(@PathParam("id") String id) throws SQLException {
		Usuario obj = null;
		try {
			setPs(consPrepare(SELECT + TABLE + WHERE + "US_PER_DUI = ?"));
			getPs().setString(1, id);
			setRs(getPs().executeQuery());
			if(getRs().next()) {
				getRs().beforeFirst();
				while (getRs().next()) {
					System.out.println("Valor "+getRs().getString(1));
				}	
			}
		} catch (Exception e) {
			return obj;
		} finally {
			getPs().close();
		}
		return obj;
	}

}
