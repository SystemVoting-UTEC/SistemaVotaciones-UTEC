package utec.voting.system.ws;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
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
 * @version 1.0 Date: September 2019
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
			logger.info("OBJECT:::"+obj.toString());
			jsArray = new JSONArray(obj);
		} catch (Exception e) {
			logger.error("Error: ",e);
			return Response.status(500).build();
		}
		return Response.ok(jsArray.toString(),MediaType.APPLICATION_JSON).build();
	}
}