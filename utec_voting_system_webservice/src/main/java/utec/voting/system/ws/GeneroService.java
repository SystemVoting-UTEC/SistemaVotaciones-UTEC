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

import utec.voting.system.entities.Genero;
import utec.voting.system.services.GeneroImpl;

/**
 * @author Kevin Orellana
 * 
 */

@Path("/genero")
@Produces(MediaType.APPLICATION_JSON) 
@Consumes(MediaType.APPLICATION_JSON)
public class GeneroService implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private GeneroImpl generoService = new GeneroImpl();
	/**
	 * Variable de logueo para errores.
	 */
	static final Logger logger = Logger.getLogger(GeneroService.class);
	
	@GET
	public Response findAll() throws SQLException {
		JSONArray jsArray;
		List<Genero> obj = null;
		try {
			obj =  new ArrayList<Genero>();
			obj = generoService.getAll();
			jsArray = new JSONArray(obj);
		} catch (Exception e) {
			logger.error("Error: ",e);
			return Response.status(500).build();
		}
		return Response.ok(jsArray.toString(),MediaType.APPLICATION_JSON).build();
	}
	
	@POST
	public Response addGenero(Genero gen) {
		JSONObject jsonObject = new JSONObject("{\"response\":0}");
		try {
			if( gen != null) {
				if(generoService.save(gen)) {
					jsonObject = new JSONObject("{\"response\":1}");
				}else {
					jsonObject = new JSONObject("{\"response\":3}");
				}
			}
		} catch (Exception e) {
			return Response.ok(jsonObject.toString(),MediaType.APPLICATION_JSON).build();
		}
		return Response.ok(jsonObject.toString(),MediaType.APPLICATION_JSON).build();
	}
	
	@PUT
	public Response updGenero(Genero gen) {
		JSONObject jsonObject = null;
		try {
			if(generoService.update(gen)) {
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
	public Response deleteGenero(Genero gen) {
		JSONObject jsonObject = new JSONObject("{\"result\": \"ok\"}");
		try {
			if(generoService.delete(gen)) {
			}
		} catch (Exception e) {
			logger.error("Error: ",e);
			return Response.status(500).build();
		}
		return Response.ok(jsonObject.toString(),MediaType.APPLICATION_JSON).build();
	}
}
