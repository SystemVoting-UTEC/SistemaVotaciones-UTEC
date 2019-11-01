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
	
	private OptionMenuImpl optionMenuService = new OptionMenuImpl();
	/**
	 * Variable de logueo para errores.
	 */
	static final Logger logger = Logger.getLogger(OptionMenuService.class);
	
	@GET
	public Response findAll() throws SQLException {
		JSONArray jsArray;
		List<OptionMenu> obj = null;
		try {
			obj =  new ArrayList<OptionMenu>();
			obj = optionMenuService.getAll();
			jsArray = new JSONArray(obj);
		} catch (Exception e) {
			logger.error("Error: ",e);
			return Response.status(500).build();
		}
		return Response.ok(jsArray.toString(),MediaType.APPLICATION_JSON).build();
	}
	
	@POST
	public Response addOptionMenu(OptionMenu gen) {
		logger.error("Request>>>>>"+gen);
		OptionMenu OptionMenu = new OptionMenu();
		JSONObject jsonObject = null;
		try {
			if( gen != null) {
				OptionMenu = optionMenuService.save(OptionMenu);
				jsonObject = new JSONObject(OptionMenu);
			}
		} catch (Exception e) {
			logger.error("Error: ",e);
			return Response.status(500).build();
		}
		return Response.ok(jsonObject.toString(),MediaType.APPLICATION_JSON).build();
	}
	
	@PUT
	public Response updOptionMenu(OptionMenu gen) {
		OptionMenu OptionMenu = new OptionMenu();
		JSONObject jsonObject = null;
		try {
			if( gen != null) {
				OptionMenu = optionMenuService.update(OptionMenu);
				jsonObject = new JSONObject(OptionMenu);
			}
		} catch (Exception e) {
			logger.error("Error: ",e);
			return Response.status(500).build();
		}
		return Response.ok(jsonObject.toString(),MediaType.APPLICATION_JSON).build();
	}
	
	@DELETE
	public Response deleteOptionMenu(OptionMenu gen) {
		JSONObject jsonObject = new JSONObject("{\"result\": \"ok\"}");
		try {
			if(optionMenuService.delete(gen)) {
			}
		} catch (Exception e) {
			logger.error("Error: ",e);
			return Response.status(500).build();
		}
		return Response.ok(jsonObject.toString(),MediaType.APPLICATION_JSON).build();
	}

	@GET
	@Path("/{dui}")
	public Response findAll(@PathParam("dui") Integer dui) throws SQLException {
		JSONArray jsArray;
		List<OptionMenu> obj = null;
		try {
			obj =  new ArrayList<OptionMenu>();
			obj = optionMenuService.getAll(dui);
			jsArray = new JSONArray(obj);
		} catch (Exception e) {
			logger.error("Error: ",e);
			return Response.status(500).build();
		}
		return Response.ok(jsArray.toString(),MediaType.APPLICATION_JSON).build();
	}
}
