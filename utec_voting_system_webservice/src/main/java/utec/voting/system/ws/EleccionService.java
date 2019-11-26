/**
 * 
 */
package utec.voting.system.ws;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import utec.voting.system.entities.Eleccion;
import utec.voting.system.entities.Genero;
import utec.voting.system.services.EleccionImpl;

/**
 * @author Kevin Orellana
 *
 */
@Path("/eleccion")
@Produces(MediaType.APPLICATION_JSON) 
@Consumes(MediaType.APPLICATION_JSON)
public class EleccionService implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EleccionImpl eleccionService = new EleccionImpl();
	/**
	 * Variable de logueo para errores.
	 */
	static final Logger logger = Logger.getLogger(EleccionService.class);
	

	@GET
	public Response findAll() throws SQLException {
		JSONObject jsonObject = null;
		Eleccion obj = null;
		try {
			obj =  new Eleccion();
			obj = eleccionService.eleccionIsActive();
			jsonObject = new JSONObject(obj);
		} catch (Exception e) {
			logger.error("Error: ",e);
			return Response.status(500).build();
		}
		return Response.ok(jsonObject.toString(),MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("/{dui}")
	public Response findAll(@PathParam("dui") String dui) throws SQLException {
		JSONObject jsonObject = null;
		Eleccion obj = new Eleccion();
		try {
			obj = eleccionService.eleccionIsActive(dui);
			if (obj == null) {
				obj= new Eleccion();
				obj.setElcId(0);
			}
			jsonObject = new JSONObject(obj);
		} catch (Exception e) {
			logger.error("Error: ",e);
			return Response.status(500).build();
		}
		return Response.ok(jsonObject.toString(),MediaType.APPLICATION_JSON).build();
	}

}
