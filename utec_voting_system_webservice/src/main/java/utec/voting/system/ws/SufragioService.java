/**
 * 
 */
package utec.voting.system.ws;

import java.io.Serializable;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import utec.voting.system.entities.Candidato;
import utec.voting.system.entities.Partido;
import utec.voting.system.entities.Sufragio;
import utec.voting.system.services.SufragioImpl;

/**
 * @author Kevin Orellana
 *
 */

@Path("/voto")
@Produces(MediaType.APPLICATION_JSON) 
@Consumes(MediaType.APPLICATION_JSON)
public class SufragioService implements Serializable{
	
	/**
	 * Variable de logueo para errores.
	 */
	static final Logger logger = Logger.getLogger(SufragioService.class);
	
	private SufragioImpl sufragioService = new SufragioImpl();
	
	@GET
	public Response tester() {
		return Response.ok("{Hola:2}",MediaType.APPLICATION_JSON).build();
	}
	
	@POST
	public Response addSufragio(Sufragio suf) {
		JSONObject jsonObject = null;
		try {
			if(sufragioService.insertSufragio(suf)) {
				jsonObject = new JSONObject("{\"response\":1}");
			}else {
				jsonObject = new JSONObject("{\"response\":0}");
			}
		} catch (Exception e) {
			logger.error("Error: ",e);
			return Response.status(500).build();
		}
		return Response.ok(jsonObject.toString(),MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("/{dui}/{elc}")
	public Response getSufragioEnable(@PathParam("dui") String dui,@PathParam("elc") Integer elc) {
		Candidato Candidato = new Candidato();
		JSONObject jsonObject = null;
		try {
			if(sufragioService.sufragioEnabled(dui, elc)) {
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
}
