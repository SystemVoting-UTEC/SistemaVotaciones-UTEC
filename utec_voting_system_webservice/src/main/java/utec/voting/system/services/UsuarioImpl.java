package utec.voting.system.services;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import utec.voting.system.entities.Persona;
import utec.voting.system.entities.TipoUsuario;
import utec.voting.system.entities.Usuario;
import utec.voting.system.jdbc.Conexion;

/**
 * @author Kevin Orellana
 * @version 1.0 Date: September 2019
 */

public class UsuarioImpl extends Conexion implements Service<Usuario>, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Variable de logueo para errores.
	 */
	static final Logger logger = Logger.getLogger(UsuarioImpl.class);
	
	private PersonaImpl personaService = new PersonaImpl();
	private TipoUsuarioImpl tipoUsuarioService = new TipoUsuarioImpl();

	@Override
	public ArrayList<Usuario> getAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario save(Usuario t) throws SQLException {
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
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public Usuario loginCredential(Usuario us) throws SQLException {
		Usuario obj = null;
		TipoUsuario tpusu = null;
		Persona per = null;
		CallableStatement stmt = null;
		try {
			String query = "{CALL SP_LOGIN(?,?)}";
			stmt = getConnection().prepareCall(query);
			stmt.setString(1, us.getUsPerDui().getPerDui());
			stmt.setString(2, us.getUsPassword());
			setRs(stmt.executeQuery());
			if(getRs().next()) {
				getRs().beforeFirst();
				while (getRs().next()) {
					per = personaService.finById(us.getUsPerDui().getPerDui());
					tpusu = tipoUsuarioService.finById(getRs().getInt(3));
					obj =  new Usuario(per, getRs().getString(2), tpusu);
				}	
			}
		} catch (Exception e) {
			logger.error("Al loginCredential: ",e);
		}finally {
			stmt.close();
		}
		return obj;
	}
}
