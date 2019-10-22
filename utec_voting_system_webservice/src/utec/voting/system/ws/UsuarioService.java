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

import org.json.JSONException;
import org.json.JSONObject;

import com.utec.voting.jdbc.Conexion;

import utec.voting.system.entities.Usuario;
import utec.voting.system.services.Service;

@Path("/usuario")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioService extends Conexion implements Service<Usuario>, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String TABLE = "USUARIO";

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
		
		try {
			setPs(consPrepare(SELECT + TABLE + WHERE + "US_PER_DUI = ?"));
			getPs().setString(1, id);
			setRs(getPs().executeQuery());
			if(getRs().next()) {
				getRs().beforeFirst();
				while (getRs().next()) {
					System.out.println("Valor "+getRs().getString(1));
				}	
			}
		} catch (Exception e) {
			return obj;
		} finally {
			getPs().close();
		}
		return obj;
	}
	
	@SuppressWarnings("finally")
	@GET
	@Path("/findId/{id}")
	public Response finByIds(@PathParam("id") String id) throws SQLException {
		Usuario obj = null;
		JSONObject jsonObject = null;
		
		try {
			setPs(consPrepare(SELECT + TABLE + WHERE + "US_PER_DUI = ?"));
			getPs().setString(1, id);
			setRs(getPs().executeQuery());
			if(getRs().next()) {
				getRs().beforeFirst();
				while (getRs().next()) {
					System.out.println("Valor "+getRs().getString(1));
				}	
			}
			jsonObject = new JSONObject(obj);
		} catch (Exception e) {
			return Response.status(200).entity(jsonObject).build();
		} finally {
			return Response.status(200).entity(jsonObject).build();
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
