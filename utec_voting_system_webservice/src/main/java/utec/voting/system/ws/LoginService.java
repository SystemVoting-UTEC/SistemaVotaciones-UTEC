package utec.voting.system.ws;

import java.io.Serializable;
import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import utec.voting.system.entities.Usuario;
import utec.voting.system.services.UsuarioImpl;

/**
 * @author Kevin Orellana
 * @version 1.0 Date: September 2019
 */

@Path("/login")
@Produces(MediaType.APPLICATION_JSON) 
@Consumes(MediaType.APPLICATION_JSON)
public class LoginService implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Variable de logueo para errores.
	 */
	static final Logger logger = Logger.getLogger(LoginService.class);
	
	private UsuarioImpl usuarioImpl = new UsuarioImpl();
	
	@POST
	public Response finByCredential(Usuario us) throws SQLException {
		Usuario obj = null;
		JSONObject jsonObject = null;
		try {
			obj =  new Usuario();
			obj = usuarioImpl.loginCredential(us);
			jsonObject = new JSONObject(obj);
		} catch (Exception e) {
			logger.error("Error: ",e);
			return Response.status(500).build();
		}
		return Response.ok(jsonObject.toString(),MediaType.APPLICATION_JSON).build();
	}

}
