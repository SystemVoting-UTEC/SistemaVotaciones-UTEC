package com.utec.voting.service;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.utec.voting.jdbc.Conexion;
import com.utec.voting.modelo.OptionMenu;
import com.utec.voting.modelo.OptionTipoUsuario;
import com.utec.voting.modelo.Partido;
import com.utec.voting.modelo.TipoUsuario;

public class OptionTipoUsuarioService extends Conexion implements Service<OptionTipoUsuario>, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String TABLE = "OPTION_TIPO_USUARIO";

	/**
	 * Variable de logueo para errores.
	 */
	static final Logger logger = Logger.getLogger(OptionMenuService.class);
	@Override
	public ArrayList<OptionTipoUsuario> getAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean save(OptionTipoUsuario t) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean update(OptionTipoUsuario t) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean delete(OptionTipoUsuario t) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OptionTipoUsuario finById(Integer id) throws SQLException {
		OptionTipoUsuario g = null;
		OptionMenuService optService = new OptionMenuService();
		TipoUsuarioService tipoUsuarioService = new TipoUsuarioService();
		try {
			setPs(consPrepare(SELECT + TABLE + WHERE + "EST_ID = ?"));
			getPs().setInt(1, id);
			setRs(getPs().executeQuery());
			if(getRs().next()) {
				getRs().beforeFirst();
				while (getRs().next()) {
					TipoUsuario tpu = tipoUsuarioService.finById(id);
					OptionMenu optMe = optService.finById(id);
					g = new OptionTipoUsuario(optMe,tpu);
				}
			}
		} catch (Exception e) {
			logger.error("Error: " + e);
		} finally {
			getPs().close();
		}
		return g;
	}

	@Override
	public OptionTipoUsuario finById(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
