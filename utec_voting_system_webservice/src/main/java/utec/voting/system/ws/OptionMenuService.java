package utec.voting.system.ws;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.json.JSONArray;

import utec.voting.system.entities.OptionMenu;
import utec.voting.system.services.OptionMenuImpl;

@Path("/option_menu")
@Produces(MediaType.APPLICATION_JSON) 
@Consumes(MediaType.APPLICATION_JSON)
public class OptionMenuService implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private OptionMenuImpl generoService = new OptionMenuImpl();
	/**
	 * Variable de logueo para errores.
	 */
	static final Logger logger = Logger.getLogger(OptionMenuService.class);
	
	@GET
	@Path("/{dui}")
	public Response findAll(@PathParam("dui") Integer dui) throws SQLException {
		JSONArray jsArray;
		List<OptionMenu> obj = null;
		try {
			obj =  new ArrayList<OptionMenu>();
			obj = generoService.getAll(dui);
			jsArray = new JSONArray(obj);
		} catch (Exception e) {
			logger.error("Error: ",e);
			return Response.status(500).build();
		}
		return Response.ok(jsArray.toString(),MediaType.APPLICATION_JSON).build();
	}
}
