package com.utec.voting.service;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.utec.voting.jdbc.Conexion;
import com.utec.voting.modelo.TipoUsuario;

/**
 * @author Kevin Orellana
 * @version 1.0 Date: September 2019
 */

public class TipoUsuarioService extends Conexion implements Service<TipoUsuario>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String TABLE = "TIPO_USUARIO";

	/**
	 * Variable de logueo para errores.
	 */
	static final Logger logger = Logger.getLogger(TipoUsuarioService.class);
	
	@Override
	public ArrayList<TipoUsuario> getAll() throws SQLException {
		TipoUsuario g;
		ArrayList<TipoUsuario> l1 = new ArrayList<>();
		try {
			setRs(consPrepare(SELECT + TABLE).executeQuery());
			while (getRs().next()) {
				g = new TipoUsuario(getRs().getInt(1), getRs().getString(2));
				l1.add(g);
			}
		} catch (Exception e) {
			logger.error("Error <TipoUsuarioService: getAll>: " + e);
		} finally {
			getPs().close();
		}
		return l1;
	}

	@Override
	public Boolean save(TipoUsuario t) throws SQLException {
		boolean bandera = false;
		try {
			setPs(consPrepare(INSERT + TABLE + "(TUS_TIPO) VALUES (?)"));
			getPs().setString(1, t.getTusTipo());
			if (getPs().executeUpdate() == 1) {
				bandera = true;
			}
		} catch (Exception e) {
			logger.error("Error" + e);
		} finally {
			getPs().close();
		}
		return bandera;
	}

	@Override
	public Boolean update(TipoUsuario t) throws SQLException {
		boolean bandera = false;
		try {
			setPs(consPrepare(UPDATE + TABLE + SET + "TUS_TIPO = ? " + WHERE + " TUS_ID = ?"));
			getPs().setString(1, t.getTusTipo());
			getPs().setInt(2, t.getTusId());
			if (getPs().executeUpdate() == 1) {
				bandera = true;
			}
		} catch (Exception e) {
			logger.error("Error" + e);
		} finally {
			getPs().close();
		}
		return bandera;
	}

	@Override
	public Boolean delete(TipoUsuario t) throws SQLException {
		boolean bandera = false;
		try {
			setPs(consPrepare(DELETE + TABLE + WHERE + " TUS_ID = ?"));
			getPs().setInt(1, t.getTusId());
			if (getPs().executeUpdate() == 1) {
				bandera = true;
			}
		} catch (Exception e) {
			logger.error("Error" + e);
		} finally {
			getPs().close();
		}
		return bandera;
	}

	@Override
	public TipoUsuario finById(Integer id) throws SQLException {
		TipoUsuario g =  null;
		try {
			setPs(consPrepare(SELECT + TABLE + WHERE + "TUS_ID = ?"));
			getPs().setInt(1, id);
			setRs(getPs().executeQuery());
			while (getRs().next()) {
				g = new TipoUsuario(getRs().getInt(1), getRs().getString(2));
			}
		} catch (Exception e) {
			logger.error("Error: " + e);
		} finally {
			getPs().close();
		}
		return g;
	}

	@Override
	public TipoUsuario finById(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
