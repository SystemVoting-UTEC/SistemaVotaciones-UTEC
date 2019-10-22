package utec.voting.system.ws;

import java.io.Serializable;
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

import com.mysql.jdbc.CallableStatement;
import com.utec.voting.jdbc.Conexion;

import utec.voting.system.entities.Persona;
import utec.voting.system.entities.TipoUsuario;
import utec.voting.system.entities.Usuario;
import utec.voting.system.services.Service;

@Path("/login")
@Produces(MediaType.APPLICATION_JSON) 
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioService extends Conexion implements Service<Usuario>, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
	public Usuario finById(@PathParam("id") String id) throws SQLException {
		Usuario obj = null;
		return obj;
	}
	
	@SuppressWarnings("finally")
	@POST
	@Path("/finByCredential/{id}/{pass}")
	public Response finByCredential(@PathParam("id") String dui, @PathParam("pass") String pass) throws SQLException {
		Usuario obj = null;
		TipoUsuario tpusu = null;
		Persona per = null;
		JSONObject jsonObject = null;
		
		try {
			String query = "{CALL SP_LOGIN(?,?)}";
			CallableStatement stmt = (CallableStatement) getConnection().prepareCall(query);
			stmt.setString(1, dui);
			stmt.setString(2, pass);
			setRs(stmt.executeQuery());
			if(getRs().next()) {
				getRs().beforeFirst();
				while (getRs().next()) {
					per = new Persona();
					per.setPerDui(getRs().getString(1));
					tpusu = new TipoUsuario(getRs().getInt(3), "ADM");
					obj =  new Usuario(per, getRs().getString(2), tpusu);
				}	
			}
			jsonObject = new JSONObject(obj);
		} catch (Exception e) {
			System.out.println("Error: "+e);
			return Response.ok(jsonObject.toString(),MediaType.APPLICATION_JSON).build();
		} finally {
			return Response.ok(jsonObject.toString(),MediaType.APPLICATION_JSON).build();
		}
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
