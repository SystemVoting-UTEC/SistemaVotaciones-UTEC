package com.utec.voting.service;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.utec.voting.jdbc.Conexion;
import com.utec.voting.modelo.Persona;
import com.utec.voting.modelo.TipoUsuario;
import com.utec.voting.modelo.Usuario;
import com.utec.voting.util.Encriptar;

/**
 * @author Kevin Orellana
 * @version 1.0 Date: September 2019
 */

public class UsuarioService extends Conexion implements Service<Usuario>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Variable de logueo para errores.
	 */
	static final Logger logger = Logger.getLogger(UsuarioService.class);
	
	private static final String TABLE = "USUARIO";
	
	private PersonaService personService = new PersonaService();
	private TipoUsuarioService tipoUsService = new TipoUsuarioService();

	public Usuario findByCredentials(String dui, String password) throws SQLException {
		Usuario obj = null;
		Persona prs = null;
		TipoUsuario tpus = null;
		try {
			setPs(consPrepare(SELECT + TABLE + WHERE + "US_PER_DUI = ? AND US_PASSWORD = ?"));
			getPs().setString(1, dui);
			getPs().setString(2, Encriptar.sha1(password));
			while (getRs().next()) {
				prs = personService.finById(getRs().getString(1));
				tpus = tipoUsService.finById(getRs().getInt(3));
				obj = new Usuario(prs, getRs().getString(2), tpus);
			}
		} catch (Exception e) {
			logger.error("Error: " + e);
		} finally {
			getPs().close();
		}
		return obj;
	}

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
		return null;
	}

}
