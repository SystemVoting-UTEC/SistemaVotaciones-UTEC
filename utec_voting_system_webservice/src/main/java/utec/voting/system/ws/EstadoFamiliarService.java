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

import utec.voting.system.entities.EstadoFamiliar;
import utec.voting.system.services.EstadoFamiliarImpl;

/**
 * @author Manuel Cardona
 *
 */

@Path("/Estado_Familiar")
@Produces(MediaType.APPLICATION_JSON) 
@Consumes(MediaType.APPLICATION_JSON)
public class EstadoFamiliarService implements Serializable{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		private EstadoFamiliarImpl tpcService = new EstadoFamiliarImpl();
		/**
		 * Variable de logueo para errores.
		 */
		static final Logger logger = Logger.getLogger(EstadoFamiliarService.class);
		
		@GET
		public Response findAll() throws SQLException {
			JSONArray jsArray;
			List<EstadoFamiliar> obj = null;
			try {
				obj =  new ArrayList<EstadoFamiliar>();
				obj = tpcService.getAll();
				jsArray = new JSONArray(obj);
			} catch (Exception e) {
				logger.error("Error: ",e);
				return Response.status(500).build();
			}
			return Response.ok(jsArray.toString(),MediaType.APPLICATION_JSON).build();
		}
		
		@POST
		public Response addEstadoFamiliar(EstadoFamiliar gen) {
			logger.error("Request>>>>>"+gen);
			EstadoFamiliar EstadoFamiliar = new EstadoFamiliar();
			JSONObject jsonObject = null;
			try {
				if( gen != null) {
					EstadoFamiliar = tpcService.save(EstadoFamiliar);
					jsonObject = new JSONObject(EstadoFamiliar);
				}
			} catch (Exception e) {
				logger.error("Error: ",e);
				return Response.status(500).build();
			}
			return Response.ok(jsonObject.toString(),MediaType.APPLICATION_JSON).build();
		}
		
		@PUT
		public Response updEstadoFamiliar(EstadoFamiliar gen) {
			EstadoFamiliar EstadoFamiliar = new EstadoFamiliar();
			JSONObject jsonObject = null;
			try {
				if(tpcService.update(gen)) {
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
		public Response deleteEstadoFamiliar(EstadoFamiliar gen) {
			JSONObject jsonObject = new JSONObject("{\"result\": \"ok\"}");
			try {
				if(tpcService.delete(gen)) {
				}
			} catch (Exception e) {
				logger.error("Error: ",e);
				return Response.status(500).build();
			}
			return Response.ok(jsonObject.toString(),MediaType.APPLICATION_JSON).build();
		}

}

