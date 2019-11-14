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

import utec.voting.system.entities.Persona;
import utec.voting.system.services.PersonaImpl;
/**
 * @author manuel cardona
 *
 */
@Path("/persona")
@Produces(MediaType.APPLICATION_JSON) 
@Consumes(MediaType.APPLICATION_JSON)

public class PersonaService implements Serializable {
	
private static final long serialVersionUID = 1L;
	
	private PersonaImpl PersonaService = new PersonaImpl();
	/**
	 * Variable de logueo para errores.
	 */
	static final Logger logger = Logger.getLogger(PersonaService.class);
	
	@GET
	public Response findAll() throws SQLException {
		JSONArray jsArray;
		List<Persona> obj = null;
		try {
			obj =  new ArrayList<Persona>();
			obj = PersonaService.getAll();
			jsArray = new JSONArray(obj);
		} catch (Exception e) {
			logger.error("Error: ",e);
			return Response.status(500).build();
		}
		return Response.ok(jsArray.toString(),MediaType.APPLICATION_JSON).build();
	}
	@POST
	public Response addPersona(Persona gen) {
		logger.error("Request>>>>>"+gen);
		Persona Persona = new Persona();
		JSONObject jsonObject = null;
		try {
			if( gen != null) {
				Persona = PersonaService.save(Persona);
				jsonObject = new JSONObject(Persona);
			}
		} catch (Exception e) {
			logger.error("Error: ",e);
			return Response.status(500).build();
		}
		return Response.ok(jsonObject.toString(),MediaType.APPLICATION_JSON).build();
	}
	
	@PUT
	public Response updPersona(Persona gen) {
		Persona Persona = new Persona();
		JSONObject jsonObject = null;
		try {
			if(PersonaService.update(gen)) {
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
	public Response deletePersona(Persona gen) {
		JSONObject jsonObject = new JSONObject("{\"result\": \"ok\"}");
		try {
			if(PersonaService.delete(gen)) {
			}
		} catch (Exception e) {
			logger.error("Error: ",e);
			return Response.status(500).build();
		}
		return Response.ok(jsonObject.toString(),MediaType.APPLICATION_JSON).build();
	}



}
