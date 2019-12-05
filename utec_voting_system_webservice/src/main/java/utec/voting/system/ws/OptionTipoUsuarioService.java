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

import utec.voting.system.entities.OptionTipoUsuario;
import utec.voting.system.services.OptionTipoUsuarioImpl;


/**
 * @author manuel cardona
 *
 */
@Path("/OptionTipoUsuario")
@Produces(MediaType.APPLICATION_JSON) 
@Consumes(MediaType.APPLICATION_JSON)
public class OptionTipoUsuarioService implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private OptionTipoUsuarioImpl OptionTipoUsuarioService= new OptionTipoUsuarioImpl();
	/**
	 * Variable de logueo para errores.
	 */
	static final Logger logger = Logger.getLogger(OptionTipoUsuarioService.class);
	
	@GET
	public Response findAll() throws SQLException {
		JSONArray jsArray;
		List<OptionTipoUsuario> obj = null;
		try {
			obj =  new ArrayList<OptionTipoUsuario>();
			obj = OptionTipoUsuarioService.getAll();
			jsArray = new JSONArray(obj);
		} catch (Exception e) {
			logger.error("Error: ",e);
			return Response.status(500).build();
		}
		return Response.ok(jsArray.toString(),MediaType.APPLICATION_JSON).build();
	}
	
	@POST
	public Response addOptionTipoUsuario(OptionTipoUsuario gen) {
		JSONObject jsonObject = new JSONObject("{\"response\":0}");
		try {
			if(OptionTipoUsuarioService.save(gen)) {
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
	public Response updOptionTipoUsuario(OptionTipoUsuario gen) {
		JSONObject jsonObject = new JSONObject("{\"response\":0}");
		try {
			if(OptionTipoUsuarioService.update(gen)) {
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
	public Response deleteOptionTipoUsuario(OptionTipoUsuario gen) {
		JSONObject jsonObject = new JSONObject("{\"result\": \"ok\"}");
		try {
			if(OptionTipoUsuarioService.delete(gen)) {
			}
		} catch (Exception e) {
			logger.error("Error: ",e);
			return Response.status(500).build();
		}
		return Response.ok(jsonObject.toString(),MediaType.APPLICATION_JSON).build();
	}

}
