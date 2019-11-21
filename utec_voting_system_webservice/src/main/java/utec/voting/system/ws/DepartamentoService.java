/**
 * 
 */
package utec.voting.system.ws;

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

import utec.voting.system.entities.Departamento;
import utec.voting.system.services.DepartamentoImpl;

@Path("/departamento")
@Produces(MediaType.APPLICATION_JSON) 
@Consumes(MediaType.APPLICATION_JSON)

public class DepartamentoService {
	
private static final long serialVersionUID = 1L;
	
	private DepartamentoImpl departamentoService = new DepartamentoImpl();
	/**
	 * Variable de logueo para errores.
	 */
	static final Logger logger = Logger.getLogger(DepartamentoService.class);
	
	@GET
	public Response findAll() throws SQLException {
		JSONArray jsArray;
		List<Departamento> obj = null;
		try {
			obj =  new ArrayList<Departamento>();
			obj = departamentoService.getAll();
			jsArray = new JSONArray(obj);
		} catch (Exception e) {
			logger.error("Error: ",e);
			return Response.status(500).build();
		}
		return Response.ok(jsArray.toString(),MediaType.APPLICATION_JSON).build();
	}
	
	@POST
	public Response addDepartamento(Departamento gen) {
		JSONObject jsonObject = new JSONObject("{\"response\":0}");
		try {
			if(departamentoService.save(gen)) {
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
	public Response updDepartamento(Departamento gen) {
		JSONObject jsonObject = new JSONObject("{\"response\":0}");
		try {
			if(departamentoService.update(gen)) {
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
	public Response deleteDepartamento(Departamento gen) {
		JSONObject jsonObject = new JSONObject("{\"result\": \"ok\"}");
		try {
			if(departamentoService.delete(gen)) {
			}
		} catch (Exception e) {
			logger.error("Error: ",e);
			return Response.status(500).build();
		}
		return Response.ok(jsonObject.toString(),MediaType.APPLICATION_JSON).build();
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static Departamento finById(int int1) {
		// TODO Auto-generated method stub
		return null;
	}


}
