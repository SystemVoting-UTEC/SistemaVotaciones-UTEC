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
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import utec.voting.system.entities.Candidato;
import utec.voting.system.services.CandidatoImpl;

/**
 * @author Manuel Cardona
 *
 */
@Path("/candidato")
@Produces(MediaType.APPLICATION_JSON) 
@Consumes(MediaType.APPLICATION_JSON)
public class CandidatoService implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private CandidatoImpl canService = new CandidatoImpl();
	/**
	 * Variable de logueo para errores.
	 */
	static final Logger logger = Logger.getLogger(CandidatoService.class);
	
	@GET
	public Response findAll() throws SQLException {
		JSONArray jsArray;
		List<Candidato> obj = null;
		try {
			obj =  new ArrayList<Candidato>();
			obj = canService.getAll();
			jsArray = new JSONArray(obj);
		} catch (Exception e) {
			logger.error("Error: ",e);
			return Response.status(500).build();
		}
		return Response.ok(jsArray.toString(),MediaType.APPLICATION_JSON).build();
	}
	
	@POST
	public Response addCandidato(Candidato gen) {
		JSONObject jsonObject = new JSONObject("{\"response\":0}");
		try {
			if(canService.save(gen)) {
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
	
	@PUT
	public Response updCandidato(Candidato gen) {
		JSONObject jsonObject = new JSONObject("{\"response\":0}");
		try {
			if(canService.update(gen)) {
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
	public Response deleteCandidato(Candidato gen) {
		JSONObject jsonObject = new JSONObject("{\"result\": \"ok\"}");
		try {
			if(canService.delete(gen)) {
			}
		} catch (Exception e) {
			logger.error("Error: ",e);
			return Response.status(500).build();
		}
		return Response.ok(jsonObject.toString(),MediaType.APPLICATION_JSON).build();
	}

	@GET
	@Path("/{parId}")
	public Response getCandidatosByDepartamento(@PathParam("parId") Integer parId) {
		JSONArray jsArray;
		List<Candidato> obj = null;
		try {
			obj =  new ArrayList<Candidato>();
			obj = canService.getAll(parId);
			jsArray = new JSONArray(obj);
		} catch (Exception e) {
			logger.error("Error: ",e);
			return Response.status(500).build();
		}
		return Response.ok(jsArray.toString(),MediaType.APPLICATION_JSON).build();
	}

}
