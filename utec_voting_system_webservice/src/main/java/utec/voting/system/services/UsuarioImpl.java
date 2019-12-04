package utec.voting.system.services;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
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
	
	private final PersonaImpl personaService = new PersonaImpl();
	private final TipoUsuarioImpl tipoUsuarioService = new TipoUsuarioImpl();
	Persona Persona = new Persona();
	TipoUsuario TipoUsuario = new TipoUsuario();

	@Override
	public ArrayList<Usuario> getAll() throws SQLException {
		Usuario g;
		final ArrayList<Usuario> l1 = new ArrayList<>();
		CallableStatement stmt = null;
		try {
			final String query = "{CALL SP_READ_ALL_USUARIO()}";
			stmt = getConnection().prepareCall(query);
			setRs(stmt.executeQuery());
			if (getRs().next()) {
				getRs().beforeFirst();
				while (getRs().next()) {

					Persona = new Persona();
					Persona = personaService.finById(getRs().getString(1));

					TipoUsuario = new TipoUsuario();
					TipoUsuario = tipoUsuarioService.finById(getRs().getInt(3));

					g = new Usuario();
					g.setUsPassword(getRs().getString(2));
					g.setUsPerDui(Persona);
					g.setUsTusId(TipoUsuario);
					g.setUsEstado(getRs().getInt(4));
					l1.add(g);
				}
			}
		} catch (final Exception e) {
			logger.error("Error: " + e);
		} finally {
			stmt.close();
		}
		return l1;

	}

	@Override
	public Boolean save(Usuario t) throws SQLException {
		CallableStatement stmt = null;
		try {
			final String query = "{CALL SP_CREATE_USUARIO(?,?,?,?,?)}";
			stmt = getConnection().prepareCall(query);
			stmt.setString(1, t.getUsPerDui().getPerDui());
			stmt.setString(2, t.getUsPassword());
			stmt.setInt(3, t.getUsTusId().getTusId());
			stmt.setInt(4, t.getUsEstado());
			stmt.setString(5, t.getUsEmail());
			stmt.registerOutParameter(6, Types.INTEGER);
			stmt.execute();
			if (stmt.getInt(6) > 0) {
				return Boolean.TRUE;
			}
		} catch (final Exception e) {
			logger.error("Error" + e);
		} finally {
			stmt.close();
		}
		return Boolean.FALSE;
	}

	@Override
	public Boolean update(Usuario t) throws SQLException {
		CallableStatement stmt = null;
		try {
			final String query = "{CALL SP_UPDATE_USUARIO(?,?,?,?,?)";
			stmt = getConnection().prepareCall(query);
			stmt.setString(1, t.getUsPerDui().getPerDui());
			stmt.setString(2, t.getUsPassword());
			stmt.setInt(3, t.getUsTusId().getTusId());
			stmt.setInt(4, t.getUsEstado());
			stmt.setString(5, t.getUsEmail());
			stmt.registerOutParameter(6, Types.INTEGER);
			stmt.execute();
			if (stmt.getInt(6) > 0) {
				return Boolean.TRUE;
			}
		} catch (final Exception e) {
			logger.error("Error" + e);
		} finally {
			stmt.close();
		}
		return Boolean.FALSE;
	}

	@Override
	public Boolean delete(final Usuario t) throws SQLException {
		return null;
	}

	@Override
	public Usuario finById(final Integer id) throws SQLException {
		return null;
	}

	@Override
	public Usuario finById(final String id) throws SQLException {
		return null;
	}

	public Usuario loginCredential(final Usuario us) throws SQLException {
		Usuario obj = null;
		TipoUsuario tpusu = null;
		Persona per = null;
		CallableStatement stmt = null;
		try {
			final String query = "{CALL SP_LOGIN(?,?)}";
			stmt = getConnection().prepareCall(query);
			stmt.setString(1, us.getUsPerDui().getPerDui());
			stmt.setString(2, us.getUsPassword());
			setRs(stmt.executeQuery());
			if (getRs().next()) {
				getRs().beforeFirst();
				while (getRs().next()) {
					per = personaService.finById(us.getUsPerDui().getPerDui());
					tpusu = tipoUsuarioService.finById(getRs().getInt(3));
					obj = new Usuario(per, getRs().getString(2), tpusu,getRs().getString(4));
				}
			}
		} catch (final Exception e) {
			logger.error("Al loginCredential: ",e);
		}finally {
			stmt.close();
		}
		return obj;
	}
}
