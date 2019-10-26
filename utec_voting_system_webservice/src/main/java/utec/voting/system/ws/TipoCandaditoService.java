/**
 * 
 */
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

import utec.voting.system.entities.TipoCandidato;
import utec.voting.system.services.TipoCandidatoImpl;

/**
 * @author Manuel Cardona
 *
 */

@Path("/tipo_candidato")
@Produces(MediaType.APPLICATION_JSON) 
@Consumes(MediaType.APPLICATION_JSON)
public class TipoCandaditoService implements Serializable{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		private TipoCandidatoImpl tpcService = new TipoCandidatoImpl();
		/**
		 * Variable de logueo para errores.
		 */
		static final Logger logger = Logger.getLogger(TipoCandaditoService.class);
		
		@GET
		public Response findAll() throws SQLException {
			JSONArray jsArray;
			List<TipoCandidato> obj = null;
			try {
				obj =  new ArrayList<TipoCandidato>();
				obj = tpcService.getAll();
				jsArray = new JSONArray(obj);
			} catch (Exception e) {
				logger.error("Error: ",e);
				return Response.status(500).build();
			}
			return Response.ok(jsArray.toString(),MediaType.APPLICATION_JSON).build();
		}
}
