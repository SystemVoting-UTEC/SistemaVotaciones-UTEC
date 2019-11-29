/**
 * 
 */
package utec.voting.system.ws;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import utec.voting.system.entities.Usuario;
import utec.voting.system.services.UsuarioImpl;


/**
 * @author manuel
 *
 */
@Path("/Usuario")
@Produces(MediaType.APPLICATION_JSON) 
@Consumes(MediaType.APPLICATION_JSON)

public class UsuarioService implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private UsuarioImpl UsuarioService = new UsuarioImpl();
	/**
	 * Variable de logueo para errores.
	 */
	static final Logger logger = Logger.getLogger(UsuarioService.class);
	
	@GET
	public Response findAll() throws SQLException {
		JSONArray jsArray;
		List<Usuario> obj = null;
		try {
			obj =  new ArrayList<Usuario>();
			obj = UsuarioService.getAll();
			jsArray = new JSONArray(obj);
		} catch (Exception e) {
			logger.error("Error: ",e);
			return Response.status(500).build();
		}
		return Response.ok(jsArray.toString(),MediaType.APPLICATION_JSON).build();
	}
	
	@POST
	public Response addUsuario(Usuario gen) {
		JSONObject jsonObject = new JSONObject("{\"response\":0}");
		try {
			if(UsuarioService.save(gen)) {
				jsonObject = new JSONObject("{\"response\":1}");
			}else {
				jsonObject = new JSONObject("{\"response\":3}");
			}
		} catch (Exception e) {
			logger.error("Error: ",e);
			return Response.status(500).build();
		}
		return Response.ok(jsonObject.toString(),MediaType.APPLICATION_JSON).build();
	}
	
	@PUT
	public Response updUsuario(Usuario gen) {
		JSONObject jsonObject = new JSONObject("{\"response\":0}");
		try {
			if(UsuarioService.update(gen)) {
				jsonObject = new JSONObject("{\"response\":2}");
			}else {
				jsonObject = new JSONObject("{\"response\":3}");
			}
		} catch (Exception e) {
			logger.error("Error: ",e);
			return Response.status(500).build();
		}
		return Response.ok(jsonObject.toString(),MediaType.APPLICATION_JSON).build();
	}
	
	@DELETE
	public Response deleteUsuario(Usuario gen) {
		JSONObject jsonObject = new JSONObject("{\"result\": \"ok\"}");
		try {
			if(UsuarioService.delete(gen)) {
			}
		} catch (Exception e) {
			logger.error("Error: ",e);
			return Response.status(500).build();
		}
		return Response.ok(jsonObject.toString(),MediaType.APPLICATION_JSON).build();
	}
}
