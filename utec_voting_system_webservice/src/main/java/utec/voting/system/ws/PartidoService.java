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

import utec.voting.system.entities.Partido;
import utec.voting.system.services.PartidoImpl;

/**
 * @author Kevin Orellana
 *
 */
@Path("/partido")
@Produces(MediaType.APPLICATION_JSON) 
@Consumes(MediaType.APPLICATION_JSON)
public class PartidoService implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private PartidoImpl partidoService = new PartidoImpl();
	/**
	 * Variable de logueo para errores.
	 */
	static final Logger logger = Logger.getLogger(PartidoService.class);
	
	@GET
	public Response findAll() throws SQLException {
		JSONArray jsArray;
		List<Partido> obj = null;
		try {
			obj =  new ArrayList<Partido>();
			obj = partidoService.getAll();
			jsArray = new JSONArray(obj);
		} catch (Exception e) {
			logger.error("Error: ",e);
			return Response.status(500).build();
		}
		return Response.ok(jsArray.toString(),MediaType.APPLICATION_JSON).build();
	}
	
	@POST
	public Response addPartido(Partido gen) {
		JSONObject jsonObject = new JSONObject("{\"response\":0}");
		try {
			if(partidoService.save(gen)) {
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
	public Response updPartido(Partido gen) {
		JSONObject jsonObject = new JSONObject("{\"response\":0}");
		try {
			if(partidoService.update(gen)) {
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
	public Response deletePartido(Partido gen) {
		JSONObject jsonObject = new JSONObject("{\"result\": \"ok\"}");
		try {
			if(partidoService.delete(gen)) {
			}
		} catch (Exception e) {
			logger.error("Error: ",e);
			return Response.status(500).build();
		}
		return Response.ok(jsonObject.toString(),MediaType.APPLICATION_JSON).build();
	}
	
}
