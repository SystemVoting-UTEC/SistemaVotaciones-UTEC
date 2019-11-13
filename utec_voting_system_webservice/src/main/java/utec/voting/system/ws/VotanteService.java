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

import utec.voting.system.entities.Votante;
import utec.voting.system.services.VotanteImpl;

/**
 * 
 * @author fxnavas
 *
 */
@Path("/votante")
@Produces(MediaType.APPLICATION_JSON) 
@Consumes(MediaType.APPLICATION_JSON)
public class VotanteService{

	private static final long serialVersionUID = 1L;

	private VotanteImpl vService = new VotanteImpl();
	/**
	 * Variable de logueo para errores.
	 */
	static final Logger logger = Logger.getLogger(VotanteService.class);
	
	@GET
	public Response findAll() throws SQLException {
		JSONArray jsArray;
		List<Votante> obj;
		try {
			
			obj =  new ArrayList<Votante>();
			obj = vService.getAll();
			System.out.println("Despues del getAll");
			jsArray = new JSONArray(obj);			
		} catch (Exception e) {
			logger.error("Error: " + e);
			return Response.status(500).build();
		}
		return Response.ok(jsArray.toString(),MediaType.APPLICATION_JSON).build();
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
