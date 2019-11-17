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

import utec.voting.system.entities.TipoUsuario;
import utec.voting.system.services.TipoUsuarioImpl;


/**
 * @author manuel cardona
 *
 */
@Path("/TipoUsuario")
@Produces(MediaType.APPLICATION_JSON) 
@Consumes(MediaType.APPLICATION_JSON)
public class TipoUsuarioService implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private TipoUsuarioImpl TipoUsuarioService = new TipoUsuarioImpl();
	/**
	 * Variable de logueo para errores.
	 */
	static final Logger logger = Logger.getLogger(TipoUsuarioService.class);
	
	@GET
	public Response findAll() throws SQLException {
		JSONArray jsArray;
		List<TipoUsuario> obj = null;
		try {
			obj =  new ArrayList<TipoUsuario>();
			obj = TipoUsuarioService.getAll();
			jsArray = new JSONArray(obj);
		} catch (Exception e) {
			logger.error("Error: ",e);
			return Response.status(500).build();
		}
		return Response.ok(jsArray.toString(),MediaType.APPLICATION_JSON).build();
	}
	
	@POST
	public Response addTipoUsuario(TipoUsuario gen) {
		TipoUsuario TipoUsuario = new TipoUsuario();
		JSONObject jsonObject = null;
		try {
			if( gen != null) {
				TipoUsuario = TipoUsuarioService.save(gen);
				jsonObject = new JSONObject(TipoUsuario);
			}
		} catch (Exception e) {
			logger.error("Error: ",e);
			return Response.status(500).build();
		}
		return Response.ok(jsonObject.toString(),MediaType.APPLICATION_JSON).build();
	}
	
	@PUT
	public Response updTipoUsuario(TipoUsuario gen) {
		TipoUsuario TipoUsuario = new TipoUsuario();
		JSONObject jsonObject = null;
		try {
			if(TipoUsuarioService.update(gen)) {
				jsonObject = new JSONObject(1);
			}else {
				jsonObject = new JSONObject(0);
			}
		} catch (Exception e) {
			logger.error("Error: ",e);
			return Response.status(500).build();
		}
		return Response.ok(jsonObject.toString(),MediaType.APPLICATION_JSON).build();
	}
	
	@DELETE
	public Response deleteTipoUsuario(TipoUsuario gen) {
		JSONObject jsonObject = new JSONObject("{\"result\": \"ok\"}");
		try {
			if(TipoUsuarioService.delete(gen)) {
			}
		} catch (Exception e) {
			logger.error("Error: ",e);
			return Response.status(500).build();
		}
		return Response.ok(jsonObject.toString(),MediaType.APPLICATION_JSON).build();
	}
}
