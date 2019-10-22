package utec.voting.system.ws;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;

import com.utec.voting.jdbc.Conexion;

import utec.voting.system.entities.Persona;
import utec.voting.system.entities.TipoUsuario;
import utec.voting.system.entities.Usuario;
import utec.voting.system.services.PersonaImpl;
import utec.voting.system.services.Service;

@Path("/login")
@Produces(MediaType.APPLICATION_JSON) 
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioService extends Conexion implements Service<Usuario>, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private PersonaImpl personaService = new PersonaImpl();

	@Override
	public ArrayList<Usuario> getAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public Boolean save(Usuario t) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean update(Usuario t) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean delete(Usuario t) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario finById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario finById(String id) throws SQLException {
		Usuario obj = null;
		return obj;
	}
	
	@POST
	public Response finByCredential(Usuario us) throws SQLException {
		Usuario obj = null;
		TipoUsuario tpusu = null;
		Persona per = null;
		JSONObject jsonObject = null;
		System.out.println("Request: "+us);
		try {
			String query = "{CALL SP_LOGIN(?,?)}";
			CallableStatement stmt = getConnection().prepareCall(query);
			stmt.setString(1, us.getUsPerDui().getPerDui());
			stmt.setString(2, us.getUsPassword());
			setRs(stmt.executeQuery());
			if(getRs().next()) {
				getRs().beforeFirst();
				while (getRs().next()) {
					per = personaService.finById(us.getUsPerDui().getPerDui());
					tpusu = new TipoUsuario(getRs().getInt(3), "ADM");
					obj =  new Usuario(per, getRs().getString(2), tpusu);
				}	
			}
			System.out.println("Response: "+obj);
			jsonObject = new JSONObject(obj);
		} catch (Exception e) {
			System.out.println("Error: "+e);
			return Response.status(500).build();
		}
		return Response.ok(jsonObject.toString(),MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("")
	public Response convertFtoC() throws JSONException {
 
		JSONObject jsonObject = new JSONObject();
		Double fahrenheit = 98.24;
		Double celsius;
		celsius = (fahrenheit - 32) * 5 / 9;
		jsonObject.put("F Value", fahrenheit);
		jsonObject.put("C Value", celsius);
 
		String result = "@Produces(\"application/json\") Output: \n\nF to C Converter Output: \n\n" + jsonObject;
		return Response.status(200).entity(result).build();
	}

}
